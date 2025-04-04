<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/return-step1.css"/>">
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
                    <div class="contents">
                        <h2>교환, 반품 신청</h2>
                        <div class="outside">
                            <form action="<c:url value="/mypage/return/step2"/>" method="post">
                                <c:choose>
                                    <c:when test="${orderList.size() <= 0}">
                                        <div style="text-align: center; padding:100px 20px; font-size: 24px;">반품 할 수 있는 상품이 존재하지 않습니다.</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="subheading">상품을 선택해 주세요</div>
                                        <div class="return_product_list">
                                            <input type="hidden" name="order_no" value="${orderList[0].order_no}">
                                            <c:forEach items="${orderList}" var="order" varStatus="status">
                                                <div class="return_product">
                                                    <div><input type="checkbox" name="check_box" value="${status.index}"></div>
                                                    <div><img src="<c:url value="/resources/img/upload/product-img/${order.product.product_id}/${order.product.image}"/>"></div>
                                                    <div><a href="<c:url value="/product"/>?product_id=${order.product_id}" target="_blank`">${order.product.product_name} / ${order.product.color} / ${order.size} / ${order.quantity}개</a></div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <div class="next_button">
                                            <button type="submit" class="submit_next">다음단계 <i class="fa-solid fa-angle-right"></i></button>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
    <script>
        document.querySelector('.submit_next').addEventListener('click', function (event){
            const checkboxes = document.querySelectorAll('.check_box');
            const isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);

            if (!isChecked) {
                alert("상품을 선택해주세요.");
                event.preventDefault();
            }
        })
    </script>
</body>
</html>