<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="common/include_tag.jsp" %>
<%@ include file="common/page_var.jsp" %>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出现了异常</title>

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
	
			<div class="error rounded">
	       		<ol></ol>
	            非常抱歉，您执行的操作出现了异常，请联系我们。
	            <br/>
	            电子邮件：service@pilicat.com
				<br/>
				技术支持旺旺：
				<a target="_blank" href="http://www.taobao.com/webww/ww.php?ver=3&touid=%E8%BF%9C%E5%8F%8B%E7%A7%91%E6%8A%80&siteid=cntaobao&status=1&charset=utf-8">
				<img border="0" src="http://amos.alicdn.com/realonline.aw?v=2&uid=%E8%BF%9C%E5%8F%8B%E7%A7%91%E6%8A%80&site=cntaobao&s=1&charset=utf-8" alt="点击这里给我发消息" />
				</a>	                  	
	                     
			</div>

	</div>

	<%@ include file="bottom.jsp" %>
    
</body>
</html>