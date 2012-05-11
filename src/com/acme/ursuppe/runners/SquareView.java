package com.acme.ursuppe.runners;

import java.util.List;

import com.acme.ursuppe.types.Color;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.ISquare;

import static com.acme.ursuppe.helpers.CollectionHelpers.*;

public class SquareView {
	public static final String template = 
			"+-----------+\n" +
			"| r:%02d b:%02d |\n" +
			"| y:%02d      |\n" +
			"| [%7s] |\n" +
			"| [%7s] |\n" +
			"+-----------+";
//	"+-----------+\n" +
//	"| r:dd b:dd |\n" +
//	"| y:dd      |\n" +
//	"| [aaaaaaa] |\n" +
//	"| [aaaaaaa] |\n" +
//	"+-----------+";
	public final int WIDTH = 12;
	public final int HEIGHT = 5;
	private final ISquare square;
	
	public SquareView(ISquare square) {
		this.square = square;
	}
	
	public String show() {
		List<IAmoeba> amoebas = list(square.getAmoebas());
		return String.format(template, 
				square.getFoodstuff().get(Color.RED),
				square.getFoodstuff().get(Color.BLUE),
				square.getFoodstuff().get(Color.YELLOW),
				amoebaLine(amoebas),
				amoebaLine(amoebas));
	}
	
	private String amoebaLine(List<IAmoeba> amoebas) {
		StringBuilder builder = new StringBuilder();
		for (IAmoeba a : list(amoebas)) {
			if (builder.length() > 5) break;
			builder.append(a.getColor().getChar());
			amoebas.remove(a);
		}
		
		return builder.toString();
	}
}
