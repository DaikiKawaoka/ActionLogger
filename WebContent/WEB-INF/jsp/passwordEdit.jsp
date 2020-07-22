<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">パスワード変更</h1>
</div>
<form class="form-adduser" action="/ActionLogger/editpassword" method="post">

	<div class="mb-3">
		<label for="password">現在のパスワード</label> <input type="password"
			class="form-control" id="password" name="password-now"
			placeholder="現在のパスワード" required>
		<div class="invalid-feedback">必須</div>
	</div>
	<div class="mb-3">
		<label for="password">新規パスワード</label> <input type="password"
			class="form-control" id="password" name="password"
			placeholder="新規パスワード" required>
		<div class="invalid-feedback">必須</div>
	</div>
	<div class="mb-3">
		<label for="password">パスワード確認</label> <input type="password"
			class="form-control" id="password" name="password-confirm"
			placeholder="パスワード確認" required>
		<div class="invalid-feedback">必須</div>
	</div>
		<%-- フォームの正当性確認データ --%>
	<input type="hidden" name="vKey" value="${validationKey.value}">

		<input type="submit" class="btn btn-secondary btn-block btn-lg"
			id="enterRoom" value="変更"></input>
	</form>