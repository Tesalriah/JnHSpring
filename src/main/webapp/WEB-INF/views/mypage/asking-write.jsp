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
    <link rel="stylesheet" href="<c:url value="/resources/css/asking-write.css"/>">

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
                    <h2>문의 ${modify == "1" ? "수정" : "작성"}</h2>

                    <c:choose>
                        <c:when test="${modify=='1'}">
                            <c:set var="action" value="modify"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="action" value="write"/>
                        </c:otherwise>
                    </c:choose>

                    <form action="<c:url value='/mypage/asking/${action}'/>" method="post">
                        <c:if test="${modify=='1'}">
                            <input type="hidden" name="no" value="${askingDto.no}">
                        </c:if>
                        <div class="write_ask">
                            <div class="ask_title">
                                <input type="text" name="title" placeholder="제목을 입력하세요" value="<c:out value='${askingDto.title}'/>">
                            </div>
                            <div class="ask_contents">
                                <textarea name="contents" rows="15" placeholder="내용을 입력하세요"><c:out value="${askingDto.contents}"/></textarea>
                            </div>
                            <div class="write_button">
                                <button type="submit">확인</button>
                                <button type="button" onclick="if(confirm('취소하겠습니까?')==true)
                                { location.href = '<c:url value="/mypage/asking/list"/>'; }">취소</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>