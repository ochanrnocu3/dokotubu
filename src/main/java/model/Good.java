package model;

import java.io.Serializable;

public class Good implements Serializable{
	private int mutterId;
	private String userId;

	public Good() {}
	
	public Good(int mutterId,String userId) {
		this.mutterId = mutterId;
		this.userId = userId;
	}

	public int getMutterId() {
		return mutterId;
	}

	public String getUserId() {
		return userId;
	}
	
}
