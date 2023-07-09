package model;

import dao.MuttersDAO;

public class PostMutterLogic {

	public void execute(Mutter mutter) {
//		mutterList.add(0,mutter);// 先頭に追加	}
		MuttersDAO dao = new MuttersDAO();
		dao.create(mutter);
	}
}