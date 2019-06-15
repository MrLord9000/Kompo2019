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


 	protected String getTextScore(Match match)
	{
		String score = "";

 		Elements elements = doc.select("div.mecz");
		//System.out.println(elements.toString());
		for(Element e: elements)
		{
			String a = e.text();
			//System.out.println(a + "\n");
			if(a.contains(match.getHome().getName()) && a.contains(match.getAway().getName()))
			{
				
				score = e.select("b").text();
				//return score;
			}
		}
		//System.out.println(score);
		return score;
	}


 	@Override
	public Score getScore(Match match)
	{
		//System.out.println(getTextScore(match, "div#mecze > div.mecz"));
		//System.out.println(doc.select("div#container > div#mecze "));

 		String [] s = getTextScore(match).split("-");
//		for(String g: s)
//		{
//			System.out.println(g);
//		}
		if(s!= null &&  s.length > 1)
		{
			int score1 = Integer.parseInt(s[0]);
			int score2 = Integer.parseInt(s[1]);

 			Score score = new Score(score1, score2);
			return score;
		}
		else return null;
 	}

}
