package parking;

import java.io.Serializable;

public class Car implements Serializable {

	private static final long serialVersionUID = 1l;

	private String number;
	private String owner;

	public Car(){
		number = null;
		owner = null;
	}

	public Car(String number, String owner){
		this();
		this.number = number;
		this.owner = owner;
	}

	public Car(final Car c) {
		this.number = c.number;
		this.owner = c.owner;
	}

	public String getNumber() {
		return number;
	}

	public String getOwner() {
		return owner;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "[" + number + " | " + owner + "]";
	}
}
