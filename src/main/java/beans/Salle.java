package beans;

public class Salle {

	private int id;
	private String code;
	private int capacity;
	private String type;
	public Salle(int id, String code, int capacity, String type) {
		super();
		this.id = id;
		this.code = code;
		this.capacity = capacity;
		this.type = type;
	}
	public Salle(String code, int capacity, String type) {
		super();
		this.code = code;
		this.capacity = capacity;
		this.type = type;
	}
	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
