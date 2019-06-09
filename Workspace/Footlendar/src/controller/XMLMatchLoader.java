package controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.TeamRepo;

public class XMLMatchLoader
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
			//Team home = TeamRepo.getInstance().get(e.getElementsByTagName("Team").item(0).);
			//Team away = TeamRepo.getInstance().get(e.getElementsByTagName("away").item(0).getTextContent());
			//matches.add(new Team(e.getElementsByTagName("Name").item(0).getTextContent()));	
		}
		
		return matches;
		
	}
}
