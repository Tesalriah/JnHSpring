<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/sideMenu.css'/>">
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
                <div class="left_menu">
                    <div><a href="">공지사항</a></div>
                    <div><a href="">이벤트</a></div>
                    <div><a href="">FAQ</a></div>
                </div>
                <div class="contents">
                    <div class="post_box">
                        <div class="post_button"><button type="button">이전글</button><button type="button">다음글</button>
                            <button type="button" onclick="location.href='<c:url value="noticeList"/>${sc.optionQueryString}'">목록</button></div>

                        <div class="notice_post">
                            <div class="notice_title">
                                <div>
                                    <span>${noticeDto.category}</span>
                                    <span><c:out value="${noticeDto.title}"/></span>
                                </div>
                                <div>
                                    <div>관리자</div>
                                    <div>${noticeDto.reg_date}2024-01-01</div>
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
                                <div class="post_each" ${dto.bno == noticeDto.bno ? "style='background-color:#f5f5f5;'" : ""}}>
                                    <div><span>999${dto.bno}</span><a href="<c:url value="notice"/>${sc.optionQueryString}&bno=${dto.bno}"><span>공지${dto.category}</span>일반 공지사항<c:out value="${dto.title}"/></a></div>
                                    <div>2024-01-01 00:00${dto.reg_date}</div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="post_button">
                        <button type="button" onclick="location.href='/jnh/noticeList'">목록</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>