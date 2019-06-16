package controller;

import java.awt.Image;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Team class for storing team data
 * @author Adrian Zieliñski
 *
 */
public class Team implements XMLable{

	private String name;
	private Image logo;
	
	/**
	 * Class constructor.
	 * @param name	Team name
	 * @param logo	Team logo
	 */
	public Team(String name, Image logo) 
	{
		this.name = name;
		this.logo = logo;
	}

	/**
	 * Class constructor.
	 * @param name	Team name
	 */
	public Team(String name) 
	{
		this.name = name;
		this.logo = null;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public Image getLogo() 
	{
		return logo;
	}
	
	public void setLogo(Image logo) 
	{
		this.logo = logo;
	}
	
	@Override
	public String toString()
	{
		return name + "\n";
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj == null) return false;
		
		Team t = (Team) obj;
		if(this.name.equals(t.name)) return true;
		else return false;
	}

	@Override
	public Element createNode(Document doc)
	{
		Element team = doc.createElement("Team");
		Element name = doc.createElement("Name");
		name.appendChild(doc.createTextNode(this.name));
		team.appendChild(name);
		return team;
	}

	@Override
	public void loadFromDocument(Document doc)
	{
		
		
	}
}