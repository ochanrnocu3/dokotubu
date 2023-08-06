package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MuttersDAO;

/**
 * Servlet implementation class DeleteMutter
 */
@WebServlet("/DeleteMutter")
public class DeleteMutter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		if(id != 0) {
			MuttersDAO dao=new MuttersDAO();
			dao.delete(id);
			
		} 
		//フォワード
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/deleteDone.jsp");
	    dispatcher.forward(request, response);
	}

}
