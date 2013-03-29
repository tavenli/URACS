<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <div class="logo"></div>
        <div class="welcome">
        您好：<a href="javascript:;" class="left_url" style="word-wrap:break-word;word-break:break-all;">
        <sec:authentication property="principal.username"/>
        <sec:authorize access="hasRole('ROLE_SUPER')">
         [<span style="color:red;">超级用户</span>]
        </sec:authorize>
        </a> ,         
        <a href="<s:url value='/u/logout'/>" class="left_url">退出</a>
        </div>
        
