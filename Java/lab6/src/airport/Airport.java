package airport;

import locale.MyLocale;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Airport extends CreationDateHolder implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum Weather { normal, inapropreate }

	String name;
	Weather weatherConditions;
	Stuff stuff;
	ArrayList<Plane> planes;
	ArrayList<Flight> flights;

	public Airport() {
		name = "unnamed";
		weatherConditions = Weather.normal;
		stuff = new Stuff();
		planes = new ArrayList<>();
		flights = new ArrayList<>();
	}

	public Airport(String name) {
		this();
		this.name = name;
	}

	public Airport(final Airport ap) {
		this();
		this.stuff = new Stuff(ap.stuff);
		this.planes = new ArrayList<>(planes);
		this.name = ap.name;
	}

	public String getName() {
		return name;
	}

	public Weather getWeatherConditions() {
		return weatherConditions;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWeatherConditions(Weather conditions) {
		this.weatherConditions = conditions;
	}

	public boolean addPlane(Plane p) {
		return planes.add(new Plane(p));
	}

	public void addStuff(CrewMember crew){
		stuff.addStuff(new CrewMember(crew));
	}

	public Plane getPlane(int idx) throws IndexOutOfBoundsException {
		if (idx <0 || idx >= planes.size())
			throw new IndexOutOfBoundsException("Invalid plane index");
		return new Plane(planes.get(idx));
	}

	public CrewMember getNextCrewMember(CrewMember.Role role){
		return stuff.getCrew(role);
	}

	public CrewMember[] getNextTeam() {
		return stuff.getTeam();
	}

	public boolean addFlight(Flight f) throws Exception {
		return flights.add(new Flight(f));
	}

	public boolean startFlight(int idx) throws IndexOutOfBoundsException {
		if (idx <0 || idx >= flights.size())
			throw new IndexOutOfBoundsException("Invalid flight index");
		return flights.get(idx).start();
	}

	public boolean setFlightDestination(int idx, Airport dest) throws IndexOutOfBoundsException {
		if (idx <0 || idx >= flights.size())
			throw new IndexOutOfBoundsException("Invalid flight index");
		return flights.get(idx).setTo(dest);
	}

	public Airport getFlightDestination(int idx) throws IndexOutOfBoundsException{
		if (idx <0 || idx >= flights.size())
			throw new IndexOutOfBoundsException("Invalid flight index");
		return flights.get(idx).getTo();
	}

	public Flight getFlightData(int idx) throws Exception {
		if (idx <0 || idx >= flights.size())
			throw new IndexOutOfBoundsException("Invalid flight index");
		return new Flight(flights.get(idx));
	}

	public int getPlanesCount(){
		return planes.size();
	}

	public int getFlightCount(){
		return flights.size();
	}

	public void closeFlight(int idx) throws IndexOutOfBoundsException {
		if (idx <0 || idx >= flights.size())
			throw new IndexOutOfBoundsException("Invalid flight index");
		flights.remove(idx);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Airport airport = (Airport) o;
		return Objects.equals(name, airport.name) &&
				weatherConditions == airport.weatherConditions &&
				Objects.equals(stuff, airport.stuff) &&
				Objects.equals(planes, airport.planes) &&
				Objects.equals(flights, airport.flights);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, weatherConditions, stuff, planes, flights);
	}

	@Override
	public String toString() {
		return 	"["+name + " | "+
				MyLocale.getString(MyLocale.weather_conds) +
				MyLocale.getString(weatherConditions.toString()) + " | " +
				getCreationDateString() +']';
	}
}
