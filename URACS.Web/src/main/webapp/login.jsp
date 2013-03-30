<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="common/include_tag.jsp" %>
<%@ include file="common/page_var.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=pageTitle%>-用户登录</title>

	<link rel="stylesheet" type="text/css" href="<s:url value='/css/common_green.css'/>" />
	
    <script type="text/javascript" src="<s:url value='/js/jquery.min.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
    


	<style type="text/css">
		.update{margin:0 auto; padding:0; width:98%;}
		.update td{margin:0; height:30px; padding:5px;}
		.update .name{text-align:right;}
		.title_div{width:350px;}
		
		body {
		background: url(<s:url value='/images/gray_bg.png'/>) 0 0 repeat-y #F6F6F6;
		}
	</style>
	
    <script type="text/javascript" >

		function reloadVerifyCode()
		{
			$("#verifyImage").attr("src","<s:url value='/getVerifyCode'/>?random="+Math.random());
		}
		
		jQuery(document).ready(function(){
			//每次加载页面后重新加载验证码	
			//reloadVerifyCode();
		});
		
		function checkForm(){
			var userName = $("#userName").val();
			var passWord = $("#passWord").val();
			var verifyCode =  $("#verifyCode").val();
			
			if(userName.length>0&&passWord.length>0&&verifyCode.length>0){
				
				return true;
			}else{
				return false;
			}
			
			
		}
		
	</script>

</head>
<body>

	<div class="rounded table">
	
			<div class="attention rounded">
	       		
						<form id="form1" action="<s:url value='/doLogin'/>" method="post">
                            <table class="table table-bordered table-striped" align="center"  style="width: 310px;">
                                    
                                    <tr>
                                        <td width="100">
                                        	<span>
                                        	用户名:
                                        	</span>
                                        </td>
										<td>
                                        	<input type="text" id="userName" name="userName"  class="input-xlarge"  value="" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100">
                                        	<span>
                                        	密  码:
                                        	</span>
                                        </td>
										<td>
                                        	<input type="password" id="passWord" name="passWord"  class="input-xlarge"  value="" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100">
                                        	<span>
                                        	验证码:
                                        	</span>
                                        </td>
										<td>
                                        	<input type="text" id="verifyCode" name="verifyCode" size="5" maxlength="5" class="code_box"/>
											<img id="verifyImage"  src="<s:url value='/getVerifyCode'/>"  align="middle" style="cursor:pointer;"  onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />

                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" >
                                        	<button class="btn btn-primary" type="submit" onclick="return checkForm();">登录</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" >
                                        	<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
                                        	<span style="color:red;">
                                        	登录失败：
											${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
											</span>
											</c:if>
											<!-- 
											用户名不存在:UsernameNotFoundException; 
											密码错误:BadCredentialException; 
											帐户被锁:LockedException; 
											帐户未启动:DisabledException; 
											密码过期:CredentialExpiredException;等等!
											
											注：
											当用户不存在时，也是提示 Bad credentials (密码错误)，这是为了安全性的默认设置，防止暴力猜测用户名
											 -->
                                        </td>
                                    </tr>
				
                            </table>
                            </form>

			</div>
	 
	
	</div>

	<%@ include file="bottom.jsp" %>
	
</body>
</html>