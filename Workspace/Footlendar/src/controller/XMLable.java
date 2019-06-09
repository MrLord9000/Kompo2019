package controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface XMLable
{
	public Element createNode(Document doc);
	public void loadFromDocument(Document doc);
}
