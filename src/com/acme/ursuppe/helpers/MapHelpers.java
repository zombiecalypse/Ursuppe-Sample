package com.acme.ursuppe.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapHelpers {
	public static <K,V> List<V> getAll(Map<K,V> map, Iterable<K> ks) {
		List<V> lst = new ArrayList<V>();
		for (K k : ks) {
			lst.add(map.get(k));
		}
		return lst;
	}
}
