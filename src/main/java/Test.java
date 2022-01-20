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
		List<Occupation> occupations = os.findByclient(2);
			System.out.println(cs.findById(2));
	}
}