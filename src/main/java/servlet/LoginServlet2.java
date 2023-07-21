package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// リクエストパラメータの取得
//		request.setCharacterEncoding("UTF-8");
//		String userId = request.getParameter("userId");
//		String pass = request.getParameter("pass");
//		
//		//ログイン処理の実行
//		User user = new User(userId,pass);
//		LoginLogic bo = new LoginLogic();
//		Account account = bo.execute(user);
//		
//		//ログイン処理の成否によって処理を分岐
//		if(account != null) {//ログイン成功時
//			//セッションスコープにユーザーIDを保存
//			HttpSession session = request.getSession();
//			session.setAttribute("userId", userId);
//			
//			//フォワード
//			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
//			dispatcher.forward(request, response);
//		} else { //ログイン失敗時
//			//リダイレクト
//			response.sendRedirect("LoginServlet");
//	}
}
