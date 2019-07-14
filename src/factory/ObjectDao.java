package factory;

import dao.DaoInterface;

public interface ObjectDao {
	public DaoInterface<?> crearDao();
}
