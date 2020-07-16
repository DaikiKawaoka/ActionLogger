package servlet;

import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkMailAddress;
import static model.InputChecker.checkPhoneNumber;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ErrorViewData;
import model.InputCheckException;
import model.*;
import model.ValidationKey;

@WebServlet("/creategroup")
public class CreateGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateGroup() {
        super();
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// 正当なフォームから送られたデータであることを確認するためのキーの生成
//		ValidationKey validationKey = new ValidationKey();
//		try {
//			Random random = new Random();
//			String randomStr = String.valueOf(random.nextLong());
//			MessageDigest validation = MessageDigest.getInstance("MD5");
//			validation.reset();
//			validation.update(randomStr.getBytes("utf8"));
//			String vkey = String.format("%032x", new BigInteger(1, validation.digest()));
//			validationKey.setValue(vkey);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		// フォーム確認キーをセッションスコープに設定
//		HttpSession session = req.getSession();
//		session.setAttribute("validationKey", validationKey);
//
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addActionForm.jsp");
//		dispatcher.forward(req, resp);
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				//下記のコードがないと送られてきたフォームの文字が文字化けする
				req.setCharacterEncoding("UTF-8");
		
				//actionインスタンス化
				//セッションインスタンス化
				ManagementGroup managementGroup = new ManagementGroup();
				HttpSession session = req.getSession();
				
//				try {
				   //ManagementGroup作成
					managementGroup.setManagement_group_id();
					managementGroup.setCreate_time();
					managementGroup.setGroup_name(req.getParameter("groupName"));

					// actionオブジェクトをセッションスコープに一旦保存（DBに入れるのはConfirmの後）
					session.setAttribute("magagementGroupToAdd", managementGroup);

					// 確認画面にリダイレクト
					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/createGroupConfirm.jsp");
					dispatcher.forward(req, resp);

//				} catch (NoSuchAlgorithmException e) {
//					e.printStackTrace();
//				} catch (UnsupportedEncodingException e) {
//					e.printStackTrace();
//				} catch (InputCheckException e1) {
//					//表示データを用意する
//					ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。",
//															"入力画面に戻る","/ActionLogger/adduser");
//					req.setAttribute("errorData", errorData);
//					//エラー表示にフォワード
//					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
//					dispatcher.forward(req, resp);
//				}
	}

}
