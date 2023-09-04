package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.GetCommentLogic;
import model.GetMutterListLogic;
import model.Good;
import model.Mutter;
import model.PostGoodLogic;

/**
 * Servlet implementation class GoodPost
 */
@WebServlet("/GoodPost")
public class GoodPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		int mutterId = Integer.parseInt(request.getParameter("id"));
		
		//セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("loginUser");

					
		//いいね！をいいね！リストに追加
		Good good = new Good(mutterId,loginUser.getUserId());
		PostGoodLogic postGoodLogic = new PostGoodLogic();
		postGoodLogic.execute(good);
		
		//いいね！カウント
		Map<Integer,Integer> gds = postGoodLogic.count();
		request.setAttribute("good", gds);
		
		//ユーザー毎のいいね！カウント
		Map<Integer, Integer> push = postGoodLogic.count2(loginUser.getUserId());
		request.setAttribute("push", push);
		
		//つぶやきリストを取得してリクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);
		
		//コメント件数を取得してリクエストスコープに保存
		GetCommentLogic getCommentLogic = new GetCommentLogic();
		Map<Integer,Integer> comments = getCommentLogic.count();
		request.setAttribute("comments", comments);
				
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);	
	}

}
