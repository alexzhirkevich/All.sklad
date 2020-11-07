package lab5;

import java.util.*;

public class Ball implements Body<Ball>{

    protected String color;
    protected Integer number;
    protected Double radius;

    private int it_idx=0;


    public Ball(double radius, String color, int number){
        this.radius = radius;
        this.color = color;
        this.number = number;
    }

    public Ball(String ball){
        this.number = Integer.parseInt(ball.substring(0,ball.indexOf('|')));
        ball = ball.substring(ball.indexOf('|')+1);
        this.color = ball.substring(0, ball.indexOf('|'));
        ball = ball.substring(ball.indexOf('|')+1);
        this.radius = Double.parseDouble(ball);
    }

    @Override
    public double area() {
        return 4 * Math.PI * Math.pow(radius,2);
    }

    @Override
    public double volume() {
        return 4/3 * Math.PI * Math.pow(radius,3);
    }

////////////////////////////////////////////////////////////////////

    @Override
    public Iterator<Object> iterator() {
        return this;
    }

    @Override
    public boolean hasNext() {
        return it_idx > 2 ? false : true;
    }

    private void reset(){
        it_idx = 0;
    }

    @Override
    public Object next() {

        switch (it_idx) {
        case 0:
            it_idx++;
            return number;
        case 1:
            it_idx++;
            return color;
        case 2:
            it_idx++;
            return radius;
        default:
            reset();
            return null;
        }
    }

    public Object getParam(int idx) throws IndexOutOfBoundsException {
        switch(idx) {
            case 0: return number;
            case 1: return color;
            case 2: return radius;
            default: throw new IndexOutOfBoundsException(idx);
        }
    }

    public void setParam(int idx,Object param) throws Exception {
        if (idx==0){
            if (param.getClass() != Integer.class){
                throw new ClassCastException("Invalid type: " + param);
            }
            System.out.println(param.getClass());
            number = (Integer)param; 
            return;
        }
        if (idx==1) {
            if (param.getClass() != String.class){
                throw new ClassCastException("Invalid type: " + param);
            }
            color = (String)param;
            return;
        }
        if (idx==2){
            if (param.getClass() != Double.class){
                throw new ClassCastException("Invalid type: " + param);
            }
            radius = (Double)param;
            return;
        }
        throw new IndexOutOfBoundsException(idx);
    }

////////////////////////////////////////////////////////////////////

    public static Comparator<Ball> getComparator(int sortBy) throws IndexOutOfBoundsException {
        switch(sortBy){
        case 0:
            return new Comparator<Ball> () {
                @Override   
                public int compare(Ball b1, Ball b2) {
                    return Integer.compare(b1.number,b2.number);
                }
            };
        case 1:
            return new Comparator<Ball> () {
                @Override   
                public int compare(Ball b1, Ball b2) {
                    return b1.compareTo(b2);
                }
            };
        case 2:
            return new Comparator<Ball> () {
                @Override   
                public int compare(Ball b1, Ball b2) {
                    return Double.compare(b1.radius,b2.radius);
                }
            };
        default: throw new IndexOutOfBoundsException(sortBy);
        }
    }
    
    @Override
    public int compareTo(Ball ball) {

        if (Double.compare(radius, ball.radius) != 0){
           return Double.compare(radius, ball.radius);   
        }
       
        if (Integer.compare(number, ball.number) != 0) {
            return Integer.compare(number, ball.number);
        }
        return 0;
    }

    @Override
    public int compare(Ball b1, Ball b2) {
        return b1.compareTo(b2);
    }

////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return Integer.toString(number) + "|" + color + "|" + Double.toString(radius);
    }
}