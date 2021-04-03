<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form>
		<div class="form-group">
			<label for="userName">Username:</label> <input type="text" class="form-control" placeholder="Enter Username" id="userName">
		</div>
		
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" placeholder="Enter password" id="passWord">
		</div>
		
		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">회원가입</button>
</div>

<script src="/myBlog/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>



