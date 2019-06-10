package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

public class TxtFileLoader <T>
{
	private String path;
	
	public TxtFileLoader(String path) 
	{
		this.path = path;
	}
	
//	public T load()
//	{
//		LinkedList<T> l = new LinkedList<>();
//		try
//		{
//			BufferedReader reader = new BufferedReader(new FileReader(path));
//			//l = reader.
//		} 
//		catch (FileNotFoundException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
