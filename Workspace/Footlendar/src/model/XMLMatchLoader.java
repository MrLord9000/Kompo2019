package model;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.ILoadable;

public class XMLMatchLoader implements ILoadable<Match>
{
	private String path;
	
	public XMLMatchLoader(String path) 
	{
		this.path = path;
	}
	
	public LinkedList<Match> load()
	{
		LinkedList<Match> matches = new LinkedList<>();
		DocumentBuilder docb = null;
		try
		{
			docb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document doc = null;
		try
		{
			doc = docb.parse(new File(path));
		} catch (SAXException | IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		NodeList nodeList = doc.getElementsByTagName("Match");
		for(int i=0; i<nodeList.getLength(); i++)
		{
			Element e = (Element) nodeList.item(i);
			long id = Long.parseLong(e.getElementsByTagName("ID").item(0).getTextContent());
			Element team1 = (Element) e.getElementsByTagName("Team").item(0);
			Element team2 = (Element) e.getElementsByTagName("Team").item(1);
			
			Team home = TeamRepo.getInstance().get(team1.getElementsByTagName("Name").item(0).getTextContent());
			Team away = TeamRepo.getInstance().get(team2.getElementsByTagName("Name").item(0).getTextContent());
			int year = Integer.parseInt(e.getElementsByTagName("Year").item(0).getTextContent());
			int month = Integer.parseInt(e.getElementsByTagName("Month").item(0).getTextContent());
			int day = Integer.parseInt(e.getElementsByTagName("Day").item(0).getTextContent());
			int hour = Integer.parseInt(e.getElementsByTagName("Hour").item(0).getTextContent());
			int minute = Integer.parseInt(e.getElementsByTagName("Minute").item(0).getTextContent());
			String description = e.getElementsByTagName("Description").item(0).getTextContent();
			GregorianCalendar date = new GregorianCalendar(year, month - 1, day, hour, minute);
			Match m = new Match(id, home, away, date, description);
			NodeList scores1 = e.getElementsByTagName("homeGoals");
			NodeList scores2 = e.getElementsByTagName("awayGoals");
			
			if(scores1.getLength() != 0 && scores2.getLength() != 0)
			{
				Integer scoreHome = Integer.parseInt(e.getElementsByTagName("homeGoals").item(0).getTextContent());
				Integer scoreAway = Integer.parseInt(e.getElementsByTagName("awayGoals").item(0).getTextContent());
				Score score = new Score(scoreHome, scoreAway);
				m.setScore(score);
			}
			matches.add(m);	
		}
		
		return matches;
		
	}
}
