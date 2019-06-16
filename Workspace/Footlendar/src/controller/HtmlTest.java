package controller;

import java.util.GregorianCalendar;

import model.Match;
import model.Team;

public class HtmlTest
{

	public static void main(String[] args)
	{
		Match m = new Match(9, new Team("Polska U20"), new Team("Włochy U20"), new GregorianCalendar(), "");
		IHtmlScoreReader r = new TestHtmlScoreReader();
		System.out.println(r.getScore(m));
	}

}
