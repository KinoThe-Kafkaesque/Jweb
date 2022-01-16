package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import beans.Admin;
import beans.Client;
import beans.Crenon;
import beans.Occupation;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class OccupationService implements IDao<Occupation> {

	SalleService ss;
	ClientService cs;
	CrenonService crs;
	AdminService as;

	public boolean create(Occupation o) {
		Salle salle = new SalleService().findById(o.getSalle().getId());
		Client client = new ClientService().findById(o.getClient().getId());
		Crenon crenon = new CrenonService().findById(o.getCrenon().getId());

		if (salle != null && crenon != null && client != null) {
			String sql = "insert into ocuppation values (null,?, ?, ? ,? , null)";
			try {
				PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
				ps.setDate(1, Date.valueOf(o.getDate()));
				ps.setInt(2, o.getSalle().getId());
				ps.setInt(3, o.getCrenon().getId());
				ps.setInt(4, o.getClient().getId());

				if (ps.executeUpdate() == 1) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Occupation o) {
		String sql = "delete from ocuppation where id = ?";
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
	public boolean update(Occupation o) {

		Salle salle = new SalleService().findById(o.getSalle().getId());
		Client client = new ClientService().findById(o.getClient().getId());
		Crenon crenon = new CrenonService().findById(o.getCrenon().getId());

		if (salle != null && crenon != null && client != null) {
			String sql = "update  ocuppation  set  date = ? , id_salle = ? , id_crenon = ? , id_client = ? , id_admin = ? where id = ? ";
			try {
				PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
				ps.setDate(1, Date.valueOf(o.getDate()));
				ps.setInt(2, o.getSalle().getId());
				ps.setInt(3, o.getCrenon().getId());
				ps.setInt(4, o.getClient().getId());
				if (o.getAdmin() != null) {
					ps.setInt(5, o.getAdmin().getId());
				} else {
					ps.setInt(5, 0);
				}
				ps.setInt(6, o.getId());

				if (ps.executeUpdate() == 1) {
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	@Override
	public Occupation findById(int id) {
		String sql = "select * from ocuppation where id  = ?";
		ss = new SalleService();
		cs = new ClientService();
		crs = new CrenonService();
		as = new AdminService();

		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Occupation(rs.getInt("id"), rs.getDate("date").toLocalDate(),
						ss.findById(rs.getInt("id_salle")), crs.findById(rs.getInt("id_crenon")),
						cs.findById(rs.getInt("id_client")), as.findById(rs.getInt("id_admin")));
			}

		} catch (SQLException e) {
			System.out.println("findById " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Occupation> findAll() {
		List<Occupation> occupations = new ArrayList<>();
		ss = new SalleService();
		cs = new ClientService();
		crs = new CrenonService();
		as = new AdminService();
		String sql = "select * from ocuppation";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("date").toLocalDate(),
						ss.findById(rs.getInt("id_salle")), crs.findById(rs.getInt("id_crenon")),
						cs.findById(rs.getInt("id_client")), as.findById(rs.getInt("id_admin"))));
			}
		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		for (Occupation occupation : occupations) {
			if (occupation.getAdmin() == null) {
				String nom = "unverified";
				occupation.setAdmin(new Admin(0, nom, nom, nom, nom, nom, null));
			} else {
				occupation.getAdmin().setPassword("");
				occupation.getAdmin().setRecupertation("");
			}
		}
		return occupations;
	}

	public List<Occupation> findByclient(int id) {
		List<Occupation> occupations = new ArrayList<>();
		ss = new SalleService();
		cs = new ClientService();
		crs = new CrenonService();
		as = new AdminService();
		String sql = "select * from ocuppation where id_client = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("date").toLocalDate(),
						ss.findById(rs.getInt("id_salle")), crs.findById(rs.getInt("id_crenon")),
						cs.findById(rs.getInt("id_client")), as.findById(rs.getInt("id_admin"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Occupation occupation : occupations) {
			if (occupation.getAdmin() == null) {
				String nom = "unverified";
				occupation.setAdmin(new Admin(0, nom, nom, nom, nom, nom, null));
			} else {
				occupation.getAdmin().setPassword("");
				occupation.getAdmin().setRecupertation("");
			}
		}
		return occupations;
	}

	public List<Occupation> findBySalleAndDate(Salle salle, LocalDate date) {
		List<Occupation> occupations = new ArrayList<>();
		ss = new SalleService();
		cs = new ClientService();
		crs = new CrenonService();
		as = new AdminService();
		String sql = "select * from ocuppation  where id_salle = ?  and date = ? ";

		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setDate(2, Date.valueOf(date));
			ps.setInt(1, salle.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), rs.getDate("date").toLocalDate(),
						ss.findById(rs.getInt("id_salle")), crs.findById(rs.getInt("id_crenon")),
						cs.findById(rs.getInt("id_client")), as.findById(rs.getInt("id_admin"))));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		for (Occupation occupation : occupations) {
			if (occupation.getAdmin() == null) {
				String nom = "unverified";
				occupation.setAdmin(new Admin(0, nom, nom, nom, nom, nom, null));
			} else {
				occupation.getAdmin().setPassword("");
				occupation.getAdmin().setRecupertation("");
			}
		}
		return occupations;
	}

	public Map<String, Integer> mostReserved() {
		Map<String, Integer> map = new LinkedHashMap<>();
		ss = new SalleService();
		String sql = "SELECT  id_salle ,count(*) " + "FROM ocuppation  " + "GROUP BY  id_salle "
				+ "ORDER BY count(*) DESC";

		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				map.put(ss.findById(rs.getInt("id_salle")).getCode(), rs.getInt("count(*)"));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}

		return map;
	}

	public Map<LocalDate, Integer> monthly() {
		Map<LocalDate, Integer> map = new LinkedHashMap<>();

		String sql = "SELECT    YEAR (date),    MONTH(date),    COUNT(*)  FROM    ocuppation  WHERE    ( date BETWEEN DATE_SUB( CURRENT_DATE() , INTERVAL 1 YEAR) AND CURRENT_DATE() )  GROUP BY    YEAR (date),    MONTH(date)  ORDER BY    YEAR (date),    MONTH(date) DESC ";

		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				map.put( LocalDate.of(rs.getInt(1), rs.getInt(2) , 0) , rs.getInt("count(*)"));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}

		return map;
	}

	public List<Occupation> searchClients(String o) {
		List<Occupation> occupations = new ArrayList<Occupation>();

		String sql = "select u.* , o.* from user u NATURAL JOIN  ocuppation o  where  (o.salle  LIKE ?  OR u.nom LIKE ?  OR u.prenom LIKE ? )   ";

		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, "%" + o + "%");
			ps.setString(2, "%" + o + "%");
			ps.setString(3, "%" + o + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("o.id"), rs.getDate("o.date").toLocalDate(),
						ss.findById(rs.getInt("o.id_salle")), crs.findById(rs.getInt("o.id_crenon")),
						cs.findById(rs.getInt("o.id_client")), as.findById(rs.getInt("o.id_admin"))));
			}

		} catch (SQLException e) {
			System.out.println("searchClients" + e.getMessage());
		}
		for (Occupation occupation : occupations) {
			if (occupation.getAdmin() == null) {
				String nom = "unverified";
				occupation.setAdmin(new Admin(0, nom, nom, nom, nom, nom, null));
			} else {
				occupation.getAdmin().setPassword("");
				occupation.getAdmin().setRecupertation("");
			}
		}

		return occupations;
	}

	public boolean verify(int id, Admin admin) {
		Occupation o = findById(id);
		o.setAdmin(admin);
		return update(o);

	}

	public boolean unverify(int id) {
		String nom = "unverified";
		Occupation o = findById(id);
		o.setAdmin(new Admin(0, nom, nom, nom, nom, nom, null));
		return update(o);

	}
}
