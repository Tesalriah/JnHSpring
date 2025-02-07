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
    <script type="text/javascript" src="<c:url value="/resources/js/review-wrote.js"/>" defer></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/review-wrote.css"/>">
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
                        <div><a href="<c:url value="/mypage/review/able"/>">리뷰 작성</a></div>
                        <div style=" border-bottom: none;"><a href="<c:url value="/mypage/review/wrote"/>">작성한 리뷰</a></div>
                    </div>
                    <!-- 리뷰 이미지 모달 -->
                    <div class="reviews_bg" style="display:none;"></div>
                    <div id="reviews_img" style="display:none;">
                        <button id="review_img_x" type="button"><i class="fa-solid fa-xmark"></i></button>
                        <div class="img_modal">
                            <img id="enlarge_img" src="img/best.jpg">
                        </div>
                    </div>
                    <div class="reviews_contents">
                        <c:if test="${empty list}">
                            <div id="empty_list">
                                작성한 구매후기가 없습니다.
                            </div>
                        </c:if>
                        <c:forEach items="${list}" var="list">
                            <div class="reviews_each">
                                <div class="review_top">
                                    <div class="product_img"><a href='<c:url value="/product?product_id=${list.product_id}"/>' target="_blank"><img src='<c:url value="/resources/img/upload/product-img/${list.product_id}/${list.order.product.image}"/>'>${list.order.product.product_name}</a></div>
                                    <div class="reviews_tools"><div class="edit_del" style="font-size: 14px;"><a href="<c:url value="/mypage/review/modify"/>?rno=${list.rno}">수정</a>|<form style="display: inline;" method="post" action="<c:url value="/mypage/review/remove"/>"><input name="rno" type="hidden" value="${list.rno}"> <button type="submit" onclick="return confirm('삭제하시겠습니까?');">삭제</button></form></div></div>
                                </div>
                                <div class="review_middle" style="display:flex; align-items: center;">
                                    <div class="rating">
                                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                        <div class="star" style="width: ${list.rating*20+1}%"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                                    </div>
                                    <div class="reviews_date"><fmt:formatDate value="${list.reg_date}" pattern="yyyy/MM/dd"/></div>
                                </div>
                                <div class="review_bottom">
                                    <div>
                                        <c:if test="${not empty list.image}">
                                            <div class="review_img"><img class="each_img" src='<c:url value="/resources/img/upload/review-img/${list.rno}/${list.image}"/>'></div>
                                        </c:if>
                                        ${list.contents}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="paging">
                        <c:if test="${ph.totalPage != null && ph.totalPage != 0 && ph.totalPage > 1}">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/mypage/review/wrote"/>?page=${ph.beginPage-1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/mypage/review/wrote"/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/mypage/review/wrote"/>?page=${ph.endPage+1}"><i class="fa-solid fa-angle-left"></i></a>
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