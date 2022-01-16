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
import beans.Client;
import beans.User;
import connexion.Connexion;
import jakarta.servlet.http.HttpServletRequest;

public class UserService {

	public User login(String user, String pass, HttpServletRequest request) {

		String check = "select status from ip   where adress  = ? ";
		try {
			PreparedStatement p = Connexion.getInstane().getConnection().prepareStatement(check);
			p.setString(1, getClientIpAddr(request));

			ResultSet r = p.executeQuery();

			if (r.next()) {
				if (r.getString(1).equals("banned")) {
					return null;
				}
			}
		} catch (SQLException e) {
			// System.out.println("u.login " + e.getMessage());
			e.printStackTrace();
		}

		String sql = "select u.* , r.* from user u NATURAL JOIN user_role r  where u.username  = ? AND  u.password = ? ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, Hashing.sha256().hashString(pass, StandardCharsets.UTF_8).toString());
			// ps.setString(2, DigestUtils.sha256Hex(pass));
//			ps.setString(2,  pass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String sql1 = "update  user set lastLogin= ?  where username  = ? ";
				PreparedStatement ps1 = Connexion.getInstane().getConnection().prepareStatement(sql1);
				ps1.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
				ps1.setString(2, user);
				ps1.executeUpdate();

				if ("client".equals(rs.getString("r.role"))) {
					Client o = new Client(rs.getInt("u.id"), rs.getString("u.nom"), rs.getString("u.prenom"),
							rs.getString("u.username"), rs.getString("u.password"), rs.getString("u.recuperation"),
							rs.getTimestamp("u.lastLogin").toLocalDateTime());
					logIp(o, request);

					return o;
				}

				if ("admin".equals(rs.getString("r.role"))) {
					Admin o = new Admin(rs.getInt("u.id"), rs.getString("u.nom"), rs.getString("u.prenom"),
							rs.getString("u.username"), rs.getString("u.password"), rs.getString("u.recuperation"),
							rs.getTimestamp("u.lastLogin").toLocalDateTime());
					logIp(o, request);
					return o;
				}
			}

		} catch (SQLException e) {
			// System.out.println("u.login " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}

	public List<User> findAllClients() {

		List<User> clients = new ArrayList<>();
		String sql = "select u.* , r.* from user u NATURAL JOIN user_role r where r.role = ? or r.role = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, Client.role);
			ps.setString(2, "banned");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clients.add(new User(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("username"), rs.getString("password"), rs.getString("recuperation"),
						rs.getTimestamp("lastLogin").toLocalDateTime(), rs.getString("role")));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return clients;
	}

	public List<Client> searchClients(String o) {
		List<Client> clients = new ArrayList<Client>();

		String sql = "select u.* , r.* from user u NATURAL JOIN user_role r  where r.role  = ? AND (u.username  LIKE ?  OR u.nom LIKE ?  OR u.prenom LIKE ?)   ";

		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, Client.role);
			ps.setString(2, "%" + o + "%");
			ps.setString(3, "%" + o + "%");
			ps.setString(4, "%" + o + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clients.add(new Client(rs.getInt("u.id"), rs.getString("u.nom"), rs.getString("u.prenom"),
						rs.getString("u.username"), rs.getString("u.password"), rs.getString("u.recuperation"),
						rs.getTimestamp("u.lastLogin").toLocalDateTime()));
			}

		} catch (SQLException e) {
			System.out.println("searchClients" + e.getMessage());
		}
		return clients;
	}

	public boolean ban(User o) {

		String sql = "update  user_role  set  role = ? where username = ? ";
		String ip = "update  ip  set  status = ?  where id_user= ? ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, "banned");
			ps.setString(2, o.getUsername());
			PreparedStatement ps1 = Connexion.getInstane().getConnection().prepareStatement(ip);
			ps1.setString(1, "banned");
			ps1.setInt(2, o.getId());
			if (ps.executeUpdate() == 1 && ps1.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean unban(User o) {

		String sql = "update  user_role  set  role = ? where username = ? ";
		String ip = "update  ip  set  status = ?  where id_user= ? ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, "client");
			ps.setString(2, o.getUsername());
			PreparedStatement ps1 = Connexion.getInstane().getConnection().prepareStatement(ip);
			ps1.setString(1, "clent");
			ps1.setInt(2, o.getId());
			if (ps.executeUpdate() == 1 && ps1.executeUpdate() == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean logIp(User o, HttpServletRequest request) {
		String ip = getClientIpAddr(request);
		String sql = "select * from ip where id_user = ? and adress =?  ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(2, ip);
			ps.setInt(1, o.getId());
			ResultSet rs = ps.executeQuery();

			if (rs.next() == false) {
				sql = "insert into ip values (null,?, ?, ?)";
				ps = Connexion.getInstane().getConnection().prepareStatement(sql);
				ps.setString(1, ip);
				ps.setInt(2, o.getId());
				ps.setString(3, o.getRole());
				if (ps.executeUpdate() == 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getClientIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		System.out.println(ip);
		return ip;
	}

}
