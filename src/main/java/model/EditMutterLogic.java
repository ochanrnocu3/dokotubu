package model;

import dao.MuttersDAO;

public class EditMutterLogic {
	public void execute(Mutter mutter) {
		MuttersDAO dao = new MuttersDAO();
		dao.edit(mutter);
	}
}
