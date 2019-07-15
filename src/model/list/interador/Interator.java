package model.list.interador;

public interface Interator<Dto> {

	public Dto first();

	public Dto next();

	public boolean hasNext();

	public boolean hasBefore();

	public Dto before();

	public int now();
}
