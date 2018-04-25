<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
href="${pageContext.request.contextPath}/resources/style
/overallstyle.css" media="screen"/>
<link type="text/css" rel="stylesheet" 
href="${pageContext.request.contextPath}/resources/style
/draftmanagerstyle.css" media="screen"/>
<title>기안문서상태관리</title>
</head>
<body>
<%@include file="/WEB-INF/importviews/header.jsp"%>
<%@include file="/WEB-INF/importviews/aside.jsp"%>
<%@include file="/WEB-INF/importviews/nav.jsp"%>

<section>
<div id="maincontent">
	<div id="managerlist">
	</div>
	<div class="pagination">
  		<a href="#">&laquo;</a>
 		<a href="#">1</a>
 		<a href="#" class="active">2</a>
  		<a href="#">3</a>
  		<a href="#">4</a>
  		<a href="#">5</a>
 		<a href="#">6</a>
 		<a href="#">&raquo;</a>
	</div>
</div>
</section>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var doccount = ${doccount};
	var docpks = ${pkarrays};
	
	$(document).ready(function() {
		if(doccount!=0){
			yesdoc();
		}else{
			nodoc();
		}
	});
	
	function yesdoc(){
		$.ajax({
			url: "/makedoclists.ajax?docpks="+docpks,
			type: "get",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			async: false,
			success:function(data){
				alert(data);
			}
		});
	}
	
	function nodoc(){
		$("#managerlist").append('<h1>기안된 문서가 없습니다!</h1>');
	}
	
	
</script>
</body>
</html>