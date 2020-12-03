package parking.index;


import java.io.Serializable;
import java.util.*;

public class UniqueIndex implements Serializable, IndexBase {

	private static final long serialVersionUID = 1L;

	private TreeMap<String,Long> map;

	public UniqueIndex() {
		map = new TreeMap<> ();
	}

	public UniqueIndex(final UniqueIndex mi){
		map = new TreeMap<>(mi.map);
	}

	public TreeSet<String> getKeys(Comparator<String> comp ) {
		TreeSet<String> result = new TreeSet<String>(comp);
		result.addAll(map.keySet());
		return result;
	}

	public void put( String key, Long value ) {
		map.put(key, value);
	}

	public boolean contains( String key ) {
		return map.containsKey(key);
	}

	public Vector<Long> get(String key ) {
		Vector<Long> v = new Vector<Long>();
		v.add(map.get(key));
		return v;
	}
}