package controller;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import model.MatchRepo;
import model.TeamRepo;

public class XMLTest
{

	public static void main(String[] args) throws FileNotFoundException
	{
		TeamRepo.getInstance().load();
		MatchRepo.getInstance().load();
		XMLFileWriter<Match> w = new XMLFileWriter<>(".\\src\\test3.xml");
		try
		{
			w.saveCollection((LinkedList<Match>) MatchRepo.getInstance().getAll());
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XMLMatchLoader tl = new XMLMatchLoader(".\\src\\test3.xml");
		LinkedList<Match> l = tl.load();
		for(Match t: l)
		{
			System.out.println(t.toString());
		}
	}

}
