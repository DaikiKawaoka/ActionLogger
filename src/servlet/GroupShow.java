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
import dao.GroupDAO;
import dao.UserDAO;
import model.Action;
import model.GroupShowModel;
import model.ManagementGroup;
import model.User;

@WebServlet("/groupshow")
public class GroupShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GroupShow() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        request.setCharacterEncoding("UTF-8");
				// HttpSessionインタフェースのオブジェクトを取得
				HttpSession session = request.getSession();
				String userId = (String) session.getAttribute("userid");
				String groupId = request.getParameter("id");
				AdominDAO adominDAO = new AdominDAO();
				boolean adominGroupBoolean = adominDAO.getAdominGroupBoolean(groupId, userId);

				if (userId == null) {
					// MainViewを表示
					response.sendRedirect("/ActionLogger/login");

				}else if(userId != null && adominGroupBoolean ) {
					//groupShowListをデータベースから取ってくる
					List<GroupShowModel> groupShowList = null;
					GroupDAO groupDAO = new GroupDAO();
					groupShowList = (List<GroupShowModel>) groupDAO.getGroupShow(groupId);
					
					List<User> groupShowUserList = null;
					UserDAO userDAO = new UserDAO();
					groupShowUserList = (List<User>) userDAO.getGroupShowUserList(groupId);
					
					//リクエストスコープに保存
//					session.setAttribute("groupShowList", groupShowList);
					request.setAttribute("groupShowList", groupShowList);
					request.setAttribute("groupShowUserList", groupShowUserList);
					
					// MainViewを表示
       				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp?view=groupshow");
					dispatcher.forward(request, response);

				}else {
					// MainViewを表示
					response.sendRedirect("/ActionLogger");
				}
	}

}
