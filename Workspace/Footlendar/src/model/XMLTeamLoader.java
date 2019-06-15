package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.Team;

public class XMLTeamLoader
{
	private String path;
	
	public XMLTeamLoader(String path) 
	{
		this.path = path;
	}
	
	public LinkedList<Team> load()
	{
		LinkedList<Team> teams = new LinkedList<>();
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
		
		
		NodeList nodeList = doc.getElementsByTagName("Team");
		for(int i=0; i<nodeList.getLength(); i++)
		{
			Element e = (Element) nodeList.item(i);
			//System.out.println(e.toString());
			teams.add(new Team(e.getElementsByTagName("Name").item(0).getTextContent()));	
		}
		
		return teams;
		
	}
}
