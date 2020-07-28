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
import dao.GroupDAO;
import dao.UserDAO;
import model.Action;
import model.GroupShowModel;
import model.User;

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
		String groupId = null;
		groupId = req.getParameter("groupId");
		
		if(groupId == null) {
			//自分の行動検索
			List<Action> searchActionList = new ArrayList<Action>(); 
			ActionDAO actionDAO = new ActionDAO();
			//検索結果を代入
			searchActionList = actionDAO.searchAction(userId, search);
			req.setAttribute("searchActionList",searchActionList );
	
			// MainViewを表示
			String url = "/WEB-INF/jsp/mainView.jsp?view=activities";
			RequestDispatcher dispatcher = req.getRequestDispatcher(url);
			dispatcher.forward(req, resp);
		}else {
			//グループの行動検索
			List<GroupShowModel> searchGroupShowAction = null;
			ActionDAO actionDAO = new ActionDAO();
			searchGroupShowAction = actionDAO.searchGroupShowAction(groupId, search);

			List<User> groupShowUserList = null;
			UserDAO userDAO = new UserDAO();
			groupShowUserList = (List<User>) userDAO.getGroupShowUserList(groupId);
			
			req.setAttribute("searchGroupShowAction",searchGroupShowAction );
			req.setAttribute("groupShowUserList", groupShowUserList);
			req.setAttribute("groupId", groupId);
						
			// MainViewを表示
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp?view=groupshow");
			dispatcher.forward(req, resp);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
