<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/include_tag.jsp" %>
<%@ include file="../common/page_var.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><%=pageTitle%> - 无操作权限</title>

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
        	<jsp:param name="current" value="notPermission"/>
       	</jsp:include>

    </div>
    <div id="body_box">
        <table cellpadding="0" cellspacing="0" border="0" class="icon">
            <tr>
                <td>
                &nbsp;
                 </td>
            </tr>
        </table>
        <div class="rounded table">
 
             <table width="100%" cellpadding="0" cellspacing="0" border="0" class="">
                <tr>
                   
                    <td>
                        <table cellpadding="0" cellspacing="0" border="0">
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            
            
 			<div class="attention rounded">
	       		<ol></ol>
	       		
		        <c:choose>
					<c:when test="${authErrorCode==1003}">
						无该功能模块操作权限。
					</c:when>
					<c:otherwise>
						无该功能模块操作权限。
					</c:otherwise>
				</c:choose>
	            	
	                     
			</div>
			
        </div>

        <%@ include file="u_bottom.jsp" %>
        
    </div>
</body>
</html>
