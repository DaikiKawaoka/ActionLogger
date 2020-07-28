<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
h4{
	padding-top:40px;
	color:red;
}
</style>	
	
<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">グループ参加</h1>
</div>

<form action="/ActionLogger/addbelongsgroup" method="post">
  <div class="form-group">
    <label>グループのIDを入力してください</label>
    <input type="text" class="form-control col-7" name="groupId">
  </div>
  <button type="submit" class="btn btn-primary">参加</button>
</form>
<h4>自分が管理しているグループに参加すると自分の行動履歴も一緒に見れるようになります。</h4>