<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String user_name = (String) session.getAttribute("user_name");
	String user_id = (String) session.getAttribute("userid");
	String user_adr = (String) session.getAttribute("user_adr");
	String user_email = (String) session.getAttribute("user_email");
	String user_tel = (String) session.getAttribute("user_ter");
%>


	<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">プロフィール編集ページ</h1>
</div>
<form class="form-adduser" action="/ActionLogger/editprofile" method="post">
	<div class="mb-3">
		<label for="userid">ユーザーID</label> <input type="text"
			class="form-control" id="userid" name="userid"
			value="<c:out value="${userid}"/>" placeholder="ユーザーID" required>
		<div class="invalid-feedback">必須</div>
	</div>
	<div class="mb-3">
		<label for="name">氏名</label> <input type="text"
			class="form-control" id="name" name="name" value="<c:out value="${user_name}"/>"　placeholder="氏名"　required>
		<div class="invalid-feedback">必須</div>
	</div>
	<div class="mb-3">
		<label for="address">住所</label> <input type="text"
			class="form-control" id="address" name="address" value="<c:out value="${user_adr}"/>" placeholder="住所">
	</div>
	<div class="mb-3">
		<label for="tel">電話番号</label> <input type="text"
			class="form-control" id="tel" name="tel"
			value="<c:out value="${user_tel}"/>" placeholder="電話番号">
	</div>
	<%-- フォームの正当性確認データ --%>
	<input type="hidden" name="vKey" value="${validationKey.value}">
		<div class="mb-3">
			<label for="email">メールアドレス</label> <input type="text"
				class="form-control" id="email" name="email" value="<c:out value="${user_email}"/>" placeholder="メールアドレス">
		</div>
		<input type="submit" class="btn btn-secondary btn-block btn-lg"
			id="enterRoom" value="変更"></input>
	</form>