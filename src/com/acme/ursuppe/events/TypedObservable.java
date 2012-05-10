package com.acme.ursuppe.events;

import java.util.Collection;
import java.util.HashSet;

public abstract class TypedObservable<EventType> {
	private Collection<TypedObserver<? super EventType>> observers = new HashSet<TypedObserver<? super EventType>>();
	
	protected void notifyObservers(EventType e) {
		for (TypedObserver<? super EventType> observer : observers) 
			observer.notify(e);
	}

	public void registerObserver(TypedObserver<? super EventType> ob) {
		observers.add(ob);
	}
}
