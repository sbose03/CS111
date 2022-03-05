public class PolygonTransform {
    public static double[] copy(double[] array) {
        double [] coordinates = new double[array.length];
        for (int i = 0; i < array.length; ++i) {
            coordinates[i] = array[i]; 
        }
        return coordinates;
    }   
    public static void scale(double[] x, double[] y, double alpha) {
        for (int j = 0; j < y.length; ++j)  { 
            x[j] *= alpha;
            y[j] *= alpha;
        }
    }
    public static void translate(double[] x, double[] y, double dx, double dy) {
        for (int k = 0; k < x.length; ++k) {
            x[k] += dx;
            y[k] += dy;
        }
    }   
    public static void rotate(double[] x, double[] y, double theta) {
        double radians = Math.toRadians((theta));
        double tempVal = 0;
        for (int l = 0; l < x.length; ++l) {
            tempVal = x[l];
            x[l] = x[l] * Math.cos(radians) - y[l] * Math.sin(radians);
            y[l] = y[l] * Math.cos(radians) + tempVal * Math.sin(radians);        
        }
    }
}