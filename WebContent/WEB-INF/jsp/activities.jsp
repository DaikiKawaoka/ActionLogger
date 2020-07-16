<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<!-- JSTL の　Core　を使うための宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Action" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
	List<Action> actionList = (ArrayList<Action>) session.getAttribute("actionList");
%>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">行動記録</h1>
</div>

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
		　　　　<c:forEach var="action" items="${ actionList }">
				<tr>
					<td><c:out value="${action.getStart_date()}"/></td>
					<td><c:out value="${action.getStart_time()}"/>- <c:out value="${action.getFinish_time()}"/></td>
					<td><c:out value="${action.getAction_place()}"/></td>
					<td><c:out value="${action.getAction_reason()}"/></td>
					<td><c:out value="${action.getAction_remarks()}"/></td>
				</tr>
		　　　　</c:forEach>
		</tbody>
	</table>
</div>
