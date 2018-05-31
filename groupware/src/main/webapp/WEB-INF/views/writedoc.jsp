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
/writedocstyle.css" media="screen"/>
<title>문서작성</title>
</head>
<body>
<%@include file="/WEB-INF/importviews/header.jsp"%>
<%@include file="/WEB-INF/importviews/aside.jsp"%>
<%@include file="/WEB-INF/importviews/nav.jsp"%>
<section>
	<div id="maincontent">
	<form id="form" action="/insertdoc" method="get">
	<input type="hidden" id="user_ai" name="user_ai" value="${userai}"/>
	<input type="hidden" id="dep_ai" name="dep_ai" value="${depai}"/>
	<input type="hidden" id="rank_ai" name="rank_ai" value="${rankai}"/>
	<input type="hidden" id="draft_important" name="draft_important" value="0"/>
	<input type="hidden" id="draft_temp" name="draft_temp" value="0"/>
	제목:
	<input type="text" name="draft_name" style="width: 660px"/><br>
	문서종류: 
	<select name="draft_type" style="width: 400px">
    	<option value="">문서선택</option>
    	<option value="vacation">휴가</option>
    	<option value="report" selected="selected">보고서</option>
    	<option value="etc">기타</option>
	</select>
	<textarea rows="10" cols="30" id="doceditor" name="draft_content">
	</textarea>
	<input type="button" value="수정취소" id="cancel" onclick="cancel()">
	<input type="button" value="임시저장" id="temp" onclick="tempsave()">
	<input type="button" value="중요문서" id="impdoc" onclick="importantdoc()">
	<input type="button" value="저장완료" id="done" onclick="savedoc()">
	</form>
	</div>
</section>
</body>
<script src="/resources/SEditor/js/HuskyEZCreator.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   var editor_object = [];
   var textAreaName = document.URL.substring(document.URL.lastIndexOf("/") + 1,
         document.URL.length);
   
   //로그인된 사용자의 정보를 hidden form으로 보내기
   $(document).ready(function() {
			
	});
   
   $(function() {
      nhn.husky.EZCreator.createInIFrame({
         oAppRef : editor_object,
         elPlaceHolder : "doceditor",
         sSkinURI : "/resources/SEditor/SmartEditor2Skin.html?textAreaName=" + textAreaName,
         htParams : {
            bUseToolbar : true,
            bUseVerticalResizer : false,
            bUseModeChanger : true
         },
         fOnAppLoad : function() {
            editor_object.getById["SEditor"].exec("PASTE_HTML", [ "" ]);
         },
         fCreator : "createSEditor2"
      });
   });
   
   function importantdoc(){
	   var alertMsg = "";
	   var draft_important = $('#draft_important').val();
	   
	   if(draft_important!=0){
		   alertMsg = "중요문서 지정을 해제합니다";
		   draft_important = 0;
	   }else{
		   alertMsg = "중요문서로 지정합니다";
		   draft_important = 1;
	   }
	   $('#draft_important').val(draft_important);
	   alert(alertMsg);
   }
   
   function tempsave(){
	   var alertMsg = "";
	   var draft_temp = $('#draft_temp').val();
	   
	   if(draft_temp!=0){
		   alertMsg = "임시저장을 취소합니다.";
		   draft_temp = 0;
	   }else{
		   alertMsg = "임시저장합니다.";
		   draft_temp = 1;
	   }
	   $('#draft_temp').val(draft_temp);
	   alert(alertMsg);
   }
   
   function savedoc(){
	   editor_object.getById["doceditor"].exec("UPDATE_CONTENTS_FIELD", [ ]);
		$('#form').submit();
   }
   
   function cancel(){
	
   }
</script>
</html>