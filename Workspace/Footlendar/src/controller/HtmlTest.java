package controller;

import java.util.GregorianCalendar;

public class HtmlTest
{

	public static void main(String[] args)
	{
		Match m = new Match(9, new Team("Polska U20"), new Team("W³ochy U20"), new GregorianCalendar(), "");
		IHtmlScoreReader r = new TestHtmlScoreReader();
		System.out.println(r.getScore(m));
	}

}
