package circle;

import java.util.Scanner;
import java.util.Random;
import java.util.Date;

public class Circle {
	public double x; // абсцисса точки
    public double y; // ордината точки
    public double r;

    public Circle () {
    	x = 0;
    	y = 0;
    	r = 0;
    }
    public Circle(double x_, double y_) {
    	x = x_;
    	y = y_;
        Init(r);    
    } 
   

    private void Init (double r_) {
    	 boolean err;
         do {
             err = false;
             System.out.println("Введите радиус окружности: ");
             Scanner scan = new Scanner(System.in);
             if(scan.hasNextDouble()) {
                 r_ = scan.nextDouble();
                 if (r_ <= 0) {
                 System.out.println("Вы ввели некорректный радиус, попробуйте снова."); 
                 err = false;
                 }                	 
             } else {
                 System.out.println("Вы ввели не число, попробуйте снова");
                 err = true;
             }
         } while (err);
         r = r_;
     
}
    public double getPerimetr() {
    return 2*Math.PI*r;
    }
    public double getSquare() {
        return Math.PI*r*r;
        }
    public void Print() {
        System.out.println("x: "+x+ " y:" +y+" r:"+r);
    }
}
