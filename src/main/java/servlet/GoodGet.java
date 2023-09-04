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

import model.GetGoodListLogic;
import model.Good;

/**
 * Servlet implementation class GoodMain
 */
@WebServlet("/GoodGet")
public class GoodGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mutterId = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		session.setAttribute("targetMutterId", mutterId);
		
		//いいねリストを取得してリクエストスコープに保存
				GetGoodListLogic getGoodListLogic = new GetGoodListLogic();
				List<Good> goodList = getGoodListLogic.execute(mutterId);
				request.setAttribute("goodList", goodList);
				
				
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/good.jsp");
				dispatcher.forward(request, response);	
	}
}
