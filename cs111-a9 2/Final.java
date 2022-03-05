public class Final {
  
      public static int foo(int x, int y) { 

            if (y == 0){
                return 1;
            }  
        
            if (y % 2 == 0){
                return foo(x*x, y/2);
            } 
        
            else {
                return foo(x*x, y/2) * x; 
            }
        }

        public static void main (String[] args) {
            System.out.print(foo(4, 5));  
        }
}
