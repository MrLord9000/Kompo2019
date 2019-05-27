

import java.util.Calendar;
import java.util.GregorianCalendar;

import controller.ConsoleNotifier;
import controller.Match;
import controller.Team;
import controller.User;

public class Main {

	public static void main(String[] args) {
		System.out.println("xD");
		User.getInstance().setNotifier(new ConsoleNotifier());
		Match m = new Match(1, new Team("t1"), new Team("t1"), new GregorianCalendar(2019, Calendar.MAY, 26, 16, 50, 30), "jakis opis");
		Match.setminutesRemindBeforeStart(1);
		m.resetTimer();
		User.getInstance().addTrackedMatch(m);
		//Team t = new Team("");
	}

}
