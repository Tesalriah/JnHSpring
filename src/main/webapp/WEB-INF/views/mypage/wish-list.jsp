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
    <script type="text/javascript" src="<c:url value='/resources/js/wish-list.js'/>" defer></script>
    <link rel="stylesheet" href="<c:url value="/resources/css/wish-list.css"/>">
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/wish.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Wish List</div>
            </div>
            <form action="" method="post" >
                <div class="list_option">
                    <div>
                        <input type="checkbox" id="delete_all" class="select_all" onclick="selectAll(this)"><label for="delete_all">&nbsp;전체 선택</label>
                    </div>
                    <input type="submit" value="선택삭제">
                </div>
                <ul class="wish_list">
                    <c:forEach var="wish" items="${list}">
                        <li>
                            <input type="checkbox" class="delete_check" name="check_box" value="" onclick="checkAll(this)">
                            <a class="img_href" href="<c:url value="/product?product_id=${wish.product.product_id}"/>"><img src="<c:url value="/resources/img/upload/${wish.product.product_id}/${wish.product.image}"/>"></a>
                            <div class="product_info">
                                ${wish.product.product_name}<br><br>
                                ${wish.product.dis_price}원
                            </div>
                            <div class="product_size">
                                사이즈 <select name="product_size">
                                <option value="" style="display: none;" selected>선택</option>
                                <c:forEach items="${wish.size}" var="size">
                                    <option value="${size}">${size}</option>
                                </c:forEach>
                            </select>
                            </div>
                            <div class="list_button">
                                <button type="submit" formaction="">장바구니에 담기</button><br><br>
                                <button type="submit" formaction="">삭제</button>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </form>
            <div class="paging">
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#">></a>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>