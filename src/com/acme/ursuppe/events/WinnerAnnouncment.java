package com.acme.ursuppe.events;

import com.acme.ursuppe.types.IPlayer;

public class WinnerAnnouncment extends UrsuppeEvent {
	private final IPlayer winner;
	
	public WinnerAnnouncment(IPlayer winner) {
		this.winner = winner;
	}

	public IPlayer getWinner() {
		return winner;
	}

}
