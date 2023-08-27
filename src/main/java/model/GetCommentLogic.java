package model;

import java.util.List;
import java.util.Map;

import dao.CommentsDAO;

public class GetCommentLogic {
	public List<Comment> execute(int targetId) {
		CommentsDAO dao = new CommentsDAO();
		List<Comment> commentList = dao.find(targetId);
		return commentList;
	}
	public Map<Integer,Integer> count() {
		CommentsDAO dao = new CommentsDAO();
		Map<Integer,Integer> comments = dao.countCmt();
		return comments;
	}
}
