package parking.index;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class KeyCompReverse implements Comparator<String> {
	public int compare(String o1, String o2) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			return sdf.parse(o1).compareTo(sdf.parse(o2));
		}
		catch (ParseException e) {
			return o2.compareTo(o1);
		}
	}
}