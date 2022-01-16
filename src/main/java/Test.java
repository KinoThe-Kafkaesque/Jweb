import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import beans.Client;
import beans.Crenon;
import beans.Occupation;
import beans.User;
import service.ClientService;
import service.CrenonService;
import service.OccupationService;
import service.SalleService;
import service.UserService;

public class Test {
	public static void main(String[] args) {
		OccupationService os = new OccupationService();
		ClientService cs = new ClientService();
	//	System.out.println(os.findBySalleAndDate(new SalleService().findById(1), LocalDate.parse("2022-08-31")));
//	System.out.println(cs.findBySalleAndDate(new SalleService().findById(1), LocalDate.parse("2022-01-31")));
	
//	System.out.println(cs.between(LocalTime.parse("02:14"), cs.findById(3), LocalTime.parse("02:14")));
//	System.out.println(cs.findById(3).getStart());

	for (  Client c : cs.findAll() ) {
		System.out.println(c.getRole());
	}
		
	}
}