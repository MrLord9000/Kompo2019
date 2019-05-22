package model;

import java.util.List;

public interface IRepository<T, G> {
	
	public void load();
	public void save();
	public T get(G id);
	public List<T> getAll();
	public void add(T item);
	public void remove(G id);
}
