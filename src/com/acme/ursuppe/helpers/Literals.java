package com.acme.ursuppe.helpers;

import java.util.HashMap;
import java.util.Map;

public class Literals {
	public static class MapBuilder<K,V> {
		Map<K,V> store = new HashMap<K,V>();
		public MapBuilder<K,V> and(K key, V val) {
			store.put(key, val);
			return this;
		}
		
		public Map<K,V> done() {
			return store;
		}
	}
	
	public static <K,V> MapBuilder<K,V> map(K k, V v) {
		return (new MapBuilder<K,V>()).and(k,v);
	}
}
