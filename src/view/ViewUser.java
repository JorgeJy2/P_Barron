package view;

import java.sql.SQLException;

import model.dto.DtoUser;
import model.list.ListUser;

public class ViewUser {

	public void viewUser() {
		ListUser listUser = ListUser.geInstance();
		try {
			listUser.loadList();
			// listUser.add(new DTOUser(1,"jejususd2s"));
			System.out.println("SELECT");
			//
			for (DtoUser dtoUser : listUser.getList()) {
				System.out.println(dtoUser.getId() + "\t" + dtoUser.getName());
			}

			DtoUser dtoUserU = listUser.getOne(0);
			dtoUserU.setName("AMANDADYS");

			listUser.update(dtoUserU, 0);

			System.out.println("UPDATE");
			for (DtoUser dtoUser : listUser.getList()) {
				System.out.println(dtoUser.getId() + "\t" + dtoUser.getName());
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}

	}
}
