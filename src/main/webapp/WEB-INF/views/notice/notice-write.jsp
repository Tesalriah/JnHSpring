<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/notice-write.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <script>
        msg = "${msg}";
        if(msg == "MUST_READ_5")alert("필독은 5개 이상 작성하실 수 없습니다.");
        if(msg == "WRT_FAIL")alert("등록에 실패했습니다 다시 시도해 주세요.");
        if(msg == "NOT_BLANK")alert("모든 칸을 다 입력해주세요.");


    </script>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/book.jpg'/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Notice</div>
            </div>
            <div class="nav">
                <div class="left_menu">
                    <div><a href="<c:url value='/notice/list'/>?option=notice">공지사항</a></div>
                    <div><a href="<c:url value='/notice/list'/>?option=event">이벤트</a></div>
                    <div><a href="">FAQ</a></div>
                </div>
                <div class="contents">
                    <h2>게시글 ${modify == "1" ? "수정" : "작성"}</h2>
                    <div class="notice_write">

                        <form action="<c:url value='/notice/write'/>" method="post">
                            <c:if test="${modify=='1'}">
                                <input type="hidden" name="bno" value="${noticeDto.bno}">
                            </c:if>
                            <div class="mustread">
                                <input type="checkbox" id="mustread" name="mustread" ${checkBox == "1" ? "checked" : ""}><label for="mustread">필독</label>
                            </div>
                            <div class="title">
                                <select name="category">
                                    <option value="" style="display: none; color:#dddddd;" ${noticeDto.category == null ? "selected" : ""}>게시판 선택</option>
                                    <option value="notice" ${noticeDto.category == "notice" ? "selected" : ""}>공지사항</option>
                                    <option value="event" ${noticeDto.category == "event" ? "selected" : ""}>이벤트</option>
                                </select>
                                <input type="text" name="title" placeholder="제목을 입력하세요" value = "<c:out value='${noticeDto.title}'/>">
                            </div>
                            <div class="notice_contents">
                                <textarea name="contents" placeholder="내용을 입력하세요" rows="15"><c:out value="${noticeDto.contents}"/></textarea>
                            </div>
                            <div class="notice_button">
                                <c:choose>
                                    <c:when test="${modify == '1'}">
                                        <input type="submit" formaction="<c:url value="/notice/modify"/>${sc.optionQueryString}" value="수정" >
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" onclick="confirm('등록하시겠습니까?')">등록</button>
                                    </c:otherwise>
                                </c:choose>
                                <button type="button" OnClick="if(confirm('취소하시겠습니까?') == true){ location.href = '+ <c:url value="/notice/list"/> +'; }">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>