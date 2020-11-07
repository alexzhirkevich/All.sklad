package lab5;

import java.lang.reflect.Array;
import java.util.*;

import sun.jvm.hotspot.utilities.CompactHashTable.SymbolVisitor;

public class Test {

    public static void main(String[] args) throws Exception{

        try{
            Ball[] balls = {
                new Ball(5.6,"Blue",2),
                new Ball(1.2, "Green",1),
                new Ball(3.4, "Red", 3), 
                new Ball(8.2, "Yellow",5),
                new Ball(4.4, "Orange", 4)
            };

            System.out.println("Before sorting:");
            for (Ball ball : balls){
                System.out.println(ball.toString());
            }

            System.out.println("\nConstructor test");
            Ball b = new Ball(balls[0].toString());
            System.out.println(b.toString());

            Arrays.sort(balls,Ball.getComparator(0));

            System.out.println("\nAfter sorting by number:");
            for (Ball ball : balls){
                System.out.println(ball.toString());
            }

            Arrays.sort(balls,Ball.getComparator(2));

            System.out.println("\nAfter sorting by radius:");
            for (Ball ball : balls){
                System.out.println(ball.toString());
            }

            System.out.println("\nAreas:");
            for (Ball ball : balls){
                System.out.println(ball.area());
            }
                
            System.out.println("\nVolumes:");
            for (Ball ball : balls){
                System.out.println(ball.volume());
            }

            System.out.println("\nException test:");
            Ball.getComparator(4);

        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}