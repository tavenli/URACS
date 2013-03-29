<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"  %>

<%
String current = request.getParameter("current");

List<String[]> rate_menu = new ArrayList<String[]>();
rate_menu.add(new String[]{"autoRateSet","自动评价全局设置","/u/rate/autoRateSet"});
rate_menu.add(new String[]{"autoRateLog","自动评价日志","/u/rate/autoRateLog"});
rate_menu.add(new String[]{"ratedList","已评价订单","/u/rate/ratedList"});
rate_menu.add(new String[]{"revNeutralRateList","收到中评的订单","/u/rate/revNeutralRateList"});
rate_menu.add(new String[]{"revBadRateList","收到差评的订单","/u/rate/revBadRateList"});



List<String[]> rate_pro_menu = new ArrayList<String[]>();
rate_pro_menu.add(new String[]{"notRateList","手工批量评价","/u/rate/notRateList"});
rate_pro_menu.add(new String[]{"alarmSet","中差评告警设置","/u/rate/alarmSet"});
//rate_pro_menu.add(new String[]{"buyerFilterSet","黑名单设置","/u/rate/buyerFilterSet"});
rate_pro_menu.add(new String[]{"subUserList","子帐号管理","/u/sys/subUserList"});

List<String[]> rate_adv_menu = new ArrayList<String[]>();
rate_adv_menu.add(new String[]{"tradeCloseSet","黑名单自动关闭订单","/u/rate/tradeCloseSet"});

List<String[]> mobile_menu = new ArrayList<String[]>();
//mobile_menu.add(new String[]{"mobileNumList","手机号码列表","/u/mobile/mobileNumsList"});
//mobile_menu.add(new String[]{"mobilePortal","买家号码查询","/u/mobile/mobilePortal"});

List<String[]> sys_menu = new ArrayList<String[]>();
sys_menu.add(new String[]{"userLogList","用户操作日志","/u/sys/userLogList"});

%>
        
        <div id="menu">
            <ol>
                <a href="javascript:void(0);" onfocus="this.blur();" class="item">自动评价·基本功能</a>
                <ul>
                    <%
                     for(String[] menu : rate_menu){
                    %>
                    <li><a href="<s:url value='<%=menu[2] %>'/>"  onfocus="this.blur();" <%=menu[0].equals(current)?" id='current'":"" %>><%=menu[1] %></a></li>
                    <%
                    }
                    %>
                </ul>
            </ol>
            
            <ol>
                <a href="javascript:void(0);" onfocus="this.blur();" class="item">自动评价·高级智能</a>
                <ul>
                    <%
                     for(String[] menu : rate_pro_menu){
                    %>
                    <li><a href="<s:url value='<%=menu[2] %>'/>"  onfocus="this.blur();" <%=menu[0].equals(current)?" id='current'":"" %>><%=menu[1] %></a></li>
                    <%
                    }
                    %>
                </ul>
            </ol>
            
<%--             <ol>
                <a href="javascript:void(0);" onfocus="this.blur();" class="item">自动评价·高级防御</a>
                <ul>
                    <%
                     for(String[] menu : rate_adv_menu){
                    %>
                    <li><a href="<s:url value='<%=menu[2] %>'/>"  onfocus="this.blur();" <%=menu[0].equals(current)?" id='current'":"" %>><%=menu[1] %></a></li>
                    <%
                    }
                    %>
                </ul>
            </ol> --%>

<%--             <ol>
                <a href="javascript:void(0);" onfocus="this.blur();" class="item">手机选号管理</a>
                <ul>
                    <%
                     for(String[] menu : mobile_menu){
                    %>
                    <li><a href="<s:url value='<%=menu[2] %>'/>"  onfocus="this.blur();" <%=menu[0].equals(current)?" id='current'":"" %>><%=menu[1] %></a></li>
                    <%
                    }
                    %>
                </ul>
            </ol> --%>
            
            <ol>
                <a href="javascript:void(0);" onfocus="this.blur();" class="item">系统管理</a>
                <ul>
                    <%
                     for(String[] menu : sys_menu){
                    %>
                    <li><a href="<s:url value='<%=menu[2] %>'/>"  onfocus="this.blur();" <%=menu[0].equals(current)?" id='current'":"" %>><%=menu[1] %></a></li>
                    <%
                    }
                    %>
                </ul>
            </ol>
            
        </div>
        
        <script type="text/javascript">
        $().ready(function(){
        	pilicat.menu();
        });
        
        </script>