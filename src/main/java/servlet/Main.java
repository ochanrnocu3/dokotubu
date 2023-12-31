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
import model.Mutter;
import model.PostGoodLogic;
import model.PostMutterLogic;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		//ログインしているか確認するためセッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		Account loginUser = (Account) session.getAttribute("loginUser");		
			
				
		if (loginUser == null) { //ログインしていない場合
			//リダイレクト
			response.sendRedirect("WEB-INF/jsp/login.jsp");
		} else { //ログイン済みの場合
			
			//つぶやきリストを取得してリクエストスコープに保存
			GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
			List<Mutter> mutterList = getMutterListLogic.execute();
			request.setAttribute("mutterList", mutterList);
			
			//コメント件数を取得してリクエストスコープに保存
			GetCommentLogic getCommentLogic = new GetCommentLogic();
			Map<Integer,Integer> comments = getCommentLogic.count();
			request.setAttribute("comments", comments);
			
			//いいね！件数を取得してリクエストスコープに保存
			PostGoodLogic postGoodLogic = new PostGoodLogic();
			Map<Integer,Integer> gds = postGoodLogic.count();
			request.setAttribute("good", gds);
			
			//ユーザー毎のいいね！カウント
			Map<Integer, Integer> push = postGoodLogic.count2(loginUser.getUserId());
			request.setAttribute("push", push);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		//セッションスコープに保存されたユーザー情報を取得
		HttpSession session = request.getSession();
		Account loginUser = (Account)session.getAttribute("loginUser");
		
		//入力値チェック
		if (text != null && text.length() !=0) {
			
			//つぶやきをつぶやきリストに追加
			Mutter mutter = new Mutter(loginUser.getName(),loginUser.getUserId(),text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter);
			
			
		} else {
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
		}
		
		//つぶやきリストを取得してリクエストスコープに保存
		GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
		List<Mutter> mutterList = getMutterListLogic.execute();
		request.setAttribute("mutterList", mutterList);
		
		//コメント件数を取得してリクエストスコープに保存
		GetCommentLogic getCommentLogic = new GetCommentLogic();
		Map<Integer,Integer> comments = getCommentLogic.count();
		request.setAttribute("comments", comments);
		
		//いいね！件数を取得してリクエストスコープに保存
		PostGoodLogic postGoodLogic = new PostGoodLogic();
		Map<Integer,Integer> gds = postGoodLogic.count();
		request.setAttribute("good", gds);
		
		//ユーザー毎のいいね！カウント
		Map<Integer, Integer> push = postGoodLogic.count2(loginUser.getUserId());
		request.setAttribute("push", push);
		
		//メイン画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
}