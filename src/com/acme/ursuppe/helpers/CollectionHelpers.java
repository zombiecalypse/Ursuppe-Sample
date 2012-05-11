package com.acme.ursuppe.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
			if (map.get(k) == null) continue;
			
			lst.add(map.get(k));
		}
		return lst;
	}
	
	public static <K,V> List<V> getAll(Map<K,V> map, K...ks) {
		ArrayList<K> klist = new ArrayList<K>();
		for (K k : ks) klist.add(k);
		return getAll(map, klist);
	}
	
	public static <A> A any(Collection<? extends A> s) {
		assert !s.isEmpty() : "Can't get any from empty set";
		return s.iterator().next();
	}
	
	public static <A> Collection<A> combine(Collection<? extends A>...cols) {
		ArrayList<Collection<? extends A>> klist = new ArrayList<Collection<? extends A>>();
		for (Collection<? extends A> a : cols) klist.add(a);
		return combine(klist);
	}
	
	public static <A> Collection<A> combine(Collection<? extends Collection<? extends A>> cols) {
		ArrayList<A> list = new ArrayList<A>();
		for (Collection<? extends A> col : cols) {
			list.addAll(col);
		}
		return list;
	}
	
	public static <A> A choose(Collection<? extends A> collection) {
		assert !collection.isEmpty();
		ArrayList<A> list = new ArrayList<A>(collection);
		Collections.shuffle(list);
		return list.get(0);
	}
}
