package model.list.paginator;

import java.sql.SQLException;
import java.util.List;

public interface Paginator<Dto>{

	public List<Dto> first() throws ClassNotFoundException, SQLException ;

	public List<Dto> next() throws ClassNotFoundException, SQLException ;

	public int now();
}
