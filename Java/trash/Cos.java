public class Cos
{
    public static void main(String[] args) {
         if ( args.length != 2 ) {
             System.err.println("Invalid number of arguments");
             System.exit(1);
         }
         double x = Double.parseDouble(args[0]);
         int k = Integer.parseInt( args[1] );
         if (k<=1){
             System.err.println("Invalid argument: " + k );
             System.exit(1);
         }
         
        double Eps = 1 / Math.pow( 10, k + 1 );
        double step = 1;
        int n = 1;
        double s = 1;

        while( Math.abs( step ) >= Eps ) {
            step =-step*Math.pow(x,2)/((2*n)*(2*n-1));
            s += step;
            n++;
        }
        String fmt = "Result: %." + args[1] + "f\n";
        System.out.printf( fmt, s );
        fmt="Math: %." + args[1] + "f\n";
        System.out.printf( fmt, Math.cos(x));
        System.exit(0);
    }
}