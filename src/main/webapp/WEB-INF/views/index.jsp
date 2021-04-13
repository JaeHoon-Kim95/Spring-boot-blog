<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
<div class="container">

<c:forEach var="board" items="${boards.content}">
  <div class="card m-3">   
    <div class="card-body">
      <h4 class="card-title">${board.title}</h4>
      <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
    </div>
  </div>
 </c:forEach>
 <ul class="pagination justify-content-center">
 <c:choose>
 	<c:when test="${boards.number <= 4}">
 		<li class="page-item disabled"><a class="page-link" href="?page=${startBlockPage-6}">Previous</a></li>
 	</c:when>
 	<c:otherwise>
 		<li class="page-item"><a class="page-link" href="?page=${startBlockPage-6}">Previous</a></li>
 	</c:otherwise>
 </c:choose>
 
 <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
 	<c:choose> 
 		<c:when test="${boards.pageable.pageNumber+1 == i}"> 
 			<li class="page-item disabled">
 				<a class="page-link" href="?page=${i-1}">${i}</a>
 			</li> 
 		</c:when>
 		<c:otherwise> 
 			<li class="page-item">
 				<a class="page-link" href="?page=${i-1}">${i}</a>
 				</li> 
 		</c:otherwise> 
 	</c:choose>
 </c:forEach>  

  
 <c:choose>
 	<c:when test="${boards.last}">
 		<li class="page-item disabled"><a class="page-link" href="?page=${startBlockPage+4}">Next</a></li>
 	</c:when>
 	<c:otherwise>
 		<li class="page-item"><a class="page-link" href="?page=${startBlockPage+4}">Next</a></li>
 	</c:otherwise>
 </c:choose>
</ul>
  <br/>
</div>

<%@ include file="layout/footer.jsp" %>	



