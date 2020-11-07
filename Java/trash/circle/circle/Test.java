package circle;

import java.util.Scanner;

public class Test {
	    static final int SIZE = 3;
	    public static void main(String[] args) {
	    	Circle arr [] = new Circle [SIZE];	    	
	    	for ( int i = 0; i < SIZE; i++ ) {
	    		System.out.println("Введите значение абсциссы x через запятую, если значение не целое (например 5,6): ");
	    		Scanner x = new Scanner(System.in);
	            double tempX = x.nextDouble();
	            System.out.println("Введите значение ординаты y через запятую, если значение не целое (например 5,6): ");
	    		Scanner y = new Scanner(System.in);
	            double tempY = y.nextDouble();
	            arr[i] = new Circle(tempX, tempY);
	            arr[i].Print();
	        }
	    	double maxPer = 0;
	    	int ind1 = 0;
	    	for ( int i = 0; i < SIZE; i++ ) {   		
	    		if (arr[i].getPerimetr() >= maxPer) {
	    			maxPer = arr[i].getPerimetr();
	    			ind1 = i;
	    		}
	    	}
	    	System.out.println("Максимальный периметр у объекта с индексом: " + ind1);
	    	double minPer = maxPer;
	    	int ind2 = 0;
	    	for ( int i = 0; i < SIZE; i++ ) {   		
	    		if (arr[i].getPerimetr() <= minPer) {
	    			minPer = arr[i].getPerimetr();
	    			ind2 = i;
	    		}
	    	}
	    	System.out.println("Минимальный периметр у объекта с индексом: " + ind2);
	    	double maxSq = 0;
	    	int ind3 = 0;
	    	for ( int i = 0; i < SIZE; i++ ) {   		
	    		if (arr[i].getSquare() >= maxSq) {
	    			maxSq = arr[i].getSquare();
	    			ind3 = i;
	    		}
	    	}
	    	System.out.println("Максимальная площадь у объекта с индексом: " + ind3);
	    	double minSq = maxSq;
	    	int ind4 = 0;
	    	for ( int i = 0; i < SIZE; i++ ) {   		
	    		if (arr[i].getSquare() <= minSq) {
	    			minSq = arr[i].getSquare();
	    			ind4 = i;
	    		}
	    	}
	    	System.out.println("Минимальная площадь у объекта с индексом: " + ind4);
	    	boolean flag = false;
	    for(int i = 0; i < SIZE; i++) 
	    	 for(int j = i + 1; j < SIZE; j++) {
	    		 double k = (arr[i].y + arr[j].y)/(arr[i].x + arr[j].x);
	    		 double b = (arr[i].y)-(k*arr[i].x);
	    		 for(int k_ = j + 1; k_ < SIZE; k_++) {
	    			 if(arr[k_].y == (k*arr[k_].x+b))
	    			 {
	    				 flag = true;
	    				 System.out.println("На одной прямой центров окружностей с номерами "+i+" и "+j + " лежит окружность с номером "+ k_ + ";");
	    			 }
	    				 
	    		 }
	    	 }
	    if (flag == false) {
	  System.out.println("Только каждые две окружности лежат на одной прямой");
	    }
}
}
