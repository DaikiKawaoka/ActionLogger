package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActionDAO;
import dao.GroupDAO;
import model.Action;
import model.ManagementAdomin;
import model.ManagementGroup;


@WebServlet("/creategroupconfirm")
public class CreateGroupConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateGroupConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

			// セッションスコープに保存していた、DB登録前のグループ情報を取得
			// グループテーブル、グループ管理ユーザテーブルを作成
			//グループテーブル
			ManagementGroup managementGroup = (ManagementGroup) session.getAttribute("magagementGroupToAdd");
			//ユーザテーブル
			String userid = (String) session.getAttribute("userid");
			GroupDAO groupDAO = new GroupDAO();
			groupDAO.save(managementGroup,userid); // DBに保存

		// DBへの保存が成功して、mainページに遷移
		resp.sendRedirect("/ActionLogger/");
	}

}
