<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	
	<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h2">Submit</h1>
</div>
	<form action="/ActionLogger/addaction" method="post">
    <div class="form-group row">
      <label>外出日時</label>
      <div class="col-md-3">
       <input type="date" class="form-control" name="start_date">
       </div>
       <div class="col-md-3">
              <input type="time" class="form-control" name="start_time">
       </div>
    </div>
    <div class="form-group row">
      <label>帰宅日時</label>
      <div class="col-md-3">
       <input type="date" class="form-control" name="finish_date">
       </div>
       <div class="col-md-3">
              <input type="time" class="form-control" name="finish_time">
       </div>
    </div>
  <div class="form-group">
    <label>場所</label>
    <input type="text" class="form-control col-7" name="action_place">
  </div>
  <div class="mb-3">
    <label for="validationTextarea">理由</label>
    <textarea class="form-control col-7" required  name="action_reason"></textarea>
  </div>
  <div class="mb-3">
    <label for="validationTextarea">備考</label>
    <textarea class="form-control col-7" name="action_remarks"></textarea>
  </div>
  <button type="submit" class="btn btn-primary">送信</button>
</form>