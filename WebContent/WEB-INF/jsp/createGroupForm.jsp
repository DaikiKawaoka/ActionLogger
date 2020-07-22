<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
	<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">グループ作成</h1>
</div>

<form action="/ActionLogger/creategroup" method="post">
  <div class="form-group">
    <label>グループの名前</label>
    <input type="text" class="form-control col-7" name="groupName">
  </div>
  <button type="submit" class="btn btn-primary">送信</button>
</form>