package model.list.paginator;

import java.sql.SQLException;
import java.util.List;

public interface Paginator<Dto>{

	int NUM_PAGINATOR  = 15;

	public List<Dto> first() throws ClassNotFoundException, SQLException ;

	public List<Dto> next() throws ClassNotFoundException, SQLException ;

}
