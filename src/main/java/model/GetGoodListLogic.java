package model;

import java.util.List;

import dao.GoodDAO;

public class GetGoodListLogic {
		public List<Good> execute(int targetId) {
			GoodDAO dao = new GoodDAO();
			List<Good> goodList = dao.find(targetId);
			return goodList;
		}
}
