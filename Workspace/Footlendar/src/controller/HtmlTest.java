package controller;

import java.util.GregorianCalendar;

public class HtmlTest
{

	public static void main(String[] args)
	{
		Match m = new Match(9, new Team("Argentyna U20"), new Team("Mali U20"), new GregorianCalendar(), "");
		IHtmlScoreReader r = new TestHtmlScoreReader();
		System.out.println(r.getScore(m));
	}

}
