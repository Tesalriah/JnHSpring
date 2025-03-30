<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/order-list.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/order-detail.css"/>">
        <script type="text/javascript" src="<c:url value='/resources/js/order-list.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>" defer></script>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <c:if test="${not empty sessionScope.msg}">
        <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
        <c:remove var="msg" scope="session"/>
    </c:if>
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
                    <h2>주문목록</h2>
                    <div class="order_list">
                        <c:if test="${empty orderList}">
                            <div id="empty_list">
                                주문하신 내역이 없습니다.
                            </div>
                        </c:if>
                        <c:forEach var="orderList" items="${orderList}" varStatus="OLstatus">
                            <form method="post" action="">
                                <div class="order">
                                    <div class="order_top"><div><fmt:formatDate value="${orderList[0].order_date}" pattern="yyyy.MM.dd"/> 주문</div><div><a href="<c:url value="/mypage/order/detail"/>?order_no=${orderList[0].order_no}&page=${ph.sc.page}">주문 상세보기&nbsp;<i class="fa-solid fa-angle-right"></i></a></div></div>
                                    <div class="order_contents">
                                        <form action="" method="post">
                                            <div class="order_img">
                                                <c:forEach var="order" items="${orderList}">
                                                    <img src="<c:url value="/resources/img/upload/product-img/${order.product.product_id}/${order.product.image}"/>">
                                                </c:forEach>
                                            </div>
                                            <input type="hidden" name="order_no" value="${orderList[0].order_no}">
                                            <div class="order_status">
                                                <c:forEach var="order" items="${orderList}" varStatus="orderStatus">
                                                    <input type="hidden" name="product_id" value="${order.product_id}">
                                                    <input type="hidden" name="size" value="${order.size}">
                                                    <input type="hidden" name="quantity" value="${order.quantity}">
                                                    <div>${order.status}</div>
                                                    <div><a href="<c:url value="/product"/>?product_id=${order.product_id}" target="_blank">${order.product.product_name} / ${order.product.color} / ${order.size}</a></div>
                                                    <div><fmt:formatNumber type="number" maxFractionDigits="0" value="${order.product.total}"/>원 / ${order.quantity}개</div>
                                                </c:forEach>
                                            </div>
                                            <div class="order_button">
                                                <div><button type="submit" formaction="<c:url value="/repurchase"/>">재구매</button></div>
                                                <div>
                                                    <c:choose>
                                                        <c:when test="${orderList[0].status == '주문완료'}"><button type="submit" class="cancel_btn" formaction="<c:url value="/mypage/return/cancel"/>?page=${ph.sc.page}"><span>주문취소</span></button></c:when>
                                                        <c:when test="${orderList[0].status == '취소완료'}"><button type="button" class="moveLink" data-link="<c:url value="/mypage/asking/write"/>">문의하기</button></c:when>
                                                        <c:otherwise><button type="submit" formaction="<c:url value="/mypage/return/step1"/>?page=${ph.sc.page}">교환, 반품신청</button></c:otherwise>
                                                    </c:choose>
                                                </div>
                                                <c:if test="${orderList[0].status == '배송완료'}">
                                                    <div><button type="button" class="moveLink" data-link="<c:url value="/mypage/review/able"/>">리뷰작성</button></div>
                                                </c:if>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </div>
                    <div class="paging">
                        <c:if test="${totalCnt != null && totalCnt != 0}">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/mypage/order/list"/>?page=${ph.beginPage-1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/order/list"/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/mypage/order/list"/>?page=${ph.endPage+1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                        </c:if>
                    </div>
                    <c:if test="${empty orderList}">
                        <div class="guide">
                            <div>배송상품 주문상태 안내</div>
                            <div>
                                <div>
                                    <div>결제완료</div>
                                    <div>주문,결제,확인이 완료되었습니다.</div>
                                </div>
                                <div><i class="fa-solid fa-angle-right"></i></div>
                                <div>
                                    <div>상품준비중</div>
                                    <div>판매자가 발송할 상품을 준비중입니다.</div>
                                </div>
                                <div><i class="fa-solid fa-angle-right"></i></div>
                                <div>
                                    <div>배송시작</div>
                                    <div>상품준비가 완료되어 곧 배송될 예정입니다.</div>
                                </div>
                                <div><i class="fa-solid fa-angle-right"></i></div>
                                <div>
                                    <div>배송중</div>
                                    <div>상품이 고객님께 배송중입니다.</div>
                                </div>
                                <div><i class="fa-solid fa-angle-right"></i></div>
                                <div>
                                    <div>배송완료</div>
                                    <div>상품이 주문자에게 전달완료되었습니다.</div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>