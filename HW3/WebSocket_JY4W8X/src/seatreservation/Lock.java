package seatreservation;

public class Lock {
	private int row;
	private int column;
	
	private String id;
	
	public Lock(int row, int column) {
		this.row = row;
		this.column = column;
		this.id = "lock_" + row + "_" + column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public String getId() {
		return this.id;
	}
}
