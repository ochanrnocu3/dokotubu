package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MuttersDAO;
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
		
		int id=Integer.parseInt(request.getParameter("id"));
		
		if(id != 0) {
			Mutter mutter = new Mutter(id);
			MuttersDAO dao=new MuttersDAO();
			dao.edit(mutter);
			
		} 
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
				String text = request.getParameter("text");
				
				
				//入力値チェック
				if(text != null && text.length() !=0) {
					Mutter mutter = new Mutter(text);
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
