package com.acme.ursuppe.model;

import com.acme.ursuppe.types.AmoebaFactory;
import com.acme.ursuppe.types.IAmoeba;
import com.acme.ursuppe.types.IPlayer;

public class StandardAmoebaFactory implements AmoebaFactory {

	@Override
	public IAmoeba make(IPlayer p) {
		return new Amoeba(p);
	}

}
