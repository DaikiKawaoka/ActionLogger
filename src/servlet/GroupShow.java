package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import model.Action;
import model.ManagementGroup;

@WebServlet("/groupshow")
public class GroupShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GroupShow() {
        super();
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
					session.setAttribute("actionList",actionList);
					
					// DBから管理グループのListを取得
					AdominDAO adominDAO = new AdominDAO();
					List<ManagementGroup> adominGroupList = new ArrayList<ManagementGroup>();
					adominGroupList = adominDAO.get((String)session.getAttribute("userid"));
					session.setAttribute("adominGroupList",adominGroupList);
					
					//DBから所属グループのListをし取得
					BelongsDAO belongsDAO = new BelongsDAO();
					List<ManagementGroup> belongsList = new ArrayList<ManagementGroup>();
					belongsList = belongsDAO.get((String)session.getAttribute("userid"));
					session.setAttribute("belongsList",belongsList);
					
					// MainViewを表示
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
					dispatcher.forward(request, response);
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
