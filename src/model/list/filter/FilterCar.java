package model.list.filter;

import java.util.List;
import java.util.stream.Collectors;

import model.dto.DtoCar;
import model.list.ListCar;

public class FilterCar extends ListCar{

	private String value;
	
	public FilterCar(String value) {
		this.value = value;
	}
	
	@Override
	public List<DtoCar> getList() {
		return super.getList().stream().filter((DtoCar car) -> { return car.getPlaca().equals(value);}).collect(Collectors.toList());
	}
}
