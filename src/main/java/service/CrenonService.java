package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import beans.Crenon;
import beans.Occupation;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class CrenonService implements IDao<Crenon> {

	public boolean create(Crenon o) {

		String sql = "insert into crenon values (null,?, ?)";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setTime(1, Time.valueOf(o.getStart()));
			ps.setTime(2, Time.valueOf(o.getEnd()));
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Crenon o) {
		String sql = "delete from crenon  where  id = ?";
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
	public boolean update(Crenon o) {
		String sql = "update  crenon  set  start = ? , end = ? where id = ? ";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setTime(1, Time.valueOf(o.getStart()));
			ps.setTime(2, Time.valueOf(o.getEnd()));
			ps.setInt(3, o.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Crenon findById(int id) {
		String sql = "select * from crenon where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Crenon(rs.getInt("id"), rs.getTime("start").toLocalTime(), rs.getTime("end").toLocalTime());
			}

		} catch (SQLException e) {
			System.out.println("findById " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Crenon> findAll() {
		List<Crenon> crenons = new ArrayList<>();

		String sql = "select * from crenon";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				crenons.add(new Crenon(rs.getInt("id"), rs.getTime("start").toLocalTime(),
						rs.getTime("end").toLocalTime()));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		Collections.sort(crenons, (o1, o2) -> {
			if (o1.getStart().isBefore(o2.getStart()))
				return 1;
			else if (!o1.getStart().isBefore(o2.getStart())) {
				return -1;
			} else {
				return 0;
			}

		});
		return crenons;
	}

	public List<Crenon> findBySalleAndDate(Salle salle, LocalDate date) {
		List<Occupation> occupations = new OccupationService().findBySalleAndDate(salle, date);
		List<Crenon> crenons = findAll();

		for (Iterator<Occupation> iterator = occupations.iterator(); iterator.hasNext();) {
			Occupation o = iterator.next();
			if (o.getAdmin().getId() != 0) {
				for (Iterator<Crenon> it = crenons.iterator(); it.hasNext();) {
					Crenon crenon = it.next();
						System.out.println(between(o.getCrenon().getStart(), crenon, o.getCrenon().getEnd()));
					if (between(o.getCrenon().getStart(), crenon, o.getCrenon().getEnd())) {
						it.remove();
					}
				}
			}
		}

		Collections.sort(crenons, (o1, o2) -> {
			if (o1.getStart().isBefore(o2.getStart()))
				return 1;
			else if (!o1.getStart().isBefore(o2.getStart())) {
				return -1;
			} else {
				return 0;
			}

		});
		return crenons;
	}

	public boolean between(LocalTime start, Crenon crenon, LocalTime end) {

//		return ((crenon.getStart().isAfter(start) && crenon.getEnd().isBefore(end))
//				|| (crenon.getEnd().isAfter(start) && crenon.getStart().isBefore(start))
//				|| (crenon.getStart().isBefore(end)) && crenon.getEnd().isAfter(end)
//				|| (crenon.getStart().isBefore(start) && crenon.getEnd().isAfter(end)));

		return !(crenon.getEnd().isBefore(start) || crenon.getStart().isAfter(end));

		// if true then the time span is somewhere in between start and end
	}

}
