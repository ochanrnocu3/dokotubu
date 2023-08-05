package model;

import java.io.Serializable;

public class Mutter implements Serializable {
	private int id;
	private String userName;
	private String text;
	private String userId;

	public Mutter() {}
	public Mutter(String userName,String userId,String text) {
		this.userName = userName;
		this.userId = userId;
		this.text = text;
	}
	public Mutter(int id,String userName,String text,String userId) {
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.userId = userId;
	}
	public int getId() {	return id;	}
	public String getUserName() {		return userName;	}
	public String getText() {		return text;	}
	public String getUserId() {		return userId;		}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
