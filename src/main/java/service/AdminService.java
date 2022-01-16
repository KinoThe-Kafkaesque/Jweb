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

import beans.Admin;
import connexion.Connexion;
import dao.IDao;

public class AdminService implements IDao<Admin> {
	@Override
	public boolean create(Admin o) {
		String sql = "insert into user values (null,?, ? , ? ,? ,? , ? )";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getUsername());
			ps.setString(4,Hashing.sha256().hashString(o.getPassword(), StandardCharsets.UTF_8).toString());
			ps.setString(5, Hashing.sha256().hashString(o.getRecupertation(), StandardCharsets.UTF_8).toString());
			ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));

			if (ps.executeUpdate() == 1) {
				String sql2 = "insert into user_role values (?,?)";
				PreparedStatement ps2 = Connexion.getInstane().getConnection().prepareStatement(sql2);
				ps.setString(1, o.getUsername());
				ps.setString(2, Admin.role);
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
	public boolean delete(Admin o) {
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
	public boolean update(Admin o) {
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
	public Admin findById(int id) {
		if (id != 0) {
			String sql = "select * from user where id  = ?" ;
			try {
				PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
							rs.getString("username"), rs.getString("password"), rs.getString("recuperation"),
							rs.getTimestamp("lastLogin").toLocalDateTime());
				}

			} catch (SQLException e) {
				System.out.println("findById " + e.getMessage());
			}
		}
	
		return null;
	}

	@Override
	public List<Admin> findAll() {
		List<Admin> admins = new ArrayList<>();

		String sql = "select * from user where user_role  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, Admin.role);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				admins.add(new Admin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("username"), rs.getString("password"), rs.getString("recuperation"),
						rs.getTimestamp("lastLogin").toLocalDateTime()) );
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return admins;
	}
	

}
