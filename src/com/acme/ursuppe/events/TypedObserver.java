package com.acme.ursuppe.events;

public interface TypedObserver<EventType> {
	void notify(EventType e);
}
