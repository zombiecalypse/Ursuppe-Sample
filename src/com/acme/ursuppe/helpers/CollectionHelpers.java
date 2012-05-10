package com.acme.ursuppe.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionHelpers {
	public static <A> List<A> sorted(List<A> l, Comparator<? super A> c) {
		ArrayList<A> lprime = new ArrayList<A>(l);
		Collections.sort(lprime, c);
		return lprime;
	}
	
	public static <A> List<A> list(Collection<A> c) {
		return new ArrayList<A>(c);
	}
	
	public static <A> List<A> reversed(List<A> l) {
		ArrayList<A> lprime = new ArrayList<A>(l);
		Collections.reverse(lprime);
		return lprime;
	}

	public static <K,V> List<V> getAll(Map<K,V> map, Iterable<K> ks) {
		List<V> lst = new ArrayList<V>();
		for (K k : ks) {
			lst.add(map.get(k));
		}
		return lst;
	}
	
	public static <K,V> List<V> getAll(Map<K,V> map, K...ks) {
		return getAll(map, ks);
	}
	
	public static <A> A any(Collection<? extends A> s) {
		assert !s.isEmpty() : "Can't get any from empty set";
		return s.iterator().next();
	}
}
