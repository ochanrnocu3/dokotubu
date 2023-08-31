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
import model.GetGoodListLogic;
import model.GetMutterListLogic;
import model.Good;
import model.Mutter;
import model.PostGoodLogic;

/**
 * Servlet implementation class GoodMain
 */
@WebServlet("/GoodMain")
public class GoodMain extends HttpServlet {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// リクエストパラメータを取得
				request.setCharacterEncoding("UTF-8");
				int mutterId = Integer.parseInt(request.getParameter("id"));
				
				//セッションスコープに保存されたユーザー情報を取得
				HttpSession session = request.getSession();
				Account loginUser = (Account)session.getAttribute("loginUser");
		
							
				//いいね！をいいね！リストに追加
				Good good = new Good(mutterId,loginUser.getName());
				PostGoodLogic postGoodLogic = new PostGoodLogic();
				postGoodLogic.execute(good);
				
				//いいね！カウント
				Map<Integer,Integer> gds = postGoodLogic.count();
				request.setAttribute("good", gds);
				
				//つぶやきリストを取得してリクエストスコープに保存
				GetMutterListLogic getMutterListLogic = new GetMutterListLogic();
				List<Mutter> mutterList = getMutterListLogic.execute();
				request.setAttribute("mutterList", mutterList);
				
				//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
				dispatcher.forward(request, response);	
				
	}

}
