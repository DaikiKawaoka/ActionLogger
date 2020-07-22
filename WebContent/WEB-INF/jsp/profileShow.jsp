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
	<h1 class="h2" style="width:1000px; text-align:center;">プロフィール</h1>
</div>

<div style="width:1000px;">
<table class="table table-dark" style="width: 550px; margin: 0 auto;">
  <tbody>
    <tr>
      <th scope="row"style="width:200px; border-right:solid 1px #ccc;">ユーザーID</th>
      <td style="width:350px; padding-left:20px;"><c:out value="${userid}"/></td>
    </tr>
    <tr>
      <th scope="row" style="border-right:solid 1px #ccc;">氏名</th>
      <td style="width:350px; padding-left:20px;"><c:out value="${user_name}"/></td>
    </tr>
    <tr>
      <th scope="row" style="border-right:solid 1px #ccc;">メールアドレス</th>
      <td style="width:350px; padding-left:20px;"><c:out value="${user_email}"/></td>
    </tr>
    <tr>
      <th scope="row" style="border-right:solid 1px #ccc;">電話番号</th>
      <td style="width:350px; padding-left:20px;"><c:out value="${user_tel}"/></td>
    </tr>
    <tr>
      <th scope="row" style="border-right:solid 1px #ccc;">住所</th>
      <td style="width:350px; padding-left:20px;"><c:out value="${user_adr}"/></td>
    </tr>
  </tbody>
</table>
</div>