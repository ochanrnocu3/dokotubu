package model;

import dao.CommentsDAO;

public class PostCommentLogic {
	public void execute(Comment comment) {

		CommentsDAO dao = new CommentsDAO();
		dao.create(comment);
	}
}
