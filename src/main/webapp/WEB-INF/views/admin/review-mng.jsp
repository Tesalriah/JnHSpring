<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/review-mng.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/review-mng.js'/>" defer></script>
    <title>J&H</title>
</head>
<style>
</style>
<body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/admin.png"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Admin Page</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <h2>리뷰 관리</h2>
                    <div class="reviews_bg" style="display:none;"></div>
                    <div id="reviews_img" style="display:none;">
                        <button id="review_img_x" type="button"><i class="fa-solid fa-xmark"></i></button>
                        <div class="img_modal">
                            <img id="enlarge_img" src="">
                        </div>
                    </div>
                    <div class="review_MNG">
                        <div class="table_box">
                            <table class="review_table" border="1">
                                <tr>
                                    <td style="width:10%;">리뷰번호</td>
                                    <td style="width:10%;">누적신고수</td>
                                    <td style="width:15%;">아이디</td>
                                    <td style="width:28%;">상품명</td>
                                    <td style="width:10%;">보기</td>
                                    <td style="width:7%;">삭제</td>
                                </tr>
                                <c:forEach items="${list}" var="review">
                                    <tr style="${review.whether == 2 ? "color:#dddddd" : ""}">
                                        <td>${review.rno}</td>
                                        <td>${review.report_cnt}</td>
                                        <td><a href="<c:url value="/admin/user-mng"/>?keyword=${review.user_id}" target="_blank">${review.user_id}</a></td>
                                        <td><a target="_blank" href="<c:url value="/product"/>?product_id=${review.product_id}">${review.order.product.product_name}</a></td>
                                        <td><button type="button" class="show_detail" data-rno="${review.rno}">보기</button></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${review.whether == 2}">
                                                    <button type="button" style="color:#dddddd;">삭제</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" class="blind-review" data-rno="${review.rno}" data-user_id="${review.user_id}">삭제</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                    <tr style="display: none;" data-rno="${review.rno}">
                                        <td colspan="6" style="text-align: left;">
                                            <div class="reviews_each">
                                                <div class="review_middle" style="display:flex; align-items: center;">
                                                    <div class="rating">
                                                        <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                                        <div class="star" style="width: ${review.rating*20+1}%"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                                                    </div>
                                                    <div class="reviews_date"><fmt:formatDate value="${review.reg_date}" pattern="yyyy/MM/dd"/></div>
                                                </div>
                                                <div class="review_bottom">
                                                    <div>
                                                        <c:if test="${not empty review.image}">
                                                            <div class="review_img"><img class="each_img" src='<c:url value="/resources/img/upload/review-img/${review.rno}/${review.image}"/>'></div>
                                                        </c:if>
                                                            ${review.contents}
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                        <div class="paging">
                            <c:if test="${ph.totalPage != null && ph.totalPage > 0}">
                                <c:if test="${ph.showPrev}">
                                    <a href="<c:url value="/admin/review-mng"/>?page=${ph.endPage-1}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                    <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/admin/review-mng"/>?page=${i}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}">${i}</a>
                                </c:forEach>
                                <c:if test="${ph.showNext}">
                                    <a href="<c:url value="/admin/review-mng"/>?page=${ph.endPage+1}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                            </c:if>
                        </div>
                        <div class="search_user">
                            <form action="<c:url value="/admin/review-mng"/>" method="get">
                                <select name="option">
                                    <option value="user_id" ${empty param.option || param.option == "user_id" ? "selected" : ""}>아이디</option>
                                    <option value="rno" ${param.option == "rno" ? "selected" : ""}>리뷰번호</option>
                                </select>
                                <input type="text" name="keyword" placeholder="검색어를 입력하세요" value="${param.keyword}"><button type="submit">검색</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
