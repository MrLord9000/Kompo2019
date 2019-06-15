package model;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import controller.ISaveable;
import controller.XMLable;

public class XMLFileWriter<T extends XMLable> implements ISaveable<T>
{
	private String path;
	
	public XMLFileWriter(String path) 
	{
		this.path = path;
	}
	
	public void save(T obj) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException
	{
		DocumentBuilder docb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docb.newDocument();
		
		Element root = obj.createNode(doc);
		doc.appendChild(root);
		
		DOMSource source = new DOMSource(doc);
		StreamResult file = new StreamResult(new File(path)); 
		Transformer transf = TransformerFactory.newInstance().newTransformer();
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transf.transform(source, file);
	}
	
	public void saveCollection(LinkedList<T> obj) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException
	{
		DocumentBuilder docb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = docb.newDocument();
		Element root = doc.createElement("root");
		for(T t: obj)
		{
			Element e = t.createNode(doc);
			root.appendChild(e);
		}
		
		//Element root = obj.createNode(doc);
		doc.appendChild(root);
		
		DOMSource source = new DOMSource(doc);
		StreamResult file = new StreamResult(new File(path)); 
		Transformer transf = TransformerFactory.newInstance().newTransformer();
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transf.setOutputProperty(OutputKeys.INDENT, "yes");
        transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transf.transform(source, file);
	}

	@Override
	public void update(T m)
			throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException
	{
		// TODO Auto-generated method stub
		
	}




}
