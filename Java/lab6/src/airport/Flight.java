package airport;

import locale.MyLocale;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Objects;

public class Flight extends CreationDateHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayDeque<CrewMember> command;
	private Airport from;
	private Airport to;
	private Plane plane;
	private Double distance;
	private Double load;

	public Flight() {
		command = null;
		from = null;
		to = null;
		distance = 0d;
		load = 0d;
		command = new ArrayDeque<CrewMember>();
	}

	public Flight(Airport from, Airport to,Plane plane, Double load, Double distance,  CrewMember[] command) throws Exception {
		this();
		if (distance > plane.getFlyRange()){
			throw new Exception("Too long distance for plane " + plane.getModel());
		}
		if (load > plane.getCapacity()){
			throw new Exception("Too heavy load for plane " + plane.getModel());
		}
		this.distance = distance;
		this.load = load;
		this.from = from;
		this.to = to;
		this.plane = plane;
		for (CrewMember c : command){
			this.command.push(c);
		}
	}

	public Flight(final Flight f) throws Exception {
		this(f.from, f.to, f.plane, f.load, f.distance, f.command.toArray(new CrewMember[f.command.size()]));
	}

	public boolean start() {
		return (from.weatherConditions != Airport.Weather.inapropreate &&
				to.weatherConditions!= Airport.Weather.inapropreate);
	}

	public CrewMember[] getCommand() {
		return (CrewMember[])command.toArray();
	}

	public Airport getFrom() {
		return from;
	}

	public Airport getTo() {
		return to;
	}

	public Plane getPlane() { return plane; }

	public void setCommand(CrewMember[] command) {
		this.command.clear();
		for (CrewMember c : command){
			this.command.push(c);
		}
	}

	public void setFrom(Airport from) {
		this.from = from;
	}

	public boolean setTo(final Airport ap) {
		if (ap.weatherConditions == Airport.Weather.inapropreate) {
			return false;
		}
		this.to = ap;
		return true;
	}

	public void addCrew(CrewMember c){
		command.add(c);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Flight flight = (Flight) o;
		return Objects.equals(command, flight.command) &&
				Objects.equals(from.getName(), flight.from.getName()) &&
				Objects.equals(to.getName(), flight.to.getName()) &&
				Objects.equals(plane,flight.plane) &&
				Objects.equals(distance, flight.distance) &&
				Objects.equals(load, flight.load);
	}

	@Override
	public int hashCode() {
		return Objects.hash(command, from, to, plane, distance, load);
	}

	@Override
	public String toString() {
		return "[" +
				MyLocale.getString(MyLocale.from) + from.getName() + " | "+
				MyLocale.getString(MyLocale.to) + to.getName() + " | " +
				plane.getModel() + " | " + getCreationDateString();
	}
}
