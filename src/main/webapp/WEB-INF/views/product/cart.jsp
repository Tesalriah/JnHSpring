<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/cart.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/cart.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/wishList.js'/>" defer></script>
        <title>J&H 장바구니</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/cart.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Cart</div>
            </div>
            <form action="<c:url value="/cart"/>" method="post" >
                <div class="list_option">
                    <div style="width:16%;">
                        <input type="checkbox" id="delete_all" class="select_all" onclick="selectAll(this)"><label for="delete_all">&nbsp;전체 선택</label>
                    </div>
                    <div style="width:45%;">상품정보</div>
                    <div style="width:4%;text-align:center;">사이즈</div>
                    <div style="width:12%; text-align:center;">수량</div>
                    <div>
                        <input type="submit" formaction="<c:url value="/delCart"/>" value="선택삭제">
                    </div>
                </div>
                <ul class="cart_list">
                    <c:if test="${empty cartList}">
                        <li>
                            <div style="text-align: center; width: 100%; padding: 60px; font-weight:bold;">
                                장바구니에 담은 상품이 없습니다.
                            </div>
                        </li>
                    </c:if>
                    <c:forEach items="${cartList}" var="cart" varStatus="status">
                        <li>
                            <div style="display:flex; width:16%;">
                                <input type="checkbox" class="delete_check" value="${status.index}" name="check_box" onclick="checkAll(this)">
                                <a class="img_href" href="<c:url value="/product?product_id=${cart.product_id}"/>" target="_blank"><img src="<c:url value="/resources/img/upload/${productList[status.index].product_id}/${productList[status.index].image}"/>"></a>
                            </div>
                            <div class="product_info">
                                <a href="<c:url value="/product?product_id=${cart.product_id}"/>" target="_blank">${productList[status.index].product_name}</a><input type="hidden" name="product_id" value="${cart.product_id}"><br><br>
                                <span class="price"><fmt:formatNumber type="number" value="${productList[status.index].dis_price}"/></span>원<input type="hidden" name="price" value="${productList[status.index].dis_price}">
                                <input type="hidden" name="size" value="${cart.size}">
                            </div>
                            <div style="width: 4%; text-align:center;">
                                ${cart.size}
                            </div>
                            <div style="width:12%; text-align:center;">
                                <button type="button" class="quantity_minus"><i class="fa-solid fa-minus"></i></button><input class="quantity" type="text" name="quantity" value="${cart.quantity}"><button type="button" class="quantity_plus"><i class="fa-solid fa-plus"></i></button>
                            </div>
                            <div class="list_button">
                                <input type="submit" formaction="<c:url value="/delOneCart"/>?del_product_id=${cart.product_id}&del_siz e=${cart.size}" value="삭제">
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <div class="end_list">
                    <div>
                        <c:if test="${not empty cartList}">
                            총 주문금액 <span id="total"></span>원
                        </c:if>
                    </div>
                </div>
                <div class="end_button_area">
                    <div>
                        <button class="end_button" type="submit">구매하기</button>
                        <button class="end_button" type="button" onclick="<c:url value="/productList"/>">계속쇼핑하기</button>
                    </div>
                </div>
            </form>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>