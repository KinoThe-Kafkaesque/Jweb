package beans;

import java.time.LocalTime;

public class Crenon {

	private int id;
	private LocalTime start;
	private LocalTime end;
	public Crenon(int id, LocalTime start, LocalTime end) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
	}
	public Crenon(LocalTime start, LocalTime end) {
		super();
		this.start = start;
		this.end = end;
	}
	public int getId() {
		return id;
	}

	public LocalTime getStart() {
		return start;
	}
	public void setStart(LocalTime start) {
		this.start = start;
	}
	public LocalTime getEnd() {
		return end;
	}
	public void setEnd(LocalTime end) {
		this.end = end;
	}
	
	
}
