<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/notice.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/book.jpg'/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Notice</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <div class="post_box">
                        <div class="post_button">
<%--                            <button type="button" OnClick="if(confirm('삭제하시겠습니까?') == true){alert('취소를 누르셨습니다.'); location.href = '/jnh/noticeList'; }">취소</button>--%>
                            <form action="" method="post">
                                <c:if test="${user.grade == 0}">
                                    <input type="hidden" name="bno" value="${noticeDto.bno}">
                                    <input type="submit" formaction="<c:url value='/notice/remove'/>" value="삭제">
                                    <button type="button" onclick="location.href='<c:url value="/notice/modify"/>${sc.optionQueryString}&bno=${noticeDto.bno}'">수정</button>
                                </c:if>
                                <c:if test="${!empty prevNext[0]}">
                                    <button type="button" onclick="location.href='<c:url value="/notice/read"/>${sc.optionQueryString}&bno=${prevNext[0]}'">이전글</button>
                                </c:if>
                                <c:if test="${!empty prevNext[1]}">
                                    <button type="button" onclick="location.href='<c:url value="/notice/read"/>${sc.optionQueryString}&bno=${prevNext[1]}'">다음글</button>
                                </c:if>
                                <button type="button" onclick="location.href='<c:url value="/notice/list"/>${sc.optionQueryString}'">목록</button>
                            </form>
                        </div>

                        <div class="notice_post">
                            <div class="notice_title">
                                <div>
                                    <span>${noticeDto.category.equals('notice')? "공지":"이벤트"}</span>
                                    <span><c:out value="${noticeDto.title}"/></span>
                                </div>
                                <div>
                                    <div>관리자</div>
                                    <div><fmt:formatDate value="${noticeDto.reg_date}" pattern="yyyy-MM-dd HH:mm"/></div>
                                </div>
                            </div>
                            <div class="notice_contents">
                                <c:out value="${noticeDto.contents}"/>
                            </div>
                        </div>
                    </div>
                    <div class="list_box">
                        <h2>전체글</h2>
                        <div class="post_list">
                            <c:forEach items="${list}" var="dto">
                                <div class="post_each" ${dto.bno == noticeDto.bno ? "style='background-color:#f5f5f5;'" : ""}>
                                    <div>
                                        <span>${dto.bno}</span>
                                            <a href="<c:url value="/notice/read"/>${sc.optionQueryString}&bno=${dto.bno}">
                                                <span>${dto.category.equals('notice')? "공지":"이벤트"}</span><c:out value="${dto.title}"/>
                                            </a>
                                    </div>
                                    <div><fmt:formatDate value="${dto.reg_date}" pattern="yyyy-MM-dd HH:mm"/></div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="post_button">
                        <button type="button" onclick="location.href='<c:url value="/notice/list"/>${sc.optionQueryString}'">목록</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>