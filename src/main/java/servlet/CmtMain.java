package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Comment;
import model.GetCommentLogic;
import model.PostCommentLogic;

/**
 * Servlet implementation class CntMain
 */
@WebServlet("/CmtMain")
public class CmtMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mutterId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		session.setAttribute("targetMutterId", mutterId);
		
		//コメントリストを取得してリクエストスコープに保存
				GetCommentLogic getCommentLogic = new GetCommentLogic();
				List<Comment> commentList = getCommentLogic.execute(mutterId);
				request.setAttribute("commentList", commentList);
				
				
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/comment.jsp");
				dispatcher.forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String comment = request.getParameter("comment");
		int mutterId = Integer.parseInt(request.getParameter("id"));
		
		//入力値チェック
		if (comment != null && comment.length() !=0) {
			
			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			Account loginUser = (Account)session.getAttribute("loginUser");
//			Mutter mutterId = (Mutter)session.getAttribute("mutterId");
			
						
			//コメントをコメントリストに追加
			Comment cmt = new Comment(mutterId,loginUser.getUserId(),comment);
			PostCommentLogic postCommentLogic = new PostCommentLogic();
			postCommentLogic.execute(cmt);
			
			
		} else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "コメントが入力されていません");
		}
		
		//コメントリストを取得してリクエストスコープに保存
		GetCommentLogic getCommentLogic = new GetCommentLogic();
		List<Comment> commentList = getCommentLogic.execute(mutterId);
		request.setAttribute("commentList", commentList);
		
		//コメント画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/comment.jsp");
			dispatcher.forward(request, response);
		}
	}
