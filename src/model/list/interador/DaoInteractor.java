package model.list.interador;

import java.util.List;

public class DaoInteractor<Dto> implements Interator<Dto> {

	private List<Dto> list;
	private int index = 0;

	public DaoInteractor(List<Dto> list) {
		this.list = list;
	}

	@Override
	public Dto first() {
		index = 0;
		return list.get(index);
	}

	@Override
	public Dto next() {
		return hasNext() ? list.get(index++) : null;
	}

	@Override
	public boolean hasNext() {
		return index < list.size();
	}

	@Override
	public boolean hasBefore() {
		return index < -1;
	}

	@Override
	public Dto before() {
		return hasBefore() ? list.get(index--) : null;
	}

	@Override
	public int now() {
		return index;
	}

}
