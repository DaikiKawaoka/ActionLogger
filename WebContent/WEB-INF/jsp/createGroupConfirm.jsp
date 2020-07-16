<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL の　Core　を使うための宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger グループ登録確認</title>

<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/ActionLogger/">Action
			Logger</a>
	</nav>
	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form class="form-adduser"
				action="/ActionLogger/creategroupconfirm" method="post">
<br>
				<h4 h3 mb-3 font-weight-normal>新規グループ確認</h4>
<br>
				<div class="mb-3">グループネーム　: <c:out value="${magagementGroupToAdd.group_name}"/></div>
<br>
				<div class="mb-3">グループ管理ユーザー　:　グループ作成者（あなた）</div>
				<input type="submit" class="btn btn-secondary btn-block btn-lg" id="enterRoom" value="OK"></input>

			</form>
		</div>
		<div class="col"></div>
	</div>
</body>
</html>