<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!-- JSTL の　Core　を使うための宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.ManagementGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
	String user_name = (String) session.getAttribute("user_name");
	String user_id = (String) session.getAttribute("user_id");
	List<ManagementGroup> adominGroupList = (ArrayList<ManagementGroup>) session.getAttribute("adominGroupList");
%>	
	
<div class="sidebar-sticky pt-3">

	<ul class="nav flex-column">
		<li class="nav-item"><a class="nav-link active"
			href="/ActionLogger/?view=dashboard"> Dashboard </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 活動記録 ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="/ActionLogger/?view=action_form"> <span
				data-feather="file-text"></span> 活動記録登録
		</a></li>
		<li class="nav-item"><a class="nav-link"
			href="/ActionLogger/?view=activities"> 表示 </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 管理グループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="/ActionLogger/?view=createGroup"> 新規グループ </a></li>
		<li class="nav-item"><a class="nav-link" href="/ActionLogger/?view=addBelongsForm"> グループに参加 </a></li>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== 管理中のグループ ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">	
	<!-- 管理グループループ -->
		<c:forEach var="group" items="${ adominGroupList }">
			<li class="nav-item"><a class="nav-link" href="/ActionLogger/groupshow?id=<c:out value="${group.getManagement_group_id()}"/>"><c:out value="${group.getGroup_name()}"/></a></li>
		</c:forEach>
	</ul>

	<h6
		class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
		<span>== プロフィール ==</span> <a
			class="d-flex align-items-center text-muted" href="#"
			aria-label="Add a new report"> <span data-feather="plus-circle"></span>
		</a>
	</h6>
	<ul class="nav flex-column mb-2">
		<li class="nav-item"><a class="nav-link" href="#"> プロフィール確認 </a>
		</li>
		<li class="nav-item"><a class="nav-link" href="#"> プロフィール変更 </a>
		</li>
	</ul>


</div>

