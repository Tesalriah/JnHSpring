<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/asking.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/asking.js'/>" defer></script>

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
                    <h2>문의</h2>
                    <div class="post_box">
                        <div class="post_button">
                            <button type="button" onclick="location.href='<c:url value="/mypage/asking/list"/>?page=${sc.page}'">목록</button>
                        </div>
                        <div class="ask_post">
                            <div class="ask_title">
                                <div><span>Q</span><span>${askingDto[0].title}</span></div>
                                <div>
                                    <div>${askingDto[0].user_id}</div>
                                    <div><fmt:formatDate value="${askingDto[0].reg_date}" pattern="yyyy-MM-dd HH:mm" /></div>
                                </div>
                            </div>
                            <div class="ask_contents">
                                ${askingDto[0].contents}
                            </div>
                        </div>
                        <div class="post_button" style="margin-top: 15px;">
                            <form method="post" action="">
                                <input type="hidden" name="no" value="${askingDto[0].no}">
                                <button type="button" onclick="location.href='<c:url value="/mypage/asking/modify"/>?no=${askingDto[0].no}&page=${sc.page}'">수정</button>
                                <button type="submit" formaction="<c:url value="/mypage/asking/remove"/>?page=${sc.page}" onclick="return deleteConfirm()">삭제</button>
                            </form>
                        </div>
                        <c:if test="${not empty askingDto[1]}">
                            <div class="ask_post">
                                <div class="ask_title">
                                    <div><span>A</span><span>관리자 답변</span></div>
                                    <div>
                                        <div>관리자</div>
                                        <div><fmt:formatDate value="${askingDto[1].reg_date}" pattern="yyyy-MM-dd HH:mm" /></div>
                                    </div>
                                </div>
                                <div class="ask_contents">
                                    ${askingDto[1].contents}
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <div class="list_box">
                        <h2>전체글</h2>
                        <div class="post_list">
                            <c:forEach items="${list}" var="dto">
                                <div class="post_each" ${dto.no == askingDto[0].no ? "style='background-color:#f5f5f5;'" : ""}>
                                    <div>
                                        <a href="<c:url value="/mypage/asking/read"/>?no=${dto.no}&page=${sc.page}">
                                            <c:out value="${dto.title}"/>
                                        </a>
                                    </div>
                                    <div><fmt:formatDate value="${dto.reg_date}" pattern="yyyy-MM-dd HH:mm"/></div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="post_button"><button type="button" onclick="location.href='<c:url value="/mypage/asking/list"/>?page=${sc.page}'">목록</button></div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>