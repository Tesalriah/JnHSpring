<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/review-able.css"/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
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
                    <h2>리뷰 관리</h2>
                    <div class="top_nav">
                        <div style=" border-bottom: none;"><a href="<c:url value="/mypage/review/able"/>">리뷰 작성</a></div>
                        <div><a href="<c:url value="/mypage/review/wrote"/>">작성한 리뷰</a></div>
                    </div>
                    <div class="able_list">
                        <c:if test="${empty list}">
                            <div id="empty_list">
                                작성 가능한 구매후기가 없습니다.
                            </div>
                        </c:if>
                        <c:forEach items="${list}" var="list">
                            <div class="able">
                                <div><img src='<c:url value="/upload/product-img/${list.product_id}/${list.order.product.image}"/>'></div>
                                <div>
                                    <div>${list.order.product.product_name} / ${list.order.color} / ${list.order.size} / ${list.order.quantity}개</div>
                                    <div>구매일자 : <fmt:formatDate value="${list.order.order_date}" pattern="yyyy/MM/dd"/></div>
                                </div>
                                <div>
                                    <div><button type="button" onclick="location.href = '<c:url value="/mypage/review/write"/>?rno=${list.rno}'">리뷰작성하기</button></div>
                                    <div><form style="display: inline;" method="post" action="<c:url value="/mypage/review/remove"/>?rno=${list.rno}"><button type="submit" onclick="return confirm('삭제하시겠습니까?');">삭제하기</button></form></div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="paging">
                        <c:if test="${ph.totalPage != null && ph.totalPage != 0 && ph.totalPage > 1}">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/mypage/review/able"/>?page=${ph.beginPage-1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/mypage/review/able"/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/mypage/review/able"/>?page=${ph.endPage+1}"><i class="fa-solid fa-angle-left"></i></a>
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