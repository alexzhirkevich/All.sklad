package parking.index;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.Vector;

public interface IndexBase {
	TreeSet<String> getKeys(Comparator<String> comp );
	void put( String key, Long value );
	boolean contains( String key );
	Vector<Long> get(String key );
}