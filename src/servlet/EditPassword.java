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

@WebServlet("/editpassword")
public class EditPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditPassword() {
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
		User userTmp = new User(); 
		String userId = (String) session.getAttribute("userid");
		try {
			// 新規パスワードのハッシュ化
			String PasswordNow = req.getParameter("password-now");
			MessageDigest digestNow = MessageDigest.getInstance("SHA-256");
			digestNow.reset();
			digestNow.update(PasswordNow.getBytes("utf8"));
			String passwordHashNow = String.format("%064x", new BigInteger(1, digestNow.digest()));
			// 新規パスワードのハッシュ化
			String rawPassword = req.getParameter("password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			String passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));
			//確認パスワードハッシュ化
			String rawPasswordCon = req.getParameter("password-confirm");
			MessageDigest digestCon = MessageDigest.getInstance("SHA-256");
			digestCon.reset();
			digestCon.update(rawPasswordCon.getBytes("utf8"));
			String passwordHashCon = String.format("%064x", new BigInteger(1, digestCon.digest()));

			//新規パスワードハッシュと確認パスワードハッシュが等しいか確認
			if(!passwordHash.equals(passwordHashCon)) {
				//等しくないとき
				//表示データを用意する
				ErrorViewData errorData = new ErrorViewData("新規パスワードと確認パスワードが一致していません。","パスワード編集","/ActionLogger?view=passwordEdit");
				req.setAttribute("errorData", errorData);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
				dispatcher.forward(req, resp);
				return;
			}

			
			// userDBを変更
			UserDAO userDAO = new UserDAO();
			userTmp = userDAO.get(userId);
			if(!userTmp.getPwdHash().equals(passwordHashNow)) {
				//エラーページに飛ばす
				ErrorViewData errorData = new ErrorViewData("現在のパスワードが違います。","パスワード編集","/ActionLogger?view=passwordEdit");
				req.setAttribute("errorData", errorData);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
				dispatcher.forward(req, resp);
				return;
			}
			
			user.setPwdHash(passwordHash); // ハッシュ値をオブジェクトに設定
			
			//更新作業
			//DBでパスワード変更が成功したか判定
			if(userDAO.editPassword(user,userId)) {
				//成功
				//プロフィール確認画面にリダイレクト
				resp.sendRedirect("/ActionLogger");
			}else {
				//エラーページに飛ばす
				ErrorViewData errorData = new ErrorViewData("パスワード変更に失敗しました。","パスワード編集","/ActionLogger?view=passwordEdit");
				req.setAttribute("errorData", errorData);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
				dispatcher.forward(req, resp);
				return;
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
//		} catch (InputCheckException e1) {
//			//表示データを用意する
//			ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。",
//													"プロフィール編集画面に戻る","/ActionLogger?view=profileEdit");
//			req.setAttribute("errorData", errorData);
//			//エラー表示にフォワード
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
//			dispatcher.forward(req, resp);
		}
	}

}
