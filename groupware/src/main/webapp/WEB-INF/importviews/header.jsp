<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
href="${pageContext.request.contextPath}/resources/style
/headerstyle.css" media="screen"/>
<title>import header</title>
</head>
<body>
<header>
<h1>TestGroupware</h1>
<ul class="topmenu" id="notlogin">
<li>안녕하세요!</li>
<li>알림</li>
<li><a href="join">회원가입</a></li>
<li><a href="login">로그인</a></li>
</ul>
<ul class="topmenu" id="logined">
<li><%=session.getAttribute("id")%>님</li>
<li>알림</li>
<li>회원정보수정</li>
<li>로그아웃</li>
</ul>
</header>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
var loginedid = "<%=(String)session.getAttribute("id")%>";

$(document).ready(function() {
	if(loginedid!="null"&&loginedid!=""){
		alert("회원님 환영합니다!");
		$('#notlogin').css('display','none');
	}else{
		$('#logined').css('display','none');
	}
});
</script>
</html>