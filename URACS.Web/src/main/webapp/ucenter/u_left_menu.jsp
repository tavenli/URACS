<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tavenli.model.MenuInfo"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"  %>

<%
String current = request.getParameter("current");

//菜单加载，我放在session中，如果你需要考虑session过期的问题，也可以放在一个缓存或静态对象中
//或者每次都去数据库读取也行，但是不推荐每次去读取
List<MenuInfo> menus = new ArrayList<MenuInfo>();
Object obj = request.getSession().getAttribute("menus");
if(obj!=null){
	menus = (List<MenuInfo>)request.getSession().getAttribute("menus");
}
%>
        
        <div id="menu">
        
	        <%
	          for(MenuInfo menu : menus){
	        %>        
            <ol>
                <a href="javascript:void(0);" onfocus="this.blur();" class="item"><%=menu.getMenuName() %></a>
                <ul>
                    <%
                    
                     for(MenuInfo childMenu : menu.getChildMenus()){
                    %>
                    <li><a href="<s:url value='<%=childMenu.getMenuUrl() %>'/>"  onfocus="this.blur();" <%=childMenu.getMenuCode().equals(current)?" id='current'":"" %>><%=childMenu.getMenuName() %></a></li>
                    <%
                    }
                    %>
                </ul>
            </ol>
            <%
            }
            %>
                            
        </div>
        
        <script type="text/javascript">
        $().ready(function(){
        	pilicat.menu();
        });
        
        </script>