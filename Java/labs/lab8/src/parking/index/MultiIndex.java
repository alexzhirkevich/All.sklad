package parking.index;

import java.io.Serializable;
import java.util.*;

public class MultiIndex implements Serializable, IndexBase {

	private static final long serialVersionUID = 1L;

	private TreeMap<String,Vector<Long>> map;

	public MultiIndex() { map = new TreeMap<> (); }

	public MultiIndex(final MultiIndex mi){
		map = new TreeMap<>(mi.map);
	}

	public TreeSet<String> getKeys(Comparator<String> comp ) {
		TreeSet<String> result = new TreeSet<String>(comp);
		result.addAll(map.keySet());
		return result;
	}

	public void put( String key, Long value ) {
		Vector<Long> arr = map.get(key);
		if (arr!= null)
			arr.add(value);
		else {
			arr = new Vector<>();
			arr.add(value);
		}
		map.put(key, arr);
	}

	public boolean contains( String key ) {
		return map.containsKey(key);
	}

	public Vector<Long> get( String key ) {
		return new Vector<>(map.get(key));
	}
}
