package dao;

import java.sql.SQLException;

import java.util.List;

public interface DaoInterface<Model> {

	Object add(Model dto) throws SQLException, ClassNotFoundException;

	boolean update(Model dto) throws SQLException, ClassNotFoundException;

	boolean delete(Model dto) throws SQLException, ClassNotFoundException;

	Model get(Object key) throws SQLException, ClassNotFoundException;

	List<Model> getAll() throws SQLException, ClassNotFoundException;

}
