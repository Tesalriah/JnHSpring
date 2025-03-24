<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="left_menu">
    <div><a ${current == "product" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/admin/product"/>">상품관리</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">유저관리</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">신고관리</a></div>
    <div><a ${current == "ask-mng" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/admin/ask-mng"/>">문의관리</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">취소/반품/교환관리</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">주문관리</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">리뷰관리</a></div>
</div>