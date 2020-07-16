package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BelongsDAO;
import dao.GroupDAO;
import model.ManagementGroup;

@WebServlet("/addbelongsgroup")
public class AddBelongs extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddBelongs() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//フォームで送られてきたグループIDがDBに存在するかチェック
		String groupId = req.getParameter("groupId");
		GroupDAO groupDAO = new GroupDAO();
		ManagementGroup group = groupDAO.checkAndGetGroup(groupId);
		if(group != null){
		  //存在した場合
			HttpSession session = req.getSession();
			String userid =(String) session.getAttribute("userid");
			BelongsDAO belongsDAO = new BelongsDAO();
			belongsDAO.save(group,userid);
			
		}
		
		// DBへの保存が成功して、mainページに遷移
		resp.sendRedirect("/ActionLogger/");
		
	}

}
