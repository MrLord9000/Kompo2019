package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ObjectFileLoader<T extends Serializable>
{
	private String path;
	
	public ObjectFileLoader(String path) 
	{
		this.path = path;
	}
	
	public T load() throws ClassNotFoundException, IOException
	{
		FileInputStream fstream = new FileInputStream(new File(path));
		ObjectInputStream objstream = new ObjectInputStream(fstream);
		T obj = (T)objstream.readObject();
		return obj;
	}
}
