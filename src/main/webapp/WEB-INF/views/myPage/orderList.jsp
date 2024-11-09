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
    <link rel="stylesheet" href="<c:url value="/resources/css/sideMenu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/orderList.css"/>">
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/house.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Mypage</div>
            </div>
            <div class="nav">
                <%@ include file="leftMenu.jsp" %>
                <div class="contents">
                    <h2>주문목록</h2>
                    <div class="order_list">
                        <c:forEach var="orderList" items="${orderList}" varStatus="OLstatus">
                            <form method="post" action="">
                                <div class="order">
                                    <div class="order_top"><div><fmt:formatDate value="${orderList[0].order_date}" pattern="yyyy.MM.dd"/> 주문</div><div><a href="<c:url value="/myPage/orderDetail?order_no=${orderList[0].order_no}&page=${ph.sc.page}"/>">주문 상세보기&nbsp;<i class="fa-solid fa-angle-right"></i></a></div></div>
                                    <div class="order_contents">
                                        <div class="order_img">
                                            <c:forEach var="order" items="${orderList}" varStatus="orderStatus">
                                                <img src="<c:url value="/resources/img/upload/${productList[OLstatus.index][orderStatus.index].product_id}/${productList[OLstatus.index][orderStatus.index].image}"/>">
                                            </c:forEach>
                                        </div>
                                        <div class="order_status">
                                            <c:forEach var="order" items="${orderList}" varStatus="orderStatus">
                                                <div>${order.status}</div>
                                                <div><a href="<c:url value="/product"/>?product_id=${order.product_id}" target="_blank">${productList[OLstatus.index][orderStatus.index].product_name} / ${productList[OLstatus.index][orderStatus.index].color} / ${order.size}</a></div>
                                                <div><fmt:formatNumber type="number" maxFractionDigits="0" value="${productList[OLstatus.index][orderStatus.index].total}"/>원 / ${order.quantity}개</div>
                                            </c:forEach>
                                        </div>
                                        <div class="order_button">
                                            <div><button type="submit" formaction="<%--<c:url value="/repurchase"/>--%>">재구매</button></div>
                                            <div><button type="button">교환, 반품신청</button></div>
                                            <div><button type="button">리뷰작성</button></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                    <div class="paging">
                        <c:if test="${totalCnt != null && totalCnt != 0}">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/myPage/orderList?page=${ph.beginPage-1}"/>"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/myPage/orderList?page=${i}" />">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/myPage/orderList?page=${ph.endPage+1}"/>"><i class="fa-solid fa-angle-left"></i></a>
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