package model;

import dao.AccountsDAO;

public class RegisterUserLogic {
	public boolean execute(Account account){
		AccountsDAO dao = new AccountsDAO();
		dao.create(account);
//		Account userId = dao.findByUserId(account);
		
	return true;
}

}
