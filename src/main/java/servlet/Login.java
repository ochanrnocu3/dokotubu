package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.LoginLogic;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String userId =request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		//Userインスタンス（ユーザ情報）の生成
		User user = new User(userId,pass);
		
		//ログイン処理
		LoginLogic loginLogic = new LoginLogic();
		Account account = loginLogic.execute(user);
		
		//ログイン成功時の処理
		if (account != null) {
		//ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", account);
		}
		//ログイン結果画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);
	}

}
