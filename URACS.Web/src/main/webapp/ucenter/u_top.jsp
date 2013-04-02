<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"  %>

    <script type="text/javascript">
    
    function changePwd(){
    	$.jBox("iframe:<s:url value='/u/changePwd'/>", {
    	    title: "修改密码",
    	    width: 700,
    	    height: 320,
    	    buttons: {}
    	});
    }
    
    </script>
    
        <table width="100%" cellpadding="0" cellspacing="0" border="0" class="header">
            <tr>
                <td width="5"></td>
                <td>
                    <a href="javascript:pilicat.switchbar();" onfocus="this.blur();" class="button" id="switch">关闭菜单</a>
                    <a href="javascript:void(0);" onfocus="this.blur();" class="button" id="menu_option">快捷菜单</a>
                    <span class="top_menu options">
                        <a href="<s:url value='/u/main'/>">管理首页</a>
                        <a href="javascript:;" onclick="changePwd();">修改密码</a>
                        <a href="<s:url value='/u/logout'/>">退出系统</a>
                    </span>
                    	                        
                    </td>
                    <td width="5"></td>
                    <td>

                     
                    </td>
	                <td class="right">
	                    
	                </td>
            </tr>
        </table>
        