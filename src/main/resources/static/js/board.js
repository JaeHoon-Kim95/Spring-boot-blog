let index = {
	init : function(){
		$("#btn-save").on("click",()=>{ // function(){}안쓰고 ()=>쓰는 이유는 this를 바인딩하기 위해서
			this.doPost();
		});
		$("#btn-delete").on("click",()=>{ // function(){}안쓰고 ()=>쓰는 이유는 this를 바인딩하기 위해서
			this.doDelete();
		});
		$("#btn-update").on("click",()=>{ // function(){}안쓰고 ()=>쓰는 이유는 this를 바인딩하기 위해서
			this.doUpdate();
		});
		$("#btn-reply-save").on("click",()=>{
			this.replyPost();
		});
		
	},
	doPost : function(){
		//alert("user의 save함수 호출");
		let data ={
			title : $("#title").val(),
			content : $("#content").val()
			
		};

		$.ajax({
			type:"POST",
			url:"/api/board",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json" 
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href="/";
		}).fail(function(){
			alert(JSON.stringify(error));
		}); 
	},
	doDelete : function(){
		let id = $("#id").text();	
		$.ajax({
			type:"DELETE",
			url:"/api/board/"+id,
			dataType:"json",
			contentType:"application/json; charset=utf-8"  
		}).done(function(){
			alert("삭제가 완료되었습니다.");
			location.href="/";
		}).fail(function(){
			alert("권한이 없습니다.");
		}); 
	},
	doUpdate : function(){
		let id= $("#id").val();
		
		let data ={
			title : $("#title").val(),
			content : $("#content").val()
			
		};

		$.ajax({
			type:"PUT",
			url:"/api/board/"+id,
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json" 
		}).done(function(){
			alert("글수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(){
			alert("권한이 없습니다.");
		}); 
	},
	
	replyPost : function(){

		let data ={
			userId:$("#userId").val(),
			boardId:$("#boardId").val(),
			content:$("#reply-content").val()
		};
		
		
		$.ajax({
			type:"POST",
			url:"/api/board/${boardId}/reply",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8", 
			dataType:"json" 
		}).done(function(resp){
			alert("댓글 작성이 완료되었습니다.");
			location.href=`/board/${data.boardId}`;
		}).fail(function(){
			alert(JSON.stringify(error));
		}); 
	},
	
	replyDelete : function(boardId, replyId){	
		$.ajax({
			type:"DELETE",
			url:`/api/board/${boardId}/reply/${replyId}`,
			dataType:"json",
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href=`/board/${boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}
}

index.init();