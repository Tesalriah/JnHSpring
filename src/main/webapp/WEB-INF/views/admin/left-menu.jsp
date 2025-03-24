<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="left_menu">
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/product')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/admin/product-mng"/>">상품관리</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/user')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/admin/user-mng"/>">유저관리</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/report')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/admin/report-mng"/>">신고관리</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/ask')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/admin/ask-mng"/>">문의관리</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/return')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/admin/return-mng"/>">취소/반품/교환관리</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/order')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/admin/order-mng"/>">주문관리</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/review')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/admin/review-mng"/>">리뷰관리</a></div>
</div>