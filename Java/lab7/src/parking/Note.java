package parking;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Note implements Serializable {

	private static final long serialVersionUID = 1l;

	private static final SimpleDateFormat sdf = new SimpleDateFormat();

	protected Car car;
	protected Date start;
	protected Date end;
	protected Double price;

	public Note() {
		car = null;
		start = null;
		end = null;
		price = 0d;
	}

	public Note(Car car, Date start, Date end, Double price) throws IllegalArgumentException{

		if (car == null || start == null || end == null || price == null || end.compareTo(start) < 0){
			throw new IllegalArgumentException("Incorrect note arguments");
		}
		this.car = new Car(car);
		this.start = (Date)start.clone();
		this.end = (Date)end.clone();
		this.price = price;
	}

	public Note(final Note n ){
		this.car = new Car(n.car);
		this.start = (Date)n.start.clone();
		this.end = (Date)n.end.clone();
		this.price = n.price;
	}

	public String getCarNumber(){
		return car.getNumber();
	}

	public String getCarOwner(){
		return car.getOwner();
	}

	public String getStart(){
		return sdf.format(start);
	}

	public String getEnd(){
		return sdf.format(end);
	}

	public Double getPrice() {
		return price;
	}

	public void setCarNumber(String num) {
		if (car == null){
			car = new Car();
		}
		this.car.setNumber(num);
	}

	public void setCarOwner(String own) {
		if (car == null){
			car = new Car();
		}
		this.car.setOwner(own);
	}

	public void setStart(Date start) {
		this.start = (Date)start.clone();
	}

	public void setEnd(Date end) {
		this.end = (Date)end.clone();
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "[" + car.getNumber() + " | " +
				car.getOwner() + " | " +
				sdf.format(start) + " | " +
				sdf.format(end) +  " | " +
				price+ "]";
	}
}
