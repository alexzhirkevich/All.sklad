package parking.index;

import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Comparator;

public class MultiIndex implements Serializable, IndexBase {

	private static final long serialVersionUID = 1L;

	private TreeMap<String,long[]> map;

	public MultiIndex() { map = new TreeMap<> (); }

	public MultiIndex(final MultiIndex mi){
		map = new TreeMap<>(mi.map);
	}

	public String[] getKeys( Comparator<String> comp ) {
		String[] result = map.keySet().toArray( new String[0] );
		Arrays.sort( result, comp );
		return result;
	}

	public void put( String key, long value ) {
		long[] arr = map.get(key);
		arr = (arr != null) ? Index.InsertValue( arr, value ) : new long[] {value};
		map.put(key, arr);
	}

	public void put( String[] keys,  long value ) throws NullPointerException {
		if (keys == null)
			throw new NullPointerException("keys is not initialized");
		for (String key : keys) {
			put(key.trim(), value);
		}
	}

	public boolean contains( String key ) {
		return map.containsKey(key);
	}

	public long[] get( String key ) {
		return map.get( key );
	}
}
