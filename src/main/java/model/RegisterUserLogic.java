package model;

import dao.AccountsDAO;

public class RegisterUserLogic {
	public boolean execute(Account account){
		AccountsDAO dao = new AccountsDAO();
		int cnt = dao.findByUserId(account.getUserId());
		
		if(cnt != 0){
			
		return false;
			
		}else {
		
		dao.create(account);
		return true;
		}
		
	}

}
