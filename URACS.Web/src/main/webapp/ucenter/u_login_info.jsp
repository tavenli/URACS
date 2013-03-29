<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

        <div class="logo"></div>
        <div class="welcome">
        您好：<a href="javascript:;" class="left_url" style="word-wrap:break-word;word-break:break-all;">
        <c:choose>
			<c:when test="${sessionScope.userInfo.isSubUser==true}">
				${sessionScope.userInfo.subUserName}
			</c:when>												
			<c:otherwise>
				${sessionScope.userInfo.userName}
			</c:otherwise>
		</c:choose>
        
        </a> ,         
        <a href="<s:url value='/u/logout'/>" class="left_url">退出</a>
        </div>
        
