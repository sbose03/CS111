/*************************************************************************
 *  Compilation:  javac Sierpinski.java
 *  Execution:    java Sierpinski
 *
 *  @author:
 *
 *************************************************************************/

public class Sierpinski {

    // Height of an equilateral triangle whose sides are of the specified length. 
    public static double height(double length) {
	// WRITE YOUR CODE HERE

    double height = (length * Math.sqrt(3)) / 2;
    return height;

    }

    // Draws a filled equilateral triangle whose bottom vertex is (x, y) 
    // of the specified side length. 
    public static void filledTriangle(double x, double y, double length) {
        double midpoint = length / 2; // Establishes midpoint
        double height = height(length); // Gets the height

        double[] xcoordinates = {x, x - midpoint, x + midpoint};   // all 3 of the x-coordinates
        double[] ycoordinates = {y, y + height, y + height}; // all 3 of the y-coordinates

        StdDraw.filledPolygon(xcoordinates, ycoordinates);

	
    }

    // Draws a Sierpinski triangle of order n, such that the largest filled 
    // triangle has bottom vertex (x, y) and sides of the specified length. 
    public static void sierpinski(int n, double x, double y, double length) {
	// WRITE YOUR CODE HERE
        double midpoint = length / 2.0;
        double height = height(length);
        
      if (n > 0) {
        if (n > 0) {    
            filledTriangle(x, y, length); // draws triangle
            sierpinski(n-1, x, y+height, midpoint);
            sierpinski(n-1, x-midpoint, y, midpoint);
            sierpinski(n-1, x+midpoint, y, midpoint);
        }
    }
}

    // Takes an integer command-line argument n; 
    // draws the outline of an equilateral triangle (pointed upwards) of length 1; 
    // whose bottom-left vertex is (0, 0) and bottom-right vertex is (1, 0); and 
    // draws a Sierpinski triangle of order n that fits snugly inside the outline. 
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double length = 1;
        double height = height(length);
        double midpoint = length / 2;
        double[] x = {0, midpoint, 1};
        double[] y = {0, height, 0};
        StdDraw.polygon(x, y); 
        sierpinski(n, midpoint, 0, midpoint);
    }
}
