package com.acme.ursuppe.helpers;

import java.util.Iterator;
import java.util.Scanner;

public class LineIterator implements Iterator<String>, Iterable<String> {
	final Scanner scanner;
	
	public LineIterator(String s) {
		scanner = new Scanner(s);
		scanner.useDelimiter("\n");
	}

	@Override
	public boolean hasNext() {
		return scanner.hasNext();
	}

	@Override
	public String next() {
		return scanner.nextLine();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<String> iterator() {
		return this;
	}

}
