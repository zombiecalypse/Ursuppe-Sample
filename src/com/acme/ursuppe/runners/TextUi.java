package com.acme.ursuppe.runners;

import com.acme.ursuppe.events.TypedObserver;
import com.acme.ursuppe.events.UrsuppeEvent;
import com.acme.ursuppe.model.Game;
import com.google.inject.Inject;

public class TextUi implements TypedObserver<UrsuppeEvent>{
	private Game game;

	@Inject
	public TextUi(Game game) {
		game.registerObserver(this);
		this.game = game;
	}

	public void run() {
		game.play();
	}

	@Override
	public void notify(UrsuppeEvent e) {
		// TODO Auto-generated method stub
	}
}
