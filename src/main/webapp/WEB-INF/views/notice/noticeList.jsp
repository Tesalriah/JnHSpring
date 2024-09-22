<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/sideMenu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/noticeList.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/book.jpg'/> ">
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
                    <h2>공지사항</h2>
                    <div class="top_nav" style="margin-top: 25px;">
                        <div ${ph.sc.option == null || ph.sc.option == "" ? 'style="border-bottom:none;"' : ""}>
                            <a href="<c:url value='/noticeList'/>">전체</a>
                        </div>
                        <div ${ph.sc.option == "notice" ? 'style="border-bottom:none;"' : ""}><a href="<c:url value='/noticeList'/>?option=notice">공지</a></div>
                        <div ${ph.sc.option == "event" ? 'style="border-bottom:none;"' : ""}><a href="<c:url value='/noticeList'/>?option=event ">이벤트</a></div>
                    </div>
                    <div class="notice_list">
                        <table>
                            <tr>
                                <td style="width: 10%;">번호</td>
                                <td style="width: 60%;">제목</td>
                                <td style="width: 10%;">작성자</td>
                                <td style="width: 20%;">작성일</td>
                            </tr>

                            <c:forEach var="noticeDto" items="${mustRead}">
                                <tr>
                                    <td>필독</td>
                                    <td><span> ${noticeDto.category.equals('notice') ? "공지" : "이벤트"}</span>
                                        <a href="<c:url value='/notice'/>${ph.sc.optionQueryString}&bno=${noticeDto.bno}"><c:out value="${noticeDto.title}"/></a>
                                    </td>
                                    <td>관리자</td>
                                    <td><fmt:formatDate value="${noticeDto.reg_date}" pattern="yyyy-MM-dd HH:mm" ></fmt:formatDate>
                                </tr>
                            </c:forEach>


                            <c:forEach var="notice" items="${list}">
                                <tr>
                                    <td>${notice.bno}</td>
                                    <td><span>${notice.category.equals('notice') ? "공지" : "이벤트"}</span>
                                        <a href="<c:url value='/notice'/>${ph.sc.optionQueryString}&bno=${notice.bno}"><c:out value="${notice.title}"/></a>
                                    </td>
                                    <td>관리자</td>
                                    <td><fmt:formatDate value="${notice.reg_date}" pattern="yyyy-MM-dd HH:mm" ></fmt:formatDate>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="post_button">
                            <button type="button" onclick="location.href='/jnh/noticeWrite'">글 작성</button>
                        </div>

                        <div class="paging">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value='/noticeList?page=${ph.beginPage-1}&pageSize=${ph.pageSize}'/>">&lt;</a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9'" : ""} href="<c:url value='/noticeList${ph.sc.getOptionQueryString(i)}'/>">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value='/noticeList?page=${ph.endPage+1}&pageSize=${ph.pageSize}'/>">&gt;</a>
                            </c:if>
                        </div>


                    </div>


                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>