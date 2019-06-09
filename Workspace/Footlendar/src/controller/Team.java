package controller;

import java.awt.Image;

public class Team {

	private String name;
	private Image logo;
	
	
	
	public Team(String name, Image logo) 
	{
		this.name = name;
		this.logo = logo;
	}
	
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
	
	
	
}