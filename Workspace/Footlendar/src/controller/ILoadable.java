package controller;

import java.util.LinkedList;

public interface ILoadable<T>
{
	public LinkedList<T> load();
	
}
