<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../../common/include_tag.jsp" %>
<%@ include file="../../common/page_var.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><%=pageTitle%></title>

    <%@ include file="../../common/page_head.jsp" %>
	
    <script type="text/javascript">
		$(function(){
			pilicat.title2div('title2div');
			pilicat.keysubmit('form1','submit',true);
		});
	</script>
	
	<script type="text/javascript" >
	    $(document).ready(function() { 
	        $('#form1').ajaxForm( { dataType: 'json' , beforeSubmit : checkForm , success : afterPost } ); 
	    }); 
	    
	    function checkForm(formData, jqForm, options) { 
	
			for (var i=0; i < formData.length; i++) { 
				
		       if  (formData[i].name=="menuName" && !formData[i].value) { 
		        	$.jBox.error("必须输入菜单名称 ！", "失败信息");
		        	return false; 
		        }
	    	}
			
	    }
	    
	    function afterPost(responseText, statusText, xhr, $form)  {
	    	
	    	if(responseText.resultStatus==true){
	    		//parent.loadPageData();
	    		parent.$.jBox.tip("保存成功，请刷新查看结果", "success", {timeout:2000});
	    		parent.$.jBox.close(true);
	    		
	    		
	    	}else{
	    		$.jBox.error("保存失败", "失败信息");
	    	}
	    	
	    }
	    
	    function submitForm(){
	    	
	    }
	    
	</script>

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
		<table width="100%" cellpadding="0" cellspacing="0" border="0" class="box_top">
			<tbody><tr>
				<td class="title"></td>
				<td></td>
			</tr>
		</tbody></table>
		
		<div class="error rounded top_error hide">
		<ol></ol><span></span>
		</div>
		
		<form id="form1" action="<s:url value='/u/saveMenu'/>" method="post">
		<table class="update" cellpadding="0" cellspacing="1" border="0">
			<tbody>
			<tr>
				<td class="name">父菜单：</td>
				<td>
				
				<form:select path="menuEntity.parentId"  items="${parentMenus}"  itemValue="id"  itemLabel="menuName" ></form:select>
				</td>
				
			</tr>
			<tr>
				<td class="name">菜单名称：</td>
				<td>
				<input name="menuName" type="text" value="${menuEntity.menuName }" size="25" maxlength="11" class="input rounded"/>
				
				<form:hidden path="menuEntity.id" />
				</td>
				
			</tr>		
			<tr class="even">
				<td class="name">菜单Code：</td>
				<td>
					<form:input path="menuEntity.menuCode"  cssClass="input rounded" size="25" />
					<img title="目前菜单Code的作用是为了JSP中的显示焦点定位在那个菜单上" class="title2div" src="<s:url value='/css/images/operation/help.png'/>" width="16" height="16"/>
				</td>
				
			</tr>
			<tr class="even">
				<td class="name">菜单Url：</td>
				<td>
					<form:input path="menuEntity.menuUrl"  cssClass="input rounded" size="50" />
					<img title="菜单的Url地址" class="title2div" src="<s:url value='/css/images/operation/help.png'/>" width="16" height="16"/>
				</td>
				
			</tr>
			<tr class="even">
				<td class="name">在导航菜单中显示：</td>
				<td>
					<form:radiobutton path="menuEntity.navMenu" value="1"/>显示&nbsp;&nbsp;
					<form:radiobutton path="menuEntity.navMenu" value="0"/>不显示&nbsp;&nbsp;		
					<img title="如果为显示，则会在系统菜单中显示，如果为不显示，则不会出现在导航菜单中，但URL可以授权给角色" class="title2div" src="<s:url value='/css/images/operation/help.png'/>" width="16" height="16"/>
				</td>
				
			</tr>
		</tbody>
		</table>
		
		<table class="table top_line">
			<tbody>
			<tr>
				<td align="center" height="60">
					<a id="submit" class="submit" href="javascript:;" onclick="submitForm();">保存</a>
				</td>
				
			</tr>
			</tbody>
		</table>

		</form>
	</div>
	
</body>
</html>
