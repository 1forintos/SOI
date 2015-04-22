package seatreservation;

public class Seat {
	
	private int row;
	private int column;
	
	private SeatStatus status;
	
	public Seat() {
		status = SeatStatus.free;
	}
	
	public Seat(int row, int column) {
		this.row = row;
		this.column = column;
		this.status = SeatStatus.free;
	}
	
	public void setRow(int newRow) {
		this.row = newRow;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public void setColumn(int newColumn) {
		this.column = newColumn;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public void setStatus(SeatStatus newStatus) {
		this.status = newStatus;
	}
	
	public SeatStatus getStatus() {
		return this.status;
	}
}
