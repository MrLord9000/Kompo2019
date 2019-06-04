package controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FlashScoreHtmlScoreReader implements IHtmlScoreReader
{

	//private String url;
	private Document doc;
	
	
	public FlashScoreHtmlScoreReader(String url) 
	{
		//this.url = url;
		try
		{
			doc = Jsoup.connect(url).get();
			//System.out.println(doc.toString());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getTextScore(Match match)
	{
		String score = null;
		String css = "div#wyniki > div.league_box > div > div.n > a";
		//String css = "tr.odd.stage-finish";
		System.out.println(css);
		Elements elements = doc.body().select(css);
		for(Element e: elements)
		{
			String a = e.text();
			if(a.contains(match.getHome().getName()) && a.contains(match.getAway().getName()))
			{
				System.out.println();
				score = e.select("b").text();
			}
		}
		
		
		return score;
	}
	
	@Override
	public Score getScore(Match match)
	{
		String [] s = getTextScore(match).split("-");
		int score1 = Integer.parseInt(s[0]);
		int score2 = Integer.parseInt(s[1]);
		
		Score score = new Score(score1, score2);
		return score;
	}

}
