<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!-- JSTL の　Core　を使うための宣言 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.Action"%>
<%@ page import="model.ManagementGroup"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.GroupShowModel"%>
<%@ page import="model.User"%>

<%
	List<GroupShowModel> groupShowList = null;
	groupShowList = (ArrayList<GroupShowModel>) request.getAttribute("groupShowList");

	List<User> groupShowUserList = null;
	groupShowUserList = (ArrayList<User>) request.getAttribute("groupShowUserList");

	List<GroupShowModel> searchGroupShowAction = null;
	searchGroupShowAction= (ArrayList<GroupShowModel>) request.getAttribute("searchGroupShowAction");

 	String groupId = (String) request.getAttribute("groupId");
%>
<style>
.no-action-td {
	height: 300px;
	font-size: 50px;
	line-height: 300px;
	text-align: center;
	color: #888;
}
form{
  width:800px;
}
input[type=text]{
  width:800px;
}
</style>

<div class="table-responsive">
	<h3 class="h4">参加ユーザー</h3>
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>住所</th>
				<th>メールアドレス</th>
				<th>電話番号</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${ groupShowUserList }">
				<tr>
					<td><c:out value="${user.getUserId()}" /></td>
					<td><c:out value="${user.getName()}" /></td>
					<td><c:out value="${user.getAddress()}" /></td>
					<td><c:out value="${user.getEmail()}" /></td>
					<td><c:out value="${user.getTel()}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">行動記録</h1>
	<form action="/ActionLogger/searchaction" method="get">
		<div class="form-group">
			<input type="text" class="form-control col-7" name="search"
				placeholder="日付や場所名で検索できます。 例）2020-07-01 , 学校 ">
			<input type="hidden" name="groupId" value="<c:out value="${groupId}"/>">
		</div>
	</form>
</div>

<div class="table-responsive">
	<table class="table table-striped table-sm">
		<thead>
			<tr>
				<th>ユーザーネーム</th>
				<th>日付</th>
				<th>時刻</th>
				<th>場所</th>
				<th>理由</th>
				<th>備考</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${groupShowList != null or searchGroupShowAction!= null}">
				<c:choose>
					<c:when test="${searchGroupShowAction != null}">
						<c:forEach var="action" items="${ searchGroupShowAction }">
							<tr>
								<td><c:out value="${action.getUser().getName()}" /></td>
								<td><c:out value="${action.getAction().getStart_date()}" /></td>
								<td><c:out value="${action.getAction().getStart_time()}" />
									~ <c:out value="${action.getAction().getFinish_time()}" /></td>
								<td><c:out value="${action.getAction().getAction_place()}" /></td>
								<td><c:out value="${action.getAction().getAction_reason()}" /></td>
								<td><c:out value="${action.getAction().getAction_remarks()}" /></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="action" items="${ groupShowList }">
							<tr>
								<td><c:out value="${action.getUser().getName()}" /></td>
								<td><c:out value="${action.getAction().getStart_date()}" /></td>
								<td><c:out value="${action.getAction().getStart_time()}" />
									~ <c:out value="${action.getAction().getFinish_time()}" /></td>
								<td><c:out value="${action.getAction().getAction_place()}" /></td>
								<td><c:out value="${action.getAction().getAction_reason()}" /></td>
								<td><c:out value="${action.getAction().getAction_remarks()}" /></td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>	
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7" class="no-action-td">現在は登録されていません</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
