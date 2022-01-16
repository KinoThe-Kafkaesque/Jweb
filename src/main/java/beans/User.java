package beans;

import java.time.LocalDateTime;

public class User {
	protected int id;
	protected String nom;
	protected String prenom;
	protected String username;
	protected String password;
	protected String recupertation;
	protected LocalDateTime lastLogin;
	protected String role;
	
	
	public User(int id, String nom, String prenom, String username, String password, String recupertation,
			LocalDateTime lastLogin, String role) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.recupertation = recupertation;
		this.lastLogin = lastLogin;
		this.role = role;
	}
	public User(String nom, String prenom, String username, String password, String recupertation,
			LocalDateTime lastLogin, String role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.recupertation = recupertation;
		this.lastLogin = lastLogin;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRecupertation() {
		return recupertation;
	}
	public void setRecupertation(String recupertation) {
		this.recupertation = recupertation;
	}
	public LocalDateTime getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	public String getRole() {
		return role;
	}
	
	
	
}
