package locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyLocale {
	private static final String strMsg = "Airport";
	private static Locale loc = Locale.getDefault();
	private static ResourceBundle res = ResourceBundle.getBundle(strMsg);

	public static Locale get() {
		return MyLocale.loc;
	}

	public static void set( Locale loc ) {
		MyLocale.loc = loc;
		res = ResourceBundle.getBundle( MyLocale.strMsg, MyLocale.loc );
	}

	public static ResourceBundle getBundle() {
		return MyLocale.res;
	}

	public static String getString( String key ) {
		return MyLocale.res.getString(key);
	}

	public static Locale createLocale( String[] args )	{
		if ( args.length != 2 )
			return null;
		return new Locale(args[0],args[1]);
	}
	public final static String from = "from";
	public final static String to = "to";
	public final static String airports = "airports";
	public final static String plane1 = "plane1";
	public final static String plane2 = "plane2";
	public final static String airport1 = "airport1";
	public final static String airport2 = "airport2";
	public final static String airport3 = "airport3";
	public final static String planes = "planes";
	public final static String crews = "crews";
	public final static String start_success = "start_success";
	public final static String start_failure = "start_failure";
	public final static String end_success = "end_success";
	public final static String changed = "changed";
	public final static String warning = "warning";
	public final static String change = "change";
	public final static String storm_started = "storm_started";
	public final static String weather_conds ="weather_conds";
	public final static String model ="model";
	public final static String capacity ="capacity";
	public final static String fly_range ="fly_range";
}
