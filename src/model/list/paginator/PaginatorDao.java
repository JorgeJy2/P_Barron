package model.list.paginator;

import java.sql.SQLException;
import java.util.List;

import dao.DaoInterface;

public class PaginatorDao<Dto> implements Paginator<Dto> {

	private  DaoInterface<Dto> dao;
	private int index = 0;
	private static final int NUM_PAGINATOR  = 15;

	public PaginatorDao(DaoInterface<Dto> dao) {
		this.dao = dao;
	}

	@Override
	public List<Dto> first() throws ClassNotFoundException, SQLException {	
		return dao.getPaginator(0, NUM_PAGINATOR);
	}
	
	@Override
	public List<Dto> next() throws ClassNotFoundException, SQLException {
		System.out.println("optener primeros.. paginator..");
		System.out.println("inicial :"+index * NUM_PAGINATOR);
		System.out.println("final :"+ (((index +1)* NUM_PAGINATOR)));
		return dao.getPaginator(index * NUM_PAGINATOR, (((index++) +1)* NUM_PAGINATOR));
	}

	@Override
	public int now() {
		return index;
	}

}
