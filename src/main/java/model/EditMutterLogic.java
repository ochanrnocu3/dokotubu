package model;

import dao.MuttersDAO;

public class EditMutterLogic {
	public void execute(int id,String text) {
		MuttersDAO dao = new MuttersDAO();
		dao.edit(id,text);
	}

}
