package com.acme.ursuppe.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListHelpers {
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
}
