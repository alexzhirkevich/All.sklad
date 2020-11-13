package test;

import airport.*;
import connector.Connector;
import locale.MyLocale;
import java.util.Locale;

public class Test {

	public final static String fileName = "airports.dat";

	public static void main(String[] args) {
		try {

			Locale loc = MyLocale.createLocale(args);

			if ( loc == null ) {
				System.err.println(
						"Invalid argument(s)\n" +
						"Syntax: language country\n" +
						"Example: be BY" );
				System.exit(1);
			}
			MyLocale.set(loc);

			//создание аэропортов
			Airport[] airports = {
					new Airport(MyLocale.getString(MyLocale.airport1)),
					new Airport(MyLocale.getString(MyLocale.airport2)),
					new Airport(MyLocale.getString(MyLocale.airport3)),
			};

			//создание экипажей
			airports[0].addStuff(new CrewMember("William Smith", CrewMember.Role.pilot));
			airports[0].addStuff(new CrewMember("Mason Johnson", CrewMember.Role.navigator));
			airports[0].addStuff(new CrewMember("James Williams", CrewMember.Role.operator));
			airports[0].addStuff(new CrewMember("Emma Brown", CrewMember.Role.steward));

			airports[1].addStuff(new CrewMember("Benjamin Jones", CrewMember.Role.pilot));
			airports[1].addStuff(new CrewMember("Michael Miller", CrewMember.Role.navigator));
			airports[1].addStuff(new CrewMember("Ethan Davis", CrewMember.Role.operator));
			airports[1].addStuff(new CrewMember("Olivia Rodriguez", CrewMember.Role.steward));

			//создание самолетов
			airports[0].addPlane(new Plane(30000d, 3000d, MyLocale.getString(MyLocale.plane1)));
			airports[1].addPlane(new Plane(25000d, 2500d, MyLocale.getString(MyLocale.plane2)));

			//создание рейсов
			airports[0].addFlight(new Flight(airports[0], airports[1], airports[0].getPlane(0), 12000d, 1200d, airports[0].getNextTeam()));
			airports[0].addFlight(new Flight(airports[0], airports[1], airports[1].getPlane(0), 12000d, 1200d, airports[1].getNextTeam()));


			airports[1].addFlight(new Flight(airports[1], airports[0], airports[1].getPlane(0), 19000d, 1200d, airports[1].getNextTeam()));
			airports[1].addFlight(new Flight(airports[1], airports[0], airports[0].getPlane(0), 22000d, 1200d, airports[0].getNextTeam()));

			//тестирование записи и чтения объектов
			Connector c = new Connector(fileName);

			c.write(airports);
			airports = c.read();

			System.out.println("\n" + MyLocale.getString(MyLocale.airports));
			for (int i = 0; i < airports.length; i++)
				System.out.println(airports[i]);
			System.out.println();

			System.out.println(MyLocale.getString(MyLocale.planes));
			for (int i = 0; i<airports.length; i++) {
				for (int j = 0; j < airports[i].getPlanesCount(); j++)
					System.out.println(airports[i].getPlane(j));
			}
			System.out.println();

			System.out.println(MyLocale.getString(MyLocale.crews));
			System.out.println(airports[0].getNextTeam());
			System.out.println(airports[1].getNextTeam());
			System.out.println();

			startFlight(airports[0], 0);
			startFlight(airports[1], 0);


			if (!endFlight(airports[0],0)) {
				changeDestination(airports[0],0, airports[2]);
				endFlight(airports[0], 0);
			}

			if (!endFlight(airports[1], 0)) {
				changeDestination(airports[1], 0, airports[2]);
				endFlight(airports[1],0);
			}

			startFlight(airports[0], 0);
			startFlight(airports[1], 0);

			setWeatherConds(airports[1], Airport.Weather.inapropreate);

			if (!endFlight(airports[0],0)) {
				changeDestination(airports[0],0, airports[2]);
				endFlight(airports[0],0);
			}

			if (!endFlight(airports[1],0)) {
				changeDestination(airports[1],0, airports[2]);
				endFlight(airports[1],0);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static boolean startFlight(Airport ap, int idx) throws Exception{
		if (!ap.startFlight(idx)) {
				if (ap.getWeatherConditions() == Airport.Weather.inapropreate) {
				System.out.println(MyLocale.getString(MyLocale.start_failure) + " " + ap.getName());
			}
			if (ap.getFlightDestination(idx).getWeatherConditions() == Airport.Weather.inapropreate) {
				System.out.println(MyLocale.getString(MyLocale.start_failure) + " " + ap.getFlightDestination(idx).getName());

			}
			return false;
		} else {
			System.out.println(MyLocale.getString(MyLocale.start_success) + " | " + MyLocale.getString(MyLocale.from) + ap.getName() + " | " + MyLocale.getString(MyLocale.to) + ap.getFlightDestination(idx).getName());
		}
		return true;
	}

	public static void changeDestination(Airport from, int idx, Airport d) throws Exception{
		Airport ap = from.getFlightDestination(idx);
		if (from.setFlightDestination(idx,d)) {
			System.out.println(MyLocale.getString(MyLocale.changed) + MyLocale.getString(MyLocale.from)+ ap.getName() + " " + MyLocale.getString(MyLocale.to) + d.getName());
		}
	}

	public static boolean endFlight(Airport ap,int idx) throws Exception {
		if (ap.getFlightDestination(idx).getWeatherConditions() == Airport.Weather.inapropreate) {
			System.out.println(MyLocale.getString(MyLocale.warning) + " " + ap.getFlightDestination(idx).getName() + " | " +MyLocale.getString(MyLocale.change));
			return false;
		}
		System.out.println(MyLocale.getString(MyLocale.end_success));
		ap.closeFlight(idx);
		return true;
	}

	static void setWeatherConds(Airport ap, Airport.Weather w) {
		if (ap.getWeatherConditions() != w && w == Airport.Weather.inapropreate) {
			System.out.println(MyLocale.getString(MyLocale.storm_started) + ap.getName());
		}
		ap.setWeatherConditions(w);
	}
}
