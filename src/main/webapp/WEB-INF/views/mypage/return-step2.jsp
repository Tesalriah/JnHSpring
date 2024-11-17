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
    <link rel="stylesheet" href="<c:url value="/resources/css/return-step2.css"/>">
    <script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
    <script type="text/javascript" src="<c:url value='/resources/js/return-radio.js'/>" defer></script>
    <script type="text/javascript" src="<c:url value="/resources/js/address.js"/>" defer></script>
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
                        <div class="return_exchange">
                            <div class="radio">
                                <div><input class="radioBtn" id="return" type="radio" name="type" value="return" checked><label for="return">반품</label></div>
                                <div><input class="radioBtn" id="exchange" type="radio" name="type" value="exchange"><label for="exchange">교환</label></div>
                            </div>
                            <form action="" method="post">
                                <input name="type" type="hidden" value="return">
                                <table class="info_table">
                                    <tr>
                                        <td>
                                            상품명
                                        </td>
                                        <td class="product_info">
                                            <c:forEach items="${orderList}" var="order" varStatus="status">
                                                <input type="hidden" name="inputId" value="${order.product_id}"><input type="hidden" name="inputSize" value="${order.size}"><input type="hidden" name="input_quan" value="${order.quantity}">
                                                <div>${productList[status.index].product_name} / ${productList[status.index].color} / ${order.size} / ${order.quantity}개</div>
                                            </c:forEach>
                                        </td>
                                        <td class="change_size" style="display: none;">
                                            <c:forEach items="${sizeList}" var="list">
                                                <div>변경사이즈
                                                    <select name="change_size">
                                                        <option value="" disabled selected>선택</option>
                                                        <c:forEach items="${list}" var="size">
                                                            <option value="${size}">${size}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            사유
                                        </td>
                                        <td style="width:100%" colspan="2">
                                            <select name="reason">
                                                <option id="init_option" value="" selected style="display: none;">사유를 선택하세요</option>
                                                <option class="reason_return" value="상품이 마음에 들지 않음">상품이 마음에 들지 않음</option>
                                                <option class="reason_exchange" value="사이즈 변경" style="display: none;">사이즈 변경</option>
                                                <option value="다른 상품이 배송됨">다른 상품이 배송됨</option>
                                                <option value="상품이 파손되어 배송됨">상품이 파손되어 배송됨</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            내용
                                        </td>
                                        <td style="width: 85%;" colspan="2">
                                            <textarea name="details" rows="6" placeholder="상세한 내용을 입력해주세요."></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            상품배송지/회수지
                                        </td>
                                        <td style="width: 85%;" colspan="2">
                                            <input name="address" class="address" type="text" value="${user.address}" readonly><button type="button" class="find_address" id="change_address">주소지변경</button>
                                            <br><div style="display: none; margin-top: 10px;"><input style="width:50%;" name="address2" class="address_detail" type="text" placeholder="상세주소"></div>
                                        </td>
                                    </tr>
                                </table>
                                <div class="app">
                                    <button type="submit">신청하기</button>
                                </div>
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