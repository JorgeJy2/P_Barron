package model.list;

import java.util.List;
import java.util.stream.Collectors;
import model.dto.DtoPeople;

public class FilterPeople extends ListPeople{

	private String value;
	
	public FilterPeople(String value) {
		this.value = value;
	}
	
	@Override
	public List<DtoPeople> getList() {
		return super.getList().stream().filter((DtoPeople people) -> { return people.getName().equals(value);}).collect(Collectors.toList());
	}
}
