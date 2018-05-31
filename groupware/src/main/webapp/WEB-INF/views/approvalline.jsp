<%@page import="java.util.ArrayList"%>
<%@page import="com.groupware.vo.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
href="${pageContext.request.contextPath}/resources/style
/overallstyle.css" media="screen"/>
<link type="text/css" rel="stylesheet" 
href="${pageContext.request.contextPath}/resources/style
/approvallinestyle.css" media="screen"/>
<title>결재라인관리</title>
</head>
<body>
	<%@include file="/WEB-INF/importviews/header.jsp"%>
	<%@include file="/WEB-INF/importviews/aside.jsp"%>
	<%@include file="/WEB-INF/importviews/nav.jsp"%>
	<section>
		<form id="frm" action="/insertline" method="post">
		<div id="maincontent">
			<div id="submenu">		
				<input type="text" value="결재라인제목을입력해주세요" name="apl_name" id="linename"/>
				<select id="linekinds">
				<c:forEach items="${docmap}" var="i">
		 	 		<c:set var="title" value="${i.key}"/>
		 	 		<c:set var="pk" value="${i.value}"/>
	         		<option value="${pk}">
	         		<c:out value = '${title}'/>
	         		</option>
     			</c:forEach>
				</select>
				<div id="linepreview">
				
				</div>
			</div>
			<div id="mainmenu">
				<div id="searchbar">
					<select id="searchoptions">
     				<option value="emp">직원명</option>
       				<option value="dep">부서명</option>
					</select>
					<input type="text" id="searchcontent" name="searchcontent" value="이준용"/>
					<input type="button" id="searchbtn" value="검색" onclick="search()"/>
				</div>
				<div id="searchresult">
					<table class="searchsort">
    				<thead>
    				<tr>
     			   	<th>선택</th>
        			<th>성명</th>
        			<th>부서</th>
        			<th>직급</th>
        			</thead>
        			<tbody id="resultlist">
        			<!-- 검색결과가 뿌려진다 -->
        			</tbody>
        			</table>
        			<input type="button" value="선택된직원추가" id="addemp"/>
    				<input type="button" value="전체선택하기" id="selectall"/>
    				<input type="button" value="전체해제/삭제하기" id="deselectall"/>
				</div>
				<textarea id="selectedemp" name="apl_content">
				
				</textarea>
				<div id="createline">
					<input type="text" id="linememo" name="apl_memo" value="결재라인에대한메모를남겨주세요"/>
					<input type="button" id="savebtn" value="결재라인저장" onclick="insertline()"/>
				</div>
			</div>
		</div>
		</form>
	</section>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var values = [];
	//0.OnReady
	$( document ).ready(function() {
		
	});

	
	//1.검색메서드
	function search(){
		var searchoption = $('#searchoptions').val();
		var searchnamme = $('#searchcontent').val();
		var searchinfos = new Array();
		
		if(searchnamme!=""){
			searchinfos.push(searchoption);
			searchinfos.push(searchnamme);
			$.ajax({
				url: "/linesearch.ajax?searchinfos="+searchinfos,
				type: "get",
				async:false,
				success:function(data){
					if(data==""){
						alert("검색결과가 존재하지 않습니다.");
					}else{
						values = data;
						$('#resultlist').empty();
						$.each(values,function(index){
						var appendElement = $("#resultlist");
						var appendStr = "";
						
						appendStr = "<tr id=result"+index+"></tr>"
						appendElement.append(appendStr);
						
						appendElement = $("#result"+index);
						appendStr =  "<input class='checkemp' id='check"+index+"' type='checkbox'"+ 
									  "style='width:13px;margin-top:7px;'>";
						appendElement.append(appendStr);
						
						$.each(values[index],function(key,value){
							if(key=="user_name"||key=="dep_name"||key=="rank_name"){
							appendElement = $("#result"+index);
							appendStr = "<td>"+value+"</td>";
							appendElement.append(appendStr);
							}
						});
						});
					}
				},error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+status+"\n"+"error:"+error+"데이터 결과값이 없습니다!");
				}	
			});
		}else{
			alert("검색할 단어를 입력해주세요");
			$('#searchcontent').focus();
		}
	}
	
	$('#addemp').click(function(){
		checkedEmployee();
	});
	
	$('#selectall').click(function(){
		$('.checkemp').attr("checked", true);
	});
	
	$('#deselectall').click(function(){
		$('#selectedemp').empty();
		$('.checkemp').attr("checked", false);
	});

	//2.직원정보 체크박스 메서드
	function checkedEmployee(){
		 $('input:checkbox[class="checkemp"]').each(function(index) {
		      if(this.checked == true){//checked 처리된 항목의 값
		    	  getCheckedEmployee($(this).attr('id'));
		      }
		 });
	}
	
	//3.직원정보 로드 메서드
	function getCheckedEmployee(element){
		var num = element.replace("check","");
		var numarray = [];
		numarray.push(num);
		
		$.each(numarray,function(index){
			var appendElement = $("#selectedlist");
			var appendStr = new Array();
			
			$.each(values[num],function(key,value){
				if(key=="user_name"||key=="dep_name"||key=="rank_name"){
					var appendElement = $("#selectedemp");
					appendStr.push(value);	
				}
			});
			for(var i=0;i<appendStr.length;i++){
				console.log(appendStr[i]);
			}
			$('#selectedemp').val(appendStr);
		});
	}
	
	//4.form submit
	function insertline(){
		$('#frm').submit();
   }
	
</script>
</html>