package model.list.filter;

import java.util.List;
import java.util.stream.Collectors;
import model.dto.DtoPeople;
import model.list.ListPeople;

/**
 * Archivo: FilterPeople.java contiene la definición de la clase FilterPeople
 * que extiende de ListPeople.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class FilterPeople extends ListPeople {
	// declaración de atributo
	private String value;

	/**
	 * Constructor con parámetro
	 * 
	 * @param value valor de tipo String
	 */
	public FilterPeople(String value) {
		this.value = value;
	}// cierre constructor

	/**
	 * Método getList
	 * 
	 * @return retorna un objeto de tipo lista.
	 */
	@Override
	public List<DtoPeople> getList() {
		return super.getList().stream().filter((DtoPeople people) -> {
			return people.getName().equals(value);
		}).collect(Collectors.toList());
	}// cierre método getList
}// cierre clase FilterPeople
