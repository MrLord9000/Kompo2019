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
	
	
	
	
}
