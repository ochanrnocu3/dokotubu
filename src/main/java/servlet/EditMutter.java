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
import model.EditMutterLogic;
import model.GetMutterListLogic;
import model.Mutter;

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
		
		//編集画面にフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/edit.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータを取得
				request.setCharacterEncoding("UTF-8");
//				int id=Integer.parseInt(request.getParameter("id"));
				String text = request.getParameter("text");
				
				//入力値チェック
				if (text != null && text.length() !=0) {
					
					//セッションスコープに保存されたユーザー情報を取得
					HttpSession session = request.getSession();
					Account loginUser = (Account)session.getAttribute("loginUser");
					
					//つぶやきを編集
					Mutter mutter = new Mutter(loginUser.getName(),loginUser.getUserId(),text);
					EditMutterLogic editMutterLogic = new EditMutterLogic();
					editMutterLogic.execute(mutter);
						
				} else {
					//エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "つぶやきが入力されていません");
				}
				
				//つぶやきリストを取得してリクエストスコープに保存
				GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
				List<Mutter> mutterList = getMutterListLogic.execute();
				request.setAttribute("mutterList", mutterList);
				
		//メイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

}
