package controller;

import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.DOMException;

public interface ISaveable<T>
{
	public void save(T m) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException;
	public void update(T m) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException;
	public void saveCollection(LinkedList<T> col) throws DOMException, IllegalArgumentException, TransformerException, ParserConfigurationException, TransformerFactoryConfigurationError;
	public void deleteBefore(GregorianCalendar cal);
}
