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
import model.EditMutterLogic;
import model.GetCommentLogic;
import model.GetMutterListLogic;
import model.Mutter;
import model.PostGoodLogic;

/**
 * Servlet implementation class EditMutter
 */
@WebServlet("/EditMutter")
public class EditMutter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		int id=Integer.parseInt(request.getParameter("id"));
		
		if(id != 0) {
			HttpSession session = request.getSession();
			session.setAttribute("targetId", id);
			session.setAttribute("targetText", text);
		
	}
		//編集画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edit.jsp");
		dispatcher.forward(request, response);}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
				request.setCharacterEncoding("UTF-8");
				String text = request.getParameter("text");
				int id=Integer.parseInt(request.getParameter("id"));
				
				//セッションスコープに保存されたユーザー情報を取得
				HttpSession session = request.getSession();
				Account loginUser = (Account)session.getAttribute("loginUser");
				
				//入力値チェック
				if(text != null && text.length() !=0 && id != 0) {
									
					EditMutterLogic editMutterLogic = new EditMutterLogic();
					editMutterLogic.execute(id,text);
					
											
				} else {
					//エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "つぶやきが入力されていません");
					//編集画面にフォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edit.jsp");
					dispatcher.forward(request, response);
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
