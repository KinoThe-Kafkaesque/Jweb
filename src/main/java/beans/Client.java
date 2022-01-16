package beans;

import java.time.LocalDateTime;

public class Client extends User {

	public static final String role = "client";

	public Client(int id, String nom, String prenom, String username, String password, String recupertation,
			LocalDateTime lastLogin) {
		super(id, nom, prenom, username, password, recupertation, lastLogin, role);
	}

	public Client(String nom, String prenom, String username, String password, String recupertation,
			LocalDateTime lastLogin) {
		super(nom, prenom, username, password, recupertation, lastLogin, role);
	}

	@Override
	public String getRole() {
		return role;
	}

}
