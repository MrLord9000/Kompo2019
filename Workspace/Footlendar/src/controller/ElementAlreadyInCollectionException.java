package controller;

/**
 * Excpetion for handling duplicate elements in collection
 * @author Filip Mazurek
 *
 */
public class ElementAlreadyInCollectionException extends Exception
{
	public ElementAlreadyInCollectionException(String msg)
	{
		super(msg);
	}
}
