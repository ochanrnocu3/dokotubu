package model;

import java.io.Serializable;

public class User implements Serializable{
	private String userId;//ユーザ名
	private String pass;

	public User() {}
	public User(String userId,String pass) {// TODO 自動生成されたコンストラクター・スタブ
	this.userId = userId;
	this.pass = pass;
	}
	public String getUserId() {
		return userId;
	}
	public String getPass() {
		return pass;
	}
	

}
