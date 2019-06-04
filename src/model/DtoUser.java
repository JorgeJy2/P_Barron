package model;

public class DtoUser {

	private int id;
	private String name;

	public DtoUser() {
		id = 0;
		name = "";
	}

	public DtoUser(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
