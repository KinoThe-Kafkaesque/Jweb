package service;

import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

import beans.Client;
import beans.User;
import connexion.Connexion;
import dao.IDao;

public class ClientService implements IDao<Client> {

	@Override
	public boolean create(Client o) {
		String sql = "insert into user values (null,?, ? , ? ,? ,? , ? )";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getUsername());
			ps.setString(4, Hashing.sha256().hashString(o.getPassword(), StandardCharsets.UTF_8).toString());
			ps.setString(5, Hashing.sha256().hashString(o.getRecupertation(), StandardCharsets.UTF_8).toString());
			ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

			if (ps.executeUpdate() == 1) {
				String sql2 = "insert into user_role values (?,?)";
				PreparedStatement ps2 = Connexion.getInstane().getConnection().prepareStatement(sql2);
				ps2.setString(1, o.getUsername());
				ps2.setString(2, Client.role);
				if (ps2.executeUpdate() == 1) {
					return true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean delete(Client o) {
		String sql = "delete from user where id = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, o.getId());

			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Client o) {
		String sql = "update  user  set  nom = ? , prenom = ? ,  username = ? , password = ? , recuperation = ? , lastLogin = ? where id = ?  ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getUsername());
			ps.setString(4, Hashing.sha256().hashString(o.getPassword(), StandardCharsets.UTF_8).toString());
			ps.setString(5, Hashing.sha256().hashString(o.getRecupertation(), StandardCharsets.UTF_8).toString());
			ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			ps.setInt(7, o.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Client findById(int id) {
		String sql = "select * from user where id = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("username"), rs.getString("password"), rs.getString("recuperation"),
						rs.getTimestamp("lastLogin").toLocalDateTime());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<>();
		String sql = "select u.* , r.* from user u NATURAL JOIN user_role r where r.role = ?  ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, Client.role);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clients.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("username"), rs.getString("password"), rs.getString("recuperation"),
						rs.getTimestamp("lastLogin").toLocalDateTime()));
			}
		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return clients;
	}
	public User findByUser(String user) {
		String sql = "select u.* , r.* from user u NATURAL JOIN user_role r where u.username = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("u.id"), rs.getString("u.nom"), rs.getString("u.prenom"),
						rs.getString("u.username"), rs.getString("u.password"), rs.getString("u.recuperation"),
						rs.getTimestamp("u.lastLogin").toLocalDateTime(), rs.getString("r.role"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changePass(User o) {
		String sql = "update  user  set   password = ?  where id = ?  ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, Hashing.sha256().hashString(o.getPassword(), StandardCharsets.UTF_8).toString());
			ps.setInt(2 , o.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
