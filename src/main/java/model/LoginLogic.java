package model;

import dao.AccountsDAO;

public class LoginLogic {

	public Account execute(User user) {
//		if (user.getPass().equals("1234")) { return true; }
//		return false;	
	AccountsDAO dao = new AccountsDAO();
	Account account = dao.findByLogin(user);
	return account;	}

//	public boolean execute(User user) {
//		// TODO 自動生成されたメソッド・スタブ
//		return false;
//	}

}
