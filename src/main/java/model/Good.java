package model;

import java.io.Serializable;

public class Good implements Serializable{
	private int mutterId;
	private String userId;
	private String name;

	public Good() {}
	
	public Good(int mutterId,String userId,String name) {
		this.mutterId = mutterId;
		this.userId = userId;
		this.name = name;
	}

	public Good(int mutterId, String userId) {
		this.mutterId = mutterId;
		this.userId = userId;
	}

	public int getMutterId() {
		return mutterId;
	}

	public String getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
}
