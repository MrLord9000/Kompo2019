package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectFileWriter<T extends Serializable>
{
	private String path;
	
	public ObjectFileWriter(String path) 
	{
		this.path = path;
	}
	
	public void save(T obj) throws IOException
	{
		File file = new File(path);
		FileOutputStream outputStream = new FileOutputStream(file);
		ObjectOutputStream objstream = new ObjectOutputStream(outputStream);
		objstream.writeObject(obj);
		objstream.close();
	}
}
