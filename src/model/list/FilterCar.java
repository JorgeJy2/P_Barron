package model.list;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import model.dto.DtoCar;

public class FilterCar extends ListCar{

	private String value;
	
	public FilterCar(String value) {
		this.value = value;
		try {
			loadList();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Corran.. :( ");
			System.out.println("Ocurrio un error en filtro");
		}
	}
	
	@Override
	public List<DtoCar> getList() {
		return super.getList().stream().filter((DtoCar car) -> { return car.getPlaca().equals(value);}).collect(Collectors.toList());
	}
}
