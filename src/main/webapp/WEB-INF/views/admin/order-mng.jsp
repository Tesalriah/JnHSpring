<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/order-mng.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/checkbox-ctrl.js'/>" defer></script>
    <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>"></script>
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
                    <h2>주문 관리</h2>
                    <c:if test="${not empty sessionScope.msg}">
                        <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
                        <c:remove var="msg" scope="session"/>
                    </c:if>
                    <div class="order_MNG">
                        <div class="status_menu">
                            <div style="${ph.sc.category eq "주문완료" ? "border-color:black;" : ""}" data-link="<c:url value="/admin/order-mng"/>?category=주문완료">
                                <div>주문완료</div>
                                <div>${cntMap["주문완료"]}</div>
                            </div>
                            <div style="${ph.sc.category eq "배송중" ? "border-color:black;" : ""}" data-link="<c:url value="/admin/order-mng"/>?category=배송중">
                                <div>배송중</div>
                                <div>${cntMap["배송중"]}</div>
                            </div>
                            <div style="${ph.sc.category eq "배송완료" ? "border-color:black;" : ""}" data-link="<c:url value="/admin/order-mng"/>?category=배송완료">
                                <div>배송완료</div>
                                <div>${cntMap["배송완료"]}</div>
                            </div>
                        </div>
                        <form method="post" action="<c:url value="/admin/order-status"/>?page=${ph.sc.page}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}">
                            <div class="checked_tools">
                                <c:choose>
                                    <c:when test="${ph.sc.category =='주문완료'}">
                                        <button type="submit">배송</button>
                                    </c:when>
                                    <c:when test="${ph.sc.category =='배송중'}">
                                        <button type="submit">배송완료</button>
                                    </c:when>
                                    <c:otherwise></c:otherwise>
                                </c:choose>
                            </div>
                            <div class="table_box">
                                <table class="order_table" border="1">
                                    <tr>
                                        <td style="width:10px;"><input type="checkbox" name="check_all"></td>
                                        <td style="width:10%;">주문번호</td>
                                        <td style="width:10%;">상품ID</td>
                                        <td style="width:10%;">색상/사이즈</td>
                                        <td style="width:5%;">수량</td>
                                        <td style="width:8%">주문자명</td>
                                        <td style="width:25%;">주소 / 요청사항</td>
                                        <td style="width:10%;">연락처</td>
                                        <td style="width:15%;">주문일시</td>
                                        <td style="width:7%;">상태</td>
                                    </tr>
                                    <c:if test="${ph.totalCnt <= 0}">
                                        <tr><td style="padding:150px 0; font-size:24px" colspan="11">데이터가 존재하지 않습니다.</td></tr>
                                    </c:if>
                                    <c:forEach items="${list}" var="order" varStatus="order_status">
                                        <tr>
                                            <td><input type="checkbox" name="check_each" value="${order_status.index}"></td>
                                            <td>${order.order_no}<input name="orderList[${order_status.index}].order_no" type="hidden" value="${order.order_no}"> </td>
                                            <td><a href="<c:url value="/product"/>?product_id=${order.product_id}" target="_blank">${order.product_id}</a><input name="orderList[${order_status.index}].product_id" type="hidden" value="${order.product_id}"></td>
                                            <td>${order.color} / ${order.size} <input name="orderList[${order_status.index}].size" type="hidden" value="${order.size}"></td>
                                            <td>${order.quantity}</td>
                                            <td>${order.name}<input name="orderList[${order_status.index}].user_id" type="hidden" value="${order.user_id}"></td>
                                            <td>${order.address} / ${order.del_request}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${fn:length(order.phone) == 11}">
                                                        ${fn:substring(order.phone,0,3)}-${fn:substring(order.phone,3,7)}-${fn:substring(order.phone,7,11)}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${fn:substring(order.phone,0,3)}-${fn:substring(order.phone,3,6)}-${fn:substring(order.phone,6,10)}
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${order.order_date}" pattern="yyyy/MM/dd HH:mm"/></td>
                                            <td>${order.status}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </form>
                        <div class="paging">
                            <c:if test="${ph.totalPage != null && ph.totalPage > 0}">
                                <c:if test="${ph.showPrev}">
                                    <a href="<c:url value="/admin/order-mng"/>?page=${ph.endPage-1}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                    <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/admin/order-mng"/>?page=${i}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}">${i}</a>
                                </c:forEach>
                                <c:if test="${ph.showNext}">
                                    <a href="<c:url value="/admin/order-mng"/>?page=${ph.endPage+1}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                            </c:if>
                        </div>
                        <div class="search_user">
                            <form action="<c:url value="/admin/order-mng"/>" method="get">
                                <select name="option">
                                    <option value="order_no" ${empty param.option || param.option == "order_no" ? "selected" : ""}>주문번호</option>
                                    <option value="user_id" ${param.option == "user_id" ? "selected" : ""}>아이디</option>
                                    <option value="name" ${param.option == "name" ? "selected" : ""}>이름</option>
                                </select>
                                <input type="hidden" name="category" value="${ph.sc.category}">
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
