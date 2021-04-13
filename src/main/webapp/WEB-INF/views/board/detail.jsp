<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>

	<div class="container">
		
		<button class="btn btn-secondary" onclick="history.back()">목록</button>
		<button id="btn-update" class="btn btn-secondary">수정</button>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
		<br/><br/>
		<div>
			글 번호 : <span id="id"><i>${board.id} </i></span><br/>
			작성자 : <span><i>${board.user.username} </i></span>
		</div>
		<br/><hr>
		<div>
			<label for="title">제목</label> 
			<h3>${board.title}</h3>
		</div>
		<br/><hr>
		<div>
  			<label for="content">내용</label>
 			<div>${board.content}</div>
		</div>
	</div>

    <script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>	



