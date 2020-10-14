package fraction;

import java.util.Random;


public class Test {
    public static void main(String[] args) throws Exception {
        try {

            final int k = 5;

            Random r = new Random();
            
            //создания массива дробей
            Fraction[] fracs = new Fraction[k];
            for (int i = 0; i < k; i++) {
               int n=-k + r.nextInt(2*k);
               int m=0;
               while (m == 0){
                   m = -k + r.nextInt(2*k);
               }
               fracs[i] = new Fraction(n,m);
            }
            
            //печать дробей
            System.out.println("Fractions:");
            for (Fraction f : fracs) {
                System.out.println(f.toString());
            }

            //сумма дробей
            System.out.println("add test: " + fracs[0].toString() + " + " + fracs[1].toString() + " = " + fracs[0].add(fracs[1]).toString());

            //дробь + число
            System.out.println("add test: " + fracs[0].toString() + " + " + 2 + " = " + (fracs[0].add(2)).toString());

            //разность дробей
            System.out.println("sub test: " + fracs[2].toString() + " - " + fracs[3].toString() + " = " + fracs[2].sub(fracs[3]).toString());

            //дробь - число
            System.out.println("sub test: " + fracs[2].toString() + " - " + 3 + " = " + (fracs[2].sub(3).toString()));

            //умножение дробей
            System.out.println("mul test: " + fracs[4].toString() + " * " + fracs[2].toString() + " = " + fracs[4].mul(fracs[2]).toString());

            //деление дробей
            System.out.println("div test: " + fracs[3].toString() + " / " + fracs[1].toString() + " = " + fracs[3].div(fracs[1]).toString());

            //сравнение дробей
            System.out.println("compare: " + fracs[1].toString() + " , " + fracs[3].toString() + " : " + fracs[1].compare(fracs[3]));
            
            System.out.println("compare: " + fracs[2].toString() + " , " + 1.5 + " : " + fracs[1].compare(1.5));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
