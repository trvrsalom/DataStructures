import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class YoHashMap_Salom {

	public static void main(String[] args) {
		YoHashMap map = new YoHashMap<Integer>();
		String[] keys = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		Integer[] vals = {0,1,2,3,4,5,6,7,8,9};
		for(Integer i : vals) map.put(keys[i], i);
		for(String key : keys) System.out.println(key + " : " + map.get(key));
	}

}

class YoHashMap<T> {
	private int size = 0;
	private final static int MAP_LENGTH = 10;
	ArrayList<LinkedList<HashPair> > table = new ArrayList<LinkedList<HashPair> >(MAP_LENGTH);

	public YoHashMap() {
		for (int i = 0; i < MAP_LENGTH; i++) table.add(null);
	}

	private class HashPair {
		public String key;
		public T value;
		public HashPair(String key, T value) {
			this.key = key;
			this.value = value;
		}

	}

	public T put(String key, T value) {
		HashPair newPair = new HashPair(key, value);
		int location = hash(key) % MAP_LENGTH;
		if (table.get(location) == null)
			table.set(location, new LinkedList<HashPair>());
		for (HashPair h : table.get(location))
			if (h.key == key) {
				T old = h.value;
				h.value = value;
				return old;
			}
		size++;
		table.get(location).add(newPair);
		return null;
	}

	public T get(String key) {
		if (table.get(hash(key) % MAP_LENGTH) != null) {
			for (HashPair h : table.get(hash(key) % MAP_LENGTH))
				if (h.key == key) return h.value;
			return null;
		}
		else return null;
	}

	private int hash(String toHash) {
		return toHash.length();
	}

	public int size() {
		return size;
	}

	public boolean containsKey(String key) {
		if (table.get(hash(key) % MAP_LENGTH) != null) {
			for (HashPair h : table.get(hash(key) % MAP_LENGTH))
				if (h.key == key) return true;
			return false;
		}
		else return false;
	}

	public Set keySet() {
		Set<String> keys = new HashSet<String>();
		for (LinkedList<HashPair> ll : table)
			if (ll != null)
				for (HashPair pair : ll)
					keys.add(pair.key);
		return keys;
	}

	public T remove(String key) {
		int location = hash(key) % 10;
		if (table.get(hash(key) % MAP_LENGTH) != null) {
			int i = 0;
			for (HashPair h : table.get(hash(key) % MAP_LENGTH))
				if (h.key == key) {
					T old = h.value;
					table.get(hash(key) % MAP_LENGTH).remove(i);
					size--;
					return old;
				}
			i++;
			return null;
		} else {
			return null;
		}
	}

}
