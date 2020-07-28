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
import model.Action;
import java.util.*;

@WebServlet("/searchaction")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchAction() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userid");
		String search = req.getParameter("search");

		List<Action> searchActionList = new ArrayList<Action>(); 
		ActionDAO actionDAO = new ActionDAO();
		//検索結果を代入
		searchActionList = actionDAO.searchAction(userId, search);
		req.setAttribute("searchActionList",searchActionList );

		// MainViewを表示
		String url = "/WEB-INF/jsp/mainView.jsp?view=activities";
		RequestDispatcher dispatcher = req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
