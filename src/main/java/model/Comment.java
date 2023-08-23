package model;

import java.io.Serializable;


	public class Comment implements Serializable {
		private int id;
		private int mutterId;
		private String userId;
		private String comment;
		private String name;

		public Comment() {}
		
		public Comment(int id,int mutterId,String userId,String name,String comment) {
			this.id = id;
			this.mutterId = mutterId;
			this.comment = comment;
			this.userId = userId;
			this.name = name;
		}

		public Comment(int mutterId,String userId, String comment) {
			this.mutterId = mutterId;
			this.comment = comment;
			this.userId = userId;
		}
		
		public int getId() {
			return id;
		}

		public int getMutterId() {
			return mutterId;
		}

		public String getUserId() {
			return userId;
		}

		public String getComment() {
			return comment;
		}
		
		public String getName() {
			return name;
		}
}
