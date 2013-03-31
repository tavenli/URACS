<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../../common/include_tag.jsp" %>
<%@ include file="../../common/page_var.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><%=pageTitle%> - 用户管理</title>

    <%@ include file="../../common/page_head.jsp" %>

    <script type="text/javascript">
        
    function add(){
    	$.jBox("iframe:<s:url value='/u/userAdd'/>", {
    	    title: "添加用户",
    	    width: 700,
    	    height: 320,
    	    buttons: {}
    	});
    }


    function edit(id){
    	$.jBox("iframe:<s:url value='/u/userEdit'/>?id="+id, {
    	    title: "修改用户",
    	    width: 700,
    	    height: 320,
    	    buttons: {}
    	});
    }
    
    function changeUserStatus(id,status){

    	$.jBox.confirm("确定 "+(status==0?"禁用":"启用")+" 用户吗？", "确认操作", function (v, h, f) {
    	    if (v == 'ok'){
    	    	
    	    	$.post("<s:url value='/u/changeUserStatus'/>",{id:id,status:status},function(responseText){
    	    		if(responseText==true){
    	    			$.jBox.info("操作成功，请刷新查看结果", "成功信息",{top: '20%'});
    	    			//window.location.reload();
    	    		}else{
    	    			$.jBox.error("操作失败", "失败信息");
    	    		}
    	    	});

    	    	
    		}
    	    return true; 
    	},{top: '40%'});
    	
    }
    
    function delUser(id,msg){

    	$.jBox.confirm("确定删除 ["+msg+"] 用户吗？", "确认操作", function (v, h, f) {
    	    if (v == 'ok'){
    	    	
    	    	$.post("<s:url value='/u/delUser'/>",{id:id},function(responseText){
    	    		if(responseText==true){
    	    			$.jBox.info("操作成功，请刷新查看结果", "成功信息",{top: '20%'});
    	    			//window.location.reload();
    	    		}else{
    	    			$.jBox.error("操作失败", "失败信息");
    	    		}
    	    	});

    	    	
    		}
    	    return true; 
    	},{top: '40%'});
    	
    }
    
    function delUsers(){
    	//批量删除用户
    	var selectedItems = new Array();
    	$("input[type=checkbox][name=cbitem]:checked").each(function() {selectedItems.push($(this).val());});
    	
    	if (selectedItems .length == 0){
    		$.jBox.tip("没有选择批量删除的数据.", "warning",{top: '40%'});
    	    return;
    	}
    	
    	$.jBox.confirm("确定批量删除吗？", "批量操作确认", function (v, h, f) {
    	    if (v == 'ok'){

    	    	$.post("/u/delUsers",{userIds:selectedItems.join(',')},function(data){
    	    		if(data>0){
    	    			$.jBox.tip("批量删除 "+data+" 条，请刷新查看结果", "success",{top: '40%'});
    	    			
    	    		}else{
    	    			$.jBox.error("批量删除失败", "失败信息");
    	    		}
    	    		
    	    	});
    	    	
    		}
    	    return true; 
    	},{top: '40%'});
    	
    }

    function userRoleSet(userId){
    	$.jBox("iframe:<s:url value='/u/userRoleSet'/>?userId="+userId, {
    	    title: "用户授权",
    	    width: 700,
    	    height: 320,
    	    buttons: {}
    	});
    }
    
    $().ready(function(){
    	pilicat.alternately('list');

    });

    </script>
