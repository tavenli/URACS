<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="common/include_tag.jsp" %>
<%@ include file="common/page_var.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=pageTitle%></title>

	<link rel="stylesheet" type="text/css" href="<s:url value='/css/common_green.css'/>" />

	<style type="text/css">
		.update{margin:0 auto; padding:0; width:98%;}
		.update td{margin:0; height:30px; padding:5px;}
		.update .name{text-align:right;}
		.title_div{width:350px;}
		
		body {
		background: url(<s:url value='/images/gray_bg.png'/>) 0 0 repeat-y #F6F6F6;
		}
	</style>
	
</head>
<body>

	<div class="rounded table">
	
			<div class="information rounded">
	       		<ol></ol>
	            系统成功退出！	                  	
	            <a href="<s:url value='/login'/>">重新登录</a>
			</div>
	 
	
	</div>

	<%@ include file="bottom.jsp" %>

</body>
</html>