<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/return-list.css"/>">
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/house.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Mypage</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <h2>취소/반품/교환 내역</h2>
                    <div class="return_list">
                        <c:forEach var="list" items="${returnsList}">
                            <div class="return_top">
                                <div><c:choose><c:when test="${list[0].type eq 'return'}">반품</c:when><c:when test="${list[0].type eq 'exchange'}">교환</c:when><c:otherwise>취소</c:otherwise></c:choose>접수일 : <fmt:formatDate value="${list[0].return_date}" pattern="yyyy/MM/dd"/></div>
                                <div>주문일 : <fmt:formatDate value="${list[0].order_date}" pattern="yyyy/MM/dd"/></div>
                            </div>
                            <div class="return">
                                <div class="return_bottom">
                                    <div class="product_name">
                                        <c:forEach var="returns" items="${list}">
                                            <div>
                                                    ${returns.product.product_name} / ${returns.size} <c:if test="${not empty returns.c_size}">/ 변경 사이즈 : ${returns.c_size}</c:if>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="info">
                                        <c:forEach var="returns" items="${list}">
                                            <div>
                                                <div>${returns.quantity}개</div>
                                                <div><fmt:formatNumber type="number" maxFractionDigits="0" value="${returns.product.total}"/>원</div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="status">
                                        <div>
                                                ${list[0].status}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="paging">
                        <c:if test="${totalCnt != null && totalCnt != 0}">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/mypage/return/list"/>?page=${ph.beginPage-1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/mypage/return/list"/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/mypage/return/list"/>?page=${ph.endPage+1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>