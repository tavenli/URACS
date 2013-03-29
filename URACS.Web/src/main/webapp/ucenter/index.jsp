<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/include_tag.jsp" %>
<%@ include file="../common/page_var.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><%=pageTitle%> - 用户中心</title>

    <%@ include file="../common/page_head.jsp" %>

    <style type="text/css">
        .center_top {
            border-bottom: 1px #CCC solid;
            height: 30px;
            padding: 0 0 0 10px;
            font-weight: bold;
            background: url(<s:url value='/images/content_box_bg.gif'/>) #F6F6F6;
            width: 100%;
        }

        .center {
            margin: 10px auto;
        }

            .center .news {
                width: 100%;
            }

                .center .news .news_top {
                    border-bottom: 1px #CCC solid;
                    height: 30px;
                    padding: 0 0 0 10px;
                    font-weight: bold;
                    background: url(<s:url value='/images/content_box_bg.gif'/>) #F6F6F6;
                    width: 100%;
                }

                .center .news .row {
                    padding: 5px 10px 15px 10px;
                }

                    .center .news .row table {
                        width: 100%;
                    }

                        .center .news .row table td {
                            height: 30px;
                            line-height: 30px;
                            background: url( <s:url value='/images/0101.gif'/> ) repeat-x bottom;
                        }

        .welcome {
            padding: 0 10px;
        }

            .welcome td {
                word-break: break-all;
            }
        /* 防止禁用的函数过多导致表格变形 */
        .server td {
            height: 30px;
            line-height: 30px;
            padding: 0 0 0 10px;
        }

        .attention, .information {
            margin: 0 0 10px 0;
        }

            .attention a, .information a {
                text-decoration: underline;
                color: #F00;
            }
    </style>

	 <script type="text/javascript">        
        $().ready(function(){
        	$('.server tr:odd').addClass('odd');
        	$('.server tr:even').addClass('even');
            pilicat.title2div('title2div');

        });
    </script>
    
</head>
<body>
    <div style="height: 60px; overflow: hidden">
		<%@ include file="u_top.jsp" %>
    </div>
    <div id="frame_side">
        <%@ include file="u_login_info.jsp" %>

		<jsp:include page="u_left_menu.jsp"  flush="true">
        	<jsp:param name="current" value="main"/>
       	</jsp:include>

    </div>
    <div id="body_box">
        <table cellpadding="0" cellspacing="0" border="0" class="icon">
            <tr>
                <td>
                <a href="<s:url value='/u/main'/>">
                    <img src="<s:url value='/icon/paper.png'/>" /><br />
                    快捷功能1
                 </a>
                 <a href="<s:url value='/u/main'/>">
                 	<img src="<s:url value='/icon/member.png'/>" /><br />
                 	快捷功能2
                  </a>
                  <a href="<s:url value='/u/main'/>">
                 	<img src="<s:url value='/icon/set_1.png'/>" /><br />
                 	快捷功能3
                  </a>
                   <a href="<s:url value='/u/main'/>">
                 	<img src="<s:url value='/icon/user.png'/>" /><br />
                 	快捷功能4
                  </a>
                 </td>
            </tr>
        </table>
        <div class="rounded table">
            <table width="100%" cellpadding="0" cellspacing="0" border="0" class="box_top">
                <tr>
                    <td class="title">欢迎 <font color="#146295"><sec:authentication property="principal.username"/></font> 进入用户中心</td>
                    <td>
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <div class="error rounded top_error hide" id="client_security"></div>
            <table class="table center" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="top">
                        <table cellpadding="0" cellspacing="1" border="0" width="100%" class="rounded">
                             <tr>
                                 <td class="center_top">用户基本信息</td>
                             </tr>
                             <tr>
                               <td class="welcome">
                                    <table cellpadding="0" cellspacing="0" border="0" class="server" width="100%">
                                        <tr>
                                            <td>您好：<sec:authentication property="principal.username"/>
											<sec:authorize access="hasRole('ROLE_SUPER')">
									         ，您是 <span style="color:red;">超级用户</span>
									        </sec:authorize>
									        ！ 
                                            &nbsp;&nbsp;&nbsp;&nbsp; [当前登录IP：<span id="ip">${userIp}</span> ] 
                                            &nbsp;&nbsp;&nbsp;&nbsp; <a href="<s:url value='/u/logout'/>">退出登录</a></td>
                                        </tr>
                                        <tr>
                                            <td>
                                            	
                                            	用户类型：
                                            	<font color='blue'>屌丝</font>
												&nbsp;&nbsp;&nbsp;&nbsp; 
                                            	信用：<span class="green">0</span>  分												
                                            	&nbsp;&nbsp;&nbsp;&nbsp; 
                                            	等级：<span class="green">0</span>  级
                                            	
                                            </td>
                                        </tr>
                                        
                                        <tr>
                                            <td>
                                            	是否高帅富：
                                            	<font color='gray'>否</font>
                                            </td>
                                        </tr>
                                        
                                        
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <!-- //  -->
                        <table cellpadding="0" cellspacing="1" border="0" width="100%" class="rounded" style="margin: 10px 0;">
                            <tr>
                                <td class="center_top">统计信息</td>
                            </tr>
                            <tr>
                                <td class="welcome">
                                    <table cellpadding="0" cellspacing="0" border="0" class="server" width="100%">
                                        <tr></tr>
                                        <tr>
                                            <td>
                                            	用户总数： <span class="red">${totalUserCount }</span> 个
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                            	日志总数： <span class="red">0</span> 条
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        
                        <div class="information rounded">
                            <ol></ol>
                                                        
                            稳定、及时、精准 是我们永远的追求。
	                        
                        </div>
                        
                    </td>
                    
                </tr>
            </table>
        </div>

        <%@ include file="u_bottom.jsp" %>
        
    </div>
</body>
</html>
