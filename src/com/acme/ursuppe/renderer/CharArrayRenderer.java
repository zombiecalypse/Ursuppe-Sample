package com.acme.ursuppe.renderer;

import com.acme.ursuppe.helpers.LineIterator;

public class CharArrayRenderer {
	private final char[][] canvas;
	final int width;
	final int height;

	public CharArrayRenderer(int x, int y) {
		this.canvas = new char[y][x];
		this.width = x;
		this.height = y;
		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++) 
				canvas[i][j] = ' ';
	}
	
	public void draw(final int x, final int y, String s) {
		int xx = x;
		int yy = y;
		for (String line : new LineIterator(s)) {
			for (char c : line.toCharArray()) {
				canvas[yy][xx++] = c;
			}
			yy++;
			xx = x;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (char[] line : canvas) {
			for (char c : line) {
				builder.append(c);
			}
			builder.append('\n');
		}
		return builder.toString();
	}
}
