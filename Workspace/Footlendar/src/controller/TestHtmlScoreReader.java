package controller;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestHtmlScoreReader implements IHtmlScoreReader
{
	
	private Document doc;
	private File testFile;
	
	public TestHtmlScoreReader() 
	{
		this.testFile = new File(".\\src\\resources\\index.html");
		try
		{
			this.doc = Jsoup.parse(testFile, "UTF-8");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	protected String getTextScore(Match match, String css)
	{
		String score = "";

		Elements elements = doc.body().select(css);
		//System.out.println(elements.toString());
		for(Element e: elements)
		{
			String a = e.text();
			if(a.contains(match.getHome().getName()) && a.contains(match.getAway().getName()))
			{
				//System.out.println();
				score = e.select("b").text();
			}
		}
		//System.out.println(score);
		return score;
	}

	
	@Override
	public Score getScore(Match match)
	{
		//System.out.println(getTextScore(match, "div#mecze > div.mecz"));
		//System.out.println(doc.select("*"));
		
		String [] s = getTextScore(match, "div#mecze > div.mecz").split("-");
		System.out.println(s.length);
		if(s.length > 1 && s != null)
		{
			int score1 = Integer.parseInt(s[0]);
			int score2 = Integer.parseInt(s[1]);
			
			Score score = new Score(score1, score2);
			return score;
		}
		else return null;
	}

}
