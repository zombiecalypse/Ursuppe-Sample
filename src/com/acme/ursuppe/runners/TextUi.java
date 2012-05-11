package com.acme.ursuppe.runners;

import com.acme.ursuppe.events.BuyAmoebaAnnouncement;
import com.acme.ursuppe.events.EndOfRoundEvent;
import com.acme.ursuppe.events.PlayerMovedEvent;
import com.acme.ursuppe.events.TypedObserver;
import com.acme.ursuppe.events.UrsuppeEvent;
import com.acme.ursuppe.events.WinnerAnnouncment;
import com.acme.ursuppe.model.Direction;
import com.acme.ursuppe.model.Game;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPlayer;
import com.google.inject.Inject;

public class TextUi implements TypedObserver<UrsuppeEvent>{
	static interface Callback<A extends UrsuppeEvent> {
		void run(A e);
	}
	private Game game;
	private GameViewBuilder gameView;

	@Inject
	public TextUi(Game game, GameViewBuilder view) {
		game.registerObserver(this);
		this.game = game;
		this.gameView = view;
	}

	public void run() {
		game.play();
	}

	@Override
	public void notify(UrsuppeEvent e) {
		if (e instanceof EndOfRoundEvent)
			dispatch((EndOfRoundEvent) e);
		if (e instanceof WinnerAnnouncment)
			dispatch((WinnerAnnouncment) e);
		if (e instanceof PlayerMovedEvent)
			dispatch((PlayerMovedEvent) e);
		if (e instanceof BuyAmoebaAnnouncement)
			dispatch((BuyAmoebaAnnouncement) e);
		
	}
	
	public void dispatch(EndOfRoundEvent e) {
		printGame(e.getGame());
	}
	
	public void dispatch(WinnerAnnouncment e) {
		printWinner(e.getWinner());
	}
	
	public void dispatch(PlayerMovedEvent e) {
		printMove(e.getPlayer(), e.getAmoeba(), e.getDirection());
	}
	
	public void dispatch(BuyAmoebaAnnouncement e) {
		printBuyAmoeba(e.getPlayer(), e.getAmoeba());
	}
	
	public void printGame(Game g) {
		System.out.println(this.gameView.show(g));
	}
	
	public void printWinner(IPlayer p) {
		System.out.println(String.format("%s won the game!", p.getName()));
	}
	
	public void printMove(IPlayer p, IAmoeba a, Direction d) {
		System.out.println(String.format("%s moves amoeba #%d to %s", p.getName(), a.getNumber(), d.toString()));
	}
	
	public void printBuyAmoeba(IPlayer p, IAmoeba a) {
		System.out.println(String.format("%s buys amoeba", p.getName()));
	}
}
