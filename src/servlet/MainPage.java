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
import dao.AdominDAO;
import dao.BelongsDAO;
import dao.UserDAO;
import model.User;
import model.Action;
import model.ManagementGroup;

import java.util.*;

@WebServlet("/")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public MainPage() {
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			List<Action> actionList = new ArrayList<Action>();
			actionList = actionDAO.get((String)session.getAttribute("userid"));
			//actionListをセッションにセット
			if(!actionList.isEmpty()) {
				session.setAttribute("actionList",actionList);
			}
			
			// DBから管理グループのListを取得
			AdominDAO adominDAO = new AdominDAO();
			List<ManagementGroup> adominGroupList = new ArrayList<ManagementGroup>();;
			adominGroupList = adominDAO.get((String)session.getAttribute("userid"));
			if(!adominGroupList.isEmpty()) {
				session.setAttribute("adominGroupList",adominGroupList);
			}
			//DBから所属グループのListをし取得
			BelongsDAO belongsDAO = new BelongsDAO();
			List<ManagementGroup> belongsList = new ArrayList<ManagementGroup>();;
			belongsList = belongsDAO.get((String)session.getAttribute("userid"));
			if(!belongsList.isEmpty()) {
				session.setAttribute("belongsList",belongsList);
			}

			// MainViewを表示
			String url = "/WEB-INF/jsp/mainView.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
