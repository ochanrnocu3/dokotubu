package model;

import java.util.Map;

import dao.GoodDAO;

public class PostGoodLogic {
	public void execute(Good good) {

		GoodDAO dao = new GoodDAO();
		dao.create(good);
	}
	
	public Map<Integer,Integer> count() {
		GoodDAO dao = new GoodDAO();
		Map<Integer,Integer> gds = dao.countGood();
		return gds;
	}
}
