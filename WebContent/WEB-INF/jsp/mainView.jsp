<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- JSTL の　Core　を使うための宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Action" %>
<%@ page import="model.ManagementGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.GroupShowModel" %>


<%
	String view = request.getParameter("view");

	if(view == null) view = "dashboard";
	String user_name = (String) session.getAttribute("user_name");
%>

<!doctype html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ActionLogger</title>
<!-- Bootstrap core CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<meta name="theme-color" content="#563d7c">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>
<!-- Custom styles for this template -->
<link href="/GuiWork/css/dashboard.css" rel="stylesheet">
</head>
<body>
	<nav
		class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
		<a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="/ActionLogger/">Action
			Logger</a>
		<button class="navbar-toggler position-absolute d-md-none collapsed"
			type="button" data-toggle="collapse" data-target="#sidebarMenu"
			aria-controls="sidebarMenu" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<ul class="navbar-nav px-3">
			<li class="nav-item text-nowrap"></li>
			<li class="nav-item text-nowrap"><a class="nav-link" href="/ActionLogger/logout"><c:out value="${user_name}"/>
					- ログアウト</a></li>
		</ul>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
				<!-- サイドバーの中身をインポート -->
				<jsp:include page="/WEB-INF/jsp/sidebar.jsp" />
			</nav>
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
				<!-- コンテンツエリアの中身をインポート -->
				<%
					if (view != null && view.equals("activities")) {
				%>
				<jsp:include page="/WEB-INF/jsp/activities.jsp" />
				<%
					} else if(view != null && view.equals("action_form")) {
				%>
				<jsp:include page="/WEB-INF/jsp/addActionForm.jsp" />
				<%
					} else if(view.equals("groupshow")) {
				%>
				<jsp:include page="/WEB-INF/jsp/groupShow.jsp" />
				<%
					} else if(view != null && view.equals("createGroup")) {
				%>
				<jsp:include page="/WEB-INF/jsp/createGroupForm.jsp" />
				<%
					} else if(view != null && view.equals("addBelongsForm")) {
				%>
				<jsp:include page="/WEB-INF/jsp/addBelongsForm.jsp" />
				<%
					}else if(view==null || view.equals("dashboard")){
				%>
				<jsp:include page="/WEB-INF/jsp/dashboard.jsp" />
				<%	
					} else if(view != null && view.equals("profileEdit")) {
				%>
				<jsp:include page="/WEB-INF/jsp/profileEdit.jsp" />
				<%	
					} else if(view != null && view.equals("profileShow")) {
				%>
				<jsp:include page="/WEB-INF/jsp/profileShow.jsp" />
				<%	
					} else if(view != null && view.equals("passwordEdit")) {
				%>
				<jsp:include page="/WEB-INF/jsp/passwordEdit.jsp" />
				<%
					}else{
				%>
					<jsp:include page="/WEB-INF/jsp/dashboard.jsp" />
				<%
					}
				%>
				
			</main>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
		integrity="sha384-1CmrxMRARb6aLqgBO7yyAxTOQE2AKb9GfXnEo760AUcUmFx3ibVJJAzGytlQcNXd"
		crossorigin="anonymous"></script>

</body>
</html>