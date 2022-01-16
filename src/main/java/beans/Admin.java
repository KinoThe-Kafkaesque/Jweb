package beans;

import java.time.LocalDateTime;

public class Admin extends User {

	public static final String role = "admin";

	public Admin(int id, String nom, String prenom, String username, String password, String recupertation,
			LocalDateTime lastLogin) {
		super(id, nom, prenom, username, password, recupertation, lastLogin, role);
	}

	public Admin(String nom, String prenom, String username, String password, String recupertation,
			LocalDateTime lastLogin) {
		super(nom, prenom, username, password, recupertation, lastLogin, role);
	}


}
