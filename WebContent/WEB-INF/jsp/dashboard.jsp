<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- JSTL の　Core　を使うための宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Action"%>
<%@ page import="model.ManagementGroup"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>


<%
	String user_name = (String) session.getAttribute("user_name");
	String user_id = (String) session.getAttribute("user_id");
	List<Action> actionList = (ArrayList<Action>) session.getAttribute("actionList");
	List<ManagementGroup> adominGroupList = (ArrayList<ManagementGroup>) session.getAttribute("adominGroupList");
	List<ManagementGroup> belongsList = (ArrayList<ManagementGroup>) session.getAttribute("belongsList");
%>

<style>
.no-action-td {
	height: 300px;
	font-size: 50px;
	line-height: 300px;
	text-align: center;
	color:#888;
}
</style>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Dashboard</h1>
</div>
<h3>ユーザー情報</h3>
<div class="table-responsive">
	<table class="table table-borderless table-sm">
		<tbody>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">ID</tk>
				<td><c:out value="${userid}" /></td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">Name</th>
				<td><c:out value="${user_name}" /></td>
			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">参加グループ</th>

				<!-- 参加グループループ -->
				<c:forEach var="group" items="${ belongsList }">
					<td>name: <c:out value="${group.getGroup_name()} " /> <br>
						id: <c:out value="${group.getManagement_group_id()} " /></td>
				</c:forEach>

			</tr>
			<tr class="d-flex">
				<th scope="row" class="col-2 text-right">管理グループ</th>

				<!-- 管理グループループ -->
				<c:forEach var="group" items="${ adominGroupList }">
					<td>name: <c:out value="${group.getGroup_name()} " /> <br>
						id: <c:out value="${group.getManagement_group_id()} " /></td>
				</c:forEach>
			</tr>
		</tbody>
	</table>
</div>
<h3>最近の行動記録</h3>
<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${actionList != null}">
					<c:forEach var="action" items="${ actionList }">
						<tr>
							<td><c:out value="${action.getStart_date()}" /></td>
							<td><c:out value="${action.getStart_time()}" />- <c:out
									value="${action.getFinish_time()}" /></td>
							<td><c:out value="${action.getAction_place()}" /></td>
							<td><c:out value="${action.getAction_reason()}" /></td>
							<td><c:out value="${action.getAction_remarks()}" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5" class="no-action-td">行動記録が登録されていません。</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
