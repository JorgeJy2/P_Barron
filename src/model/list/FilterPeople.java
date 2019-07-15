package model.list;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import model.dto.DtoPeople;

public class FilterPeople extends ListPeople{

	private String value;
	
	public FilterPeople(String value) {
		this.value = value;
		try {
			loadList();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Corran.. :( ");
			System.out.println("Ocurrio un error en filtro");
		}
	}
	
	@Override
	public List<DtoPeople> getList() {
		return super.getList().stream().filter((DtoPeople people) -> { return people.getName().equals(value);}).collect(Collectors.toList());
	}
}
