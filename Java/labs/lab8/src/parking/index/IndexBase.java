package parking.index;

import java.util.Comparator;

public interface IndexBase {
	String[] getKeys( Comparator<String> comp );
	void put( String key, long value );
	boolean contains( String key );
	long[] get( String key );
}