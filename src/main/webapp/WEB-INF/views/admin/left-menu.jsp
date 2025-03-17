<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="left_menu">
    <div><a ${current == "product" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/admin/product-mng"/>">상품관리</a></div>
    <div><a ${current == "user" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/admin/user-mng"/>">유저관리</a></div>
    <div><a ${current == "report" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/admin/report-mng"/>">신고관리</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">문의관리</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">취소/반품/교환관리</a></div>
    <div><a ${current == "order" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/admin/order-mng"/>">주문관리</a></div>
    <div><a ${current == "review" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/admin/review-mng"/>">리뷰관리</a></div>
</div>