<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="left_menu">
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/notice') and param.option eq 'notice'}">style="color:#FFAEC9;"</c:if> href="<c:url value="/notice/list"/>?option=notice">공지사항</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/notice') and param.option eq 'event'}">style="color:#FFAEC9;"</c:if> href="<c:url value="/notice/list"/>?option=event">이벤트</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/faq')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/FAQ/list"/>">FAQ</a></div>
</div>