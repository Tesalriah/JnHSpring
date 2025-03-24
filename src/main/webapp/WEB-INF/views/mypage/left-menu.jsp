<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="left_menu">
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/order')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/mypage/order/list"/>">주문목록</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/return')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/mypage/return/list"/>">취소/반품/교환</a></div>
    <div><a href="<c:url value="/mypage/wish/list"/>">찜리스트</a></div>
    <br>
    <div><a href="<c:url value="/mypage/user"/> ">개인정보 확인/수정</a></div>
    <br>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/review')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/mypage/review/able"/>">리뷰관리</a></div>
    <br>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/asking-write')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/mypage/asking/write"/>">문의하기</a></div>
    <div><a <c:if test="${fn:contains(pageContext.request.requestURI, '/asking-list') or fn:contains(pageContext.request.requestURI, '/question-list')}">style="color:#FFAEC9;"</c:if> href="<c:url value="/mypage/asking/list"/>">문의내역확인</a></div>
</div>