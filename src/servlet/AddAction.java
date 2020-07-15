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

@WebServlet("/addaction")
public class AddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddAction() {
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
				Action action = new Action();
				HttpSession session = req.getSession();
				
//				try {
					action.setAction_id();
					//作成時間セット
					action.setCreate_time();

					//セッションからユーザーID取得
					action.setUser_id( (String) session.getAttribute("userid") );

					action.setStart_date( req.getParameter("start_date") );
					action.setFinish_date( req.getParameter("finish_date") );
					action.setStart_time( req.getParameter("start_time") );
					action.setFinish_time( req.getParameter("finish_time") );
					action.setAction_place( req.getParameter("action_place") );
					action.setAction_reason( req.getParameter("action_reason") );
					action.setAction_remarks( req.getParameter("action_remarks") );

					// actionオブジェクトをセッションスコープに一旦保存（DBに入れるのはConfirmの後）
					session.setAttribute("actionToAdd", action);

					// 確認画面にリダイレクト
					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addActionConfirm.jsp");
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
