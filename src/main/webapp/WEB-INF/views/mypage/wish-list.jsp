<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <script type="text/javascript" src="<c:url value='/resources/js/wish-list.js'/>" defer></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/wish-list.css"/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/wish.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Wish List</div>
            </div>
            <form action="<c:url value="/mypage/wish/remove-all"/>?page=${ph.sc.page}&pageSize=${ph.sc.pageSize}" method="post" >
                <div class="list_option">
                    <div>
                        <input type="checkbox" id="delete_all" class="select_all" onclick="selectAll(this)"><label for="delete_all">&nbsp;전체 선택</label>
                    </div>
                    <input type="submit" value="선택삭제">
                </div>
                <ul class="wish_list">
                    <c:if test="${totalCnt <= 0}">
                        <li>
                            <div id="empty_list">
                                찜한 상품이 없습니다.
                            </div>
                        </li>
                    </c:if>
                    <c:forEach var="wish" items="${list}" varStatus="status">
                        <li>
                            <input type="checkbox" class="delete_check" name="check_box" value="${status.index}" onclick="checkAll(this)">
                            <input type="hidden" name="product_id" value="${wish.product_id}">
                            <a class="img_href" href="<c:url value="/product?product_id=${wish.product.product_id}"/>"><img src="<c:url value="/upload/product-img/${wish.product.product_id}/${wish.product.image}"/>"></a>
                            <div class="product_info">
                                <a href="<c:url value='/product?product_id=${wish.product_id}'/>" target="_blank">${wish.product.product_name}</a><br><br>
                                <fmt:formatNumber type="number" maxFractionDigits="0" value="${wish.product.dis_price}"/>원
                            </div>
                            <div class="product_size">
                                사이즈 <select name="${wish.product_id}_size">
                                <option value="" style="display: none;" selected>선택</option>
                                <c:forEach items="${wish.size}" var="size">
                                    <option value="${size}">${size}</option>
                                </c:forEach>
                            </select>
                            </div>
                            <div class="list_button">
                                <button type="submit" formaction="<c:url value='/mypage/wish/add-cart'/>?product_id=${wish.product_id}&page=${ph.sc.page}&pageSize=${ph.sc.pageSize}">장바구니에 담기</button><br><br>
                                <button type="submit" formaction="<c:url value='/mypage/wish/remove'/>?product_id=${wish.product_id}&page=${ph.sc.page}&pageSize=${ph.sc.pageSize}">삭제</button>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </form>
                <div class="paging">
                    <c:if test="${totalCnt != null && totalCnt != 0}">
                        <c:if test="${ph.showPrev}">
                            <a href="<c:url value="/mypage/wish/list"/>?page=${ph.beginPage-1}&pageSize=${ph.sc.pageSize}"><i class="fa-solid fa-angle-left"></i></a>
                        </c:if>
                        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                            <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/mypage/wish/list"/>?page=${i}&pageSize=${ph.sc.pageSize}">${i}</a>
                        </c:forEach>
                        <c:if test="${ph.showNext}">
                            <a href="<c:url value="/mypage/wish/list"/>?page=${ph.endPage+1}&pageSize=${ph.sc.pageSize}"><i class="fa-solid fa-angle-left"></i></a>
                        </c:if>
                    </c:if>
                </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>