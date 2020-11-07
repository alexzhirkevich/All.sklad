package parking.index;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class UniqueIndex implements Serializable, IndexBase {

	private static final long serialVersionUID = 1L;

	private TreeMap<String,Long> map;

	public UniqueIndex() {
		map = new TreeMap<> ();
	}

	public UniqueIndex(final UniqueIndex mi){
		map = new TreeMap<>(mi.map);
	}

	public String[] getKeys( Comparator<String> comp ) {
		String[] result = map.keySet().toArray( new String[0] );
		Arrays.sort( result, comp );
		return result;
	}

	public void put( String key, long value ) {
		map.put(key, value);
	}

	public boolean contains( String key ) {
		return map.containsKey(key);
	}

	public long[] get( String key ) {
		long pos = map.get(key);
		return new long[] { pos };
	}
}