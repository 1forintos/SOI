package seatreservation.impl;

import seatreservation.Seat;
import seatreservation.SeatStatus;

public class SeatHandler {
	
	private Seat seat;
	private SeatStatus status;
	
	public SeatHandler(Seat seat) {
		this.seat = seat;
		this.status = SeatStatus.FREE;
	}
	
	public Seat getSeat() {
		return seat;
	}
	
	public SeatStatus getStatus() {
		return status;
	}
	
	public void setStatus(SeatStatus status) {
		this.status = status;
	}
}
