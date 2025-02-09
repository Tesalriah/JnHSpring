<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="left_menu">
    <div><a ${current == "order" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/order/list"/>">주문목록</a></div>
    <div><a ${current == "return" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/return/list"/>">취소/반품/교환</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/wish/list"/>">찜리스트</a></div>
    <br>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/user"/> ">개인정보 확인/수정</a></div>
    <div><a ${current == "" ? "style='color:#FFAEC9;'" : ""} href="">신고내역확인</a></div>
    <br>
    <div><a ${current == "review" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/review/able"/>">리뷰관리</a></div>
    <br>
    <div><a ${current == "/" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/asking/write"/>">문의하기</a></div>
    <div><a ${current == "/" ? "style='color:#FFAEC9;'" : ""} href="<c:url value="/mypage/asking/list"/>">문의내역확인</a></div>
</div>