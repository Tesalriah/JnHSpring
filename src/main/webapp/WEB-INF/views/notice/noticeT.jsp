<%--
  Created by IntelliJ IDEA.
  User: bella
  Date: 2024-10-15
  Time: 오후 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    msg = "${msg}";
    if(msg == "FAIL")alert("실패");
</script>
<form action="<c:url value='/wriT'/>" method="post">
    <c:if test="${modi == 1}">
        <input type="hidden" name="bno" value="${asd.bno}">
    </c:if>
    게시물 번호 : ${asd.bno} <br>
    <input type="checkbox" id="mustread" name="mustread" ${asd.must_read == 0 ? "checked" : ""}><label for="mustread">필독</label><br>
    <select name="category">
        <option value="" style="display: none; color:#dddddd;" ${empty asd.category ? "" :"selected"}>게시판 선택</option>
        <option value="notice" ${asd.category=="notice"?"selected":""}>공지사항</option>
        <option value="event" ${asd.category=="event"?"selected":""}>이벤트</option>
    </select><br>
    <input type="text" name="title" placeholder="제목을 입력하세요" value = "${asd.title}"><br>
    <textarea name="contents" placeholder="내용을 입력하세요" rows="15">${asd.contents}</textarea><br>

    <c:choose>
        <c:when test="${modi==1}">
            <input type="submit" formaction="<c:url value="/modT"/>" value="수정"><br>
        </c:when>
        <c:otherwise>
            <button type="submit" >등록</button><br>
        </c:otherwise>
    </c:choose>
    <button type="button">취소</button><br>
</form>
</body>
</html>
