package servlet;

import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkMailAddress;
import static model.InputChecker.checkPhoneNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.ErrorViewData;
import model.InputCheckException;
import model.User;
import model.ValidationKey;

@WebServlet("/editprofile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

//		// フォームから送られた確認キーが保存したものと一致するか確認
//		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
//		if (!req.getParameter("vKey").equals(validationKey.getValue())) {
//			// 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
//			session.removeAttribute("validationKey");
//			//表示データを用意する
//			ErrorViewData errorData = new ErrorViewData("問題が発生しました。",
//													"トップに戻る","/ActionLogger/");
//			req.setAttribute("errorData", errorData);
//			//エラー表示にフォワード
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
//			dispatcher.forward(req, resp);
//			return;
//		}

		User user = new User();
		//DBから変更後のユーザを探すための引数に使うためにuseridをいれる
		String userid = null;
		try {
			user.setUserId( checkLongInput(req.getParameter("userid")) );
			user.setName(checkLongInput(req.getParameter("name")) );
			user.setAddress( checkLongInput(req.getParameter("address")) );
			user.setTel( checkPhoneNumber(req.getParameter("tel")) );
			user.setEmail( checkMailAddress(req.getParameter("email")) );

			userid = user.getUserId();
			
			// userDBを変更
			UserDAO userDAO = new UserDAO();
			//変更が成功したらuserをDBから取ってきてsessionに保存する
			if(userDAO.editProfile(user)) {
				// DBからユーザーを取得
	            User editeduser = userDAO.get(userid);

				// DBからの取得が成功
				session.setAttribute("user_name",editeduser.getName());
				session.setAttribute("user_adr", editeduser.getAddress());
				session.setAttribute("user_email", editeduser.getEmail());
				session.setAttribute("user_tel", editeduser.getTel());
				
				//変更確認画面にリダイレクト
				resp.sendRedirect("/ActionLogger?view=profileShow");
			}else {
				//エラーページに飛ばす
				ErrorViewData errorData = new ErrorViewData("プロフィール変更に失敗しました。","プロフィール編集","/ActionLogger?view=profileEdit");
				req.setAttribute("errorData", errorData);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
				dispatcher.forward(req, resp);
				return;
			}

			
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InputCheckException e1) {
			//表示データを用意する
			ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。",
													"プロフィール編集画面に戻る","/ActionLogger?view=profileEdit");
			req.setAttribute("errorData", errorData);
			//エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
