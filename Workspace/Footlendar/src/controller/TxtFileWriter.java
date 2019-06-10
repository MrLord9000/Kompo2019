package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TxtFileWriter
{
	private String path;
	
	public TxtFileWriter(String path) 
	{
		this.path = path;
	}
	
	public void save(Object obj) throws IOException
	{
		File file = new File(path);
		String textToSave = obj.toString();
		file.createNewFile();
		FileWriter outputStream = new FileWriter(file);
		outputStream.write(textToSave, 0, textToSave.length());
		outputStream.close();
	}
}
