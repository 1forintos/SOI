package seatreservation.impl;

import seatreservation.Lock;
import seatreservation.Seat;

public class LockHandler {
	
	private String id;
	private Lock lock;
	
	public LockHandler(Lock lock) {
		this.lock = lock;
		id = lock.getSeat().getRow() + "_" 
			+ lock.getSeat().getColumn() + "_"
			+ lock.getCount();
	}
	
	public String getId() {
		return id;
	}
	
	public Seat getSeat() {
		return lock.getSeat();
	}
	
	public int getCount() {
		return lock.getCount();
	}
}
