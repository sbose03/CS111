public class LeasingCost {
    
    /* 
     * Description:
     *      This method creates an array of Vehicles objects from the given file name
     *      ******IMPORTANT******
     *      This method calls buildVehicle() method, which is STUDENT'S task to complete.
     *
     * Parameters:
     *      filename: the file name containing the vehicles description
     *
     * File format:
     *      the first line of the file containing an integer representing how many vehicles will be described 
     *      in the following lines.
     *      Each line other than the first line describes one vehicle; 
     *      7 or 8 fragments of data in randomly placed order describing the vehicle (7 for gas car, 8 for electric car) in each line. 
     *      Each fragment end with the ';' character
     * 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *      type:gas; name:civic; due:1000; length:3; monthly:295; mile/unit:34; 
     *
     * Returns:
     *      this method returns an array of Vehicle objects 
     */
	public static Vehicle[] createAllVehicles(String filename) {
        StdIn.setFile(filename);

        int numberOfCars = Integer.parseInt( StdIn.readLine() );
        Vehicle[] vehicles = new Vehicle[numberOfCars];

        for ( int i = 0; i < numberOfCars; i++ ) {
        	String line = StdIn.readLine();
            vehicles[i] = createVehicle(line);
        }
        return vehicles;
    }

    /* 
     * Description:
     *      This method calculates the CO2 emission given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		co2PerUnit:
     *			kg of CO2 released with the combustion of each gallon of gasoline, or
     *			kg of CO2 are emitted to generate 1 kWh on average
     * Returns:
     *      this method returns a double representing the CO2 emission produced by the car during the lease.
     */
	public static double computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit ){
		double miles = numberOfMonth * mileageAllowance ;
		return miles/usage*co2PerUnit;
    }

    /* 
     * Description:
     *      This method calculates the cost the fuel during the lease given several parameters
     * Parameters:
     *      numberOfMonth: 
     *          the lease length in months
     *      usage: 
     *          miles the car can drive per gallon if fuelType is GAS, or
     *			miles the car can drive per kWh    if fuelType is ELECTRIC
     *      mileageAllowance: 
     *			mileage allowance per month
     *		fuelPrice: 
     *			price of 1 kWh in cents of a dollar,  if fuelType is GAS, or
     *			price of one gallon of gas in dollars if fuelType is ELECTRIC
     * Returns:
     *      this method returns a double representing the fuel cost during the lease
     */
	public static double computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice){
    	double miles = numberOfMonth * mileageAllowance;
        double cost = miles/usage * fuelPrice;
    	return cost;
    }

    /* 
     * Description:
     *      This method calculates the cost of lease
     * Parameters:
     *      dueAtSigning: 
     *          the dollar amount due at signing the lease
     *      numberOfMonths: 
     *          lease length in months
     *      monthlyCost: 
     *			cost of the lease per month
     * Returns:
     *      this method returns a double representing the lease cost during the entire lease
     */
	public static double computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost){
    	
        return dueAtSigning + numberOfMonths*monthlyCost;
    }

    /* 
     * Description:
     *      This method creates and returns an Vehicle object with name, Lease, and Fuel properly populated based on the given string
     *      
     * Parameters:
     *      one string containing 7~8 fragments descrbing the 
     *   All possible fragments:
     *      type:FULETYPE;
     *          FULETPE can be gas or electric
     *      name:CARNAME;
     *          CARNAME is the name of the car
     *      due:DUEATSIGNING;
     *          DUEATSIGNING is a double number describing the dollar amount due when signing the lease
     *      length:LEASELENGTH;
     *          LEASELENGTH is an integer number describing the lease length in months
     *      monthly:MONTHLYCOST;
     *          MONTHLYCOST is a double number describing the monthly lease cost in dollar
     *      mile/unit:USAGE; 
     *          USAGE is a double describing miles the car can drive per gallon if fuel type is GAS, or miles the car can drive per kWh if fuel type is ELECTRIC
     *      allowance:MILEAGEALLOWANCE;
     *          MILEAGEALLOWANCE is an integer describing the maximum distance the car is allowed to travel per month
     *      charger:CHARGERCOST;
     *          CHARGERCOST is a double describing the cost of charger for electric cars, this fragment can only appear if the line is describing an electrical car
     *   Example of a line:
     *          type:gas.name:civic.due:1000.length:3.monthly:295.mile/unit:34.mileageAllowance:1200.
     *          monthly:238.name:Bolt.due:1000.mileageAllowance:20000.length:15.mile/unit:50.type:electric.charger:500.
     * Returns:
     *      this method creates and returns an Vehicle object with name, Lease, and Fuel properly populated
     *
     * Hint: 
     *      to extract the information of each fragments, we can use 
     *          s.substring(int startIndex, int endIndex) 
     *          s.indexOf(String target)
     *          s.indexOf(char target)
     *
     *      for example, assume we have: 
     *          String s = "description1:ABCD;  description2:EFGH;  description3:IJKL;"
     *      if we want to find the data for description 2, we can first take the substring of the entire string from the letter 'E'
     *      but first we need to find the index of 'E', we can do it by find the index of the string "description2:" and add 13("description2" is 13 chars long)to it
     *      and then we can take the substring from 'E' until the end of the entire string
     *      now use s.substring to exract:
     *          "EFGH;  description3:IJKL;" and let's call it newString for now. 
     *      to extract "EFGH" (the data we want) from newString. we need to find the index of the first ';' which we can simply use newString.indexOf(';')
     *      then we can take the substring of the newString knowing the index of ;
     *      we now have extracted "EFGH" from the String s
     *      the last step is just to convert the datatype based on what type of data we are extracting
     */
	public static Vehicle createVehicle(String description){
        
        // COMPLETE THIS METHOD
	  
      String  name;
      String searchString;
      Fuel fuel;
      Lease lease;
      String half1;
      int index;
      int semiColonIndex;


      searchString = "name:";
      index = description.indexOf(searchString);

      if(index != -1){
      
          String subDescription = description.substring(index);

          semiColonIndex = subDescription.indexOf(";");

          name = subDescription.substring(searchString.length(),semiColonIndex );

      
          searchString = "name:"+name+";";
          index = description.indexOf(searchString);

          half1 = description.substring(0,index);
          description =  half1 + description.substring(half1.length()+searchString.length());
      } else{
          return null;
      }

      int due;
      int monthly;
      int allowance;
      int length;
      searchString = "due:";

      index = description.indexOf(searchString);

      if(index != -1){
          String subDescription = description.substring(index);
          semiColonIndex = subDescription.indexOf(";");
          String dueString = subDescription.substring(searchString.length(),semiColonIndex );
          due = Integer.parseInt(dueString);

          searchString = "due:"+due+";";
          index = description.indexOf(searchString);
          half1 = description.substring(0,index);
          description =  half1 + description.substring(half1.length()+searchString.length()-1);
      } else{
          return null;
      }

      searchString = "length:";
      index = description.indexOf(searchString);
      if(index != -1){
          String subDescription = description.substring(index);
          semiColonIndex = subDescription.indexOf(";");
          String dueString = subDescription.substring(searchString.length(),semiColonIndex );
          length = Integer.parseInt(dueString);

          searchString = "length:"+length+";";
          index = description.indexOf(searchString);
          half1 = description.substring(0,index);
          description =  half1 + description.substring(half1.length()+searchString.length()-1);
      } else{
          return null;
      }

      searchString = "monthly:";
      index = description.indexOf(searchString);
      if(index != -1){
          String subDescription = description.substring(index);
          semiColonIndex = subDescription.indexOf(";");
          String dueString = subDescription.substring(searchString.length(),semiColonIndex );
          monthly = Integer.parseInt(dueString);

          searchString = "monthly:"+monthly+";";
          index = description.indexOf(searchString);
          half1 = description.substring(0,index);
          description =  half1 + description.substring(half1.length()+searchString.length()-1);
      } else{
          return null;
      }

      searchString = "allowance:";
      index = description.indexOf(searchString);
      if(index != -1){
          String subDescription = description.substring(index);
          semiColonIndex = subDescription.indexOf(";");
          String dueString = subDescription.substring(searchString.length(),semiColonIndex );
          allowance = Integer.parseInt(dueString);

          searchString = "allowance:"+allowance+";";
          index = description.indexOf(searchString);
          half1 = description.substring(0,index);
          description =  half1 + description.substring(half1.length()+searchString.length()-1);
      } else{
          return null;
      }
      lease = new Lease(due,length,monthly,allowance);

      int type;
      double usage;
      double charger;
      searchString = "type:";
      index = description.indexOf(searchString);
      if(index != -1){
          String subDescription = description.substring(index);
          semiColonIndex = subDescription.indexOf(";");
          String fueltype = subDescription.substring(searchString.length(),semiColonIndex );

          if (fueltype == "gas") {
            type = 1;
          }
          else {
              type = 2;
          }

        

          searchString = "type:"+fueltype+";";
          index = description.indexOf(searchString);
          half1 = description.substring(0,index);
          description =  half1 + description.substring(half1.length()+searchString.length()-1);
      } 
      else  {
          return null;
      }

      searchString = "mile/unit:";
      index = description.indexOf(searchString);
      if(index != -1){
          String subDescription = description.substring(index);
          semiColonIndex = subDescription.indexOf(";");
          String fuelUsage = subDescription.substring(searchString.length(),semiColonIndex );
          usage = Double.parseDouble(fuelUsage);

          searchString = "mile/unit:"+fuelUsage+";";
          index = description.indexOf(searchString);
          half1 = description.substring(0,index);
          description =  half1 + description.substring(half1.length()+searchString.length()-1);
      } else{
          return null;
      }

      if(type == Fuel.ELECTRIC){
          searchString = "charger:";
          index = description.indexOf(searchString);
          if(index != -1){
              String subDescription = description.substring(index);
              semiColonIndex = subDescription.indexOf(";");
              String fuelCharger = subDescription.substring(searchString.length(),semiColonIndex );
              charger = Integer.parseInt(fuelCharger);

              searchString = "charger:"+fuelCharger+";";
              index = description.indexOf(searchString);
              half1 = description.substring(0,index);
              description =  half1 + description.substring(half1.length()+searchString.length()-1);
          } else{
              return null;
          }
          fuel = new Fuel(usage,charger);
         }
      else{
          fuel = new Fuel(usage);
      }
      Vehicle vehicle = new Vehicle(name, fuel,lease);
      return vehicle;
	}

    /* 
     * Description:
     *      The method calculates and assign CO2Emission, FuelCost, leastCost, of each vehicle.
     *      
     * Parameters:
     *      vehicles: 
     *          an array of Vehicle objects, initilized by getVehicles() method
     *      gasPrice: 
     *          a double representing the price of gas in dollar/gallon
     *      electricityPrice: 
     *			a double representing the price of gas in dollar/kWh
     * Hint:
     *      **********REMEMBER charger cost for electric cars***************
     *      feel free to use:
     *          computeCO2(double numberOfMonth, double usage, double mileageAllowance, double co2PerUnit )
     *          computeFuelCost(double numberOfMonth, double usage, double mileageAllowance, double fuelPrice)
     *          computeLeaseCost(double dueAtSigning, int numberOfMonths, double monthlyCost)
     */
	public static void computeCO2EmissionsAndCost( Vehicle[] vehicles, double gasPrice, double electricityPrice ){
        double co2Emission;
        double fuelCost;
        double leaseCost;

        for ( int i = 0; i < vehicles.length; i++ ) {
            Vehicle currVehicle = vehicles[i];
            Fuel fuel = currVehicle.getFuel();
            Lease lease = currVehicle.getLease();
            leaseCost = computeLeaseCost(lease.getDueAtSigning(), lease.getLeaseLength(), lease.getMonthlyCost());

            if(fuel.getType()==Fuel.ELECTRIC){
                co2Emission = computeCO2(lease.getLeaseLength(),fuel.getUsage() , lease.getMileageAllowance(), Fuel.CO2EMITTED_ELECTRICITYCOMBUSTION);
                fuelCost = computeFuelCost(lease.getLeaseLength(), fuel.getUsage(), lease.getMileageAllowance(), electricityPrice);
                vehicles[i].setTotalCost(leaseCost + fuelCost + fuel.getCharger());
            }
            else    {
                co2Emission = computeCO2(lease.getLeaseLength(),fuel.getUsage() , lease.getMileageAllowance(), Fuel.CO2EMITTED_GASCOMBUSTION);
                fuelCost = computeFuelCost(lease.getLeaseLength(), fuel.getUsage(), lease.getMileageAllowance(), gasPrice);
                vehicles[i].setTotalCost(leaseCost + fuelCost);
            }
            vehicles[i].setCO2Emission(co2Emission);
            vehicles[i].setFuelCost(fuelCost);
         }
    	}


    /**
     * How to compile:
     *     javac LeasingCost.java
     * How to run:         
     *     java LeasingCost vehicles.txt 3.85 11.0
     **/
	public static void main (String[] args) {
        String filename         = args[0];
        double gasPrice 		= Double.parseDouble( args[1] );
		double electricityPrice = Double.parseDouble( args[2] );

		Vehicle[] vehicles = createAllVehicles(filename); 
		computeCO2EmissionsAndCost(vehicles, gasPrice, electricityPrice);

		for ( Vehicle v : vehicles ) 
            System.out.println(v.toString());
    }
}
