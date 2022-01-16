package beans;

import java.time.LocalDate;

public class Occupation {
	private int id;
	private LocalDate date;
	private Salle salle;
	private Crenon crenon;
	private Client client;
	private Admin admin;
	
	public Occupation(int id, LocalDate date, Salle salle, Crenon crenon, Client client, Admin admin) {
		super();
		this.id = id;
		this.date = date;
		this.salle = salle;
		this.crenon = crenon;
		this.client = client;
		this.admin = admin;
	}
	
	public Occupation(LocalDate date, Salle salle, Crenon crenon, Client client, Admin admin) {
		super();
		this.date = date;
		this.salle = salle;
		this.crenon = crenon;
		this.client = client;
		this.admin = admin;
	}
	public int getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public Crenon getCrenon() {
		return crenon;
	}
	public void setCrenon(Crenon crenon) {
		this.crenon = crenon;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return " admin=" + admin + "]";
	}
	
	
}