</head>
<body>

    <div style="height: 60px; overflow: hidden">
		<%@ include file="../u_top.jsp" %>
    </div>
    
    <div id="frame_side">
        <%@ include file="../u_login_info.jsp" %>

		<jsp:include page="../u_left_menu.jsp"  flush="true">
        	<jsp:param name="current" value="users"/>
       	</jsp:include>

    </div>
    <div id="body_box">
        <table cellpadding="0" cellspacing="0" border="0" class="icon">
            <tr>
                <td>
                </td>
            </tr>
        </table>
        
        <table cellpadding="0" cellspacing="0" border="0" class="table">
			<tbody>
			<tr>
				<td valign="top" align="right">
					<form action="" method="get" name="search" id="search">
					<table cellpadding="0" cellspacing="5" border="0">
						<tbody>
						<tr>							
							<td><a class="button_4" href="javascript:;" onclick="add();">添加新用户</a></td>
						</tr>
					</tbody></table>			
					</form>
				</td>
			</tr>
		</tbody>
	</table>
        
        <div class="rounded table">
            <form action="" method="get" name="search_form" id="search_form">
                <table width="100%" cellpadding="0" cellspacing="0" border="0" class="box_top">
                    <tr>
                        <td class="title">用户列表</td>
                        <td>                            
                        </td>
                    </tr>
                </table>
            </form>

				<form action="" method="post" name="manage" id="manage">
                <table class="list td_align" cellpadding="0" cellspacing="1" border="0">
                    <tr>
                        <td class="field_head" width="30">选择</td>
                        <td class="field_head">用户名称</td>
                        <td class="field_head">用户状态</td>
                        <td class="field_head">创建时间</td>
                        <td class="field_head">最后更新时间</td>
                        <td class="field_head">操作</td>
                        
                    </tr>
                    
                    <c:if test="${totalCount==0}">
                    <tr>
                        <td colspan="6" class="field_head">没有相关数据</td>
                    </tr>
                    </c:if>
                    
                    <c:forEach items="${dataList}"  var="dataItem" varStatus="rowStatus" >
                    
                    <c:choose>
						<c:when test="${rowStatus.index%2==0}">
							<tr class="odd">
						</c:when>												
						<c:otherwise>
							<tr class="even">
						</c:otherwise>
					</c:choose>
						
						<td>
						<input type="checkbox" name="cbitem" value="${dataItem.id }"/>
						</td>
						<td>${dataItem.userName}</td>
						<td>
						
						<c:choose>
							<c:when test="${dataItem.status==0}">
								<font color='red'>禁用</font>
							</c:when>
							<c:when test="${dataItem.status==1}">
								<font color='green'>启用</font>
							</c:when>	
							<c:otherwise>
								<font color='gray'>未知:${dataItem.status}</font>
							</c:otherwise>
						</c:choose>
												
						</td>
						<td>	
                           <fmt:formatDate value="${dataItem.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						
						</td>
						<td>
							<fmt:formatDate value="${dataItem.lastUpdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td class="operation">
							<a href="javascript:;" onclick="edit(${dataItem.id});">
							 	<img src="<s:url value='/css/images/operation/pencil.png'/>" title="修改用户信息"/>
							 </a>
							 
							 <c:choose>
								<c:when test="${dataItem.status==0}">
									 <a href="javascript:;" onclick="changeUserStatus(${dataItem.id},1);">
		                            	<img src="<s:url value='/css/images/operation/lock_unlock.png'/>" title="启用用户"/>
		                            </a>
								</c:when>					
								<c:otherwise>
		                            <a href="javascript:;" onclick="changeUserStatus(${dataItem.id},0);">
		                            	<img src="<s:url value='/css/images/operation/lock.png'/>" title="禁用用户"/>
		                            </a>
								</c:otherwise>
							</c:choose>
							
							 <a href="javascript:;" onclick="userRoleSet(${dataItem.id});">
							 	<img src="<s:url value='/css/images/operation/member.png'/>" title="用户权限"/>
							 </a>   
							 <a href="javascript:;" onclick="delUser(${dataItem.id},'${dataItem.userName}');">
							 	<img src="<s:url value='/css/images/operation/trashcan_delete.png'/>" title="删除用户"/>
							 </a>
							                 
						</td>
						
					</tr>
					</c:forEach>
					
                </table>
                <table class="table top_line">
                    <tr>
                        <td>
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td class="all_action">
                                        <input name="allChkbox" id="allChkbox" type="checkbox" onclick="pilicat.select_all(this.form);" />
                                    </td>
                                    <td>&nbsp;全选/取消 &nbsp;</td>
                                    <td class="operation">
                                        
                                    </td>
                                    
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </form>
            
            <table class="page" cellpadding="0" cellspacing="5">
                <tr>                    
                    <td>
                    <div  id="pageNav" class="scott">
						<font color="#88af3f">共${totalCount} 条数据，  ${totalPage} 页</font>  ${pageNav}
                    </div>
                    
                    </td>
                    <td></td>
                </tr>
            </table>
        </div>

		<%@ include file="../u_bottom.jsp" %>

    </div>
</body>
</html>
