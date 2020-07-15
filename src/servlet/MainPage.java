package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.ActionDAO;
import dao.UserDAO;
import model.User;
import model.Action;
import java.util.*;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public MainPage() {
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HttpSessionインタフェースのオブジェクトを取得
		HttpSession session = request.getSession();
		// useridデータをsessionスコープで保存
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			// MainViewを表示
			response.sendRedirect("/ActionLogger/login");

		} else {
			// DBからユーザーのaction一覧を取得
			ActionDAO actionDAO = new ActionDAO();
			List<Action> actionList = actionDAO.get((String)session.getAttribute("userid"));
			// MainViewを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
