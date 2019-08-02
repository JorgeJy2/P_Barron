package model.list.paginator;

import java.sql.SQLException;
import java.util.List;

import dao.DaoInterface;

public class PaginatorDao<Dto> implements Paginator<Dto> {

	private DaoInterface<Dto> dao;
	private int index = 0;
	
	public PaginatorDao(DaoInterface<Dto> dao) {
		this.dao = dao;
	}

	@Override
	public List<Dto> first() throws ClassNotFoundException, SQLException {	
		return dao.getPaginator(0, NUM_PAGINATOR);
	}
	
	@Override
	public List<Dto> next() throws ClassNotFoundException, SQLException {
		List<Dto> next = dao.getPaginator(index, ((index +1) + NUM_PAGINATOR));
		
		if(next.size() > 0) 
			index += next.size();
		
		return next;
	}

}
