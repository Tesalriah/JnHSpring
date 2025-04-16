<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/asking.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/ask-details.css"/>">

    <title>J&H</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/admin.png"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Admin Page</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>


                <div class="contents">
                    <div class="post_box">
                        <form action="" method="post">
                            <div class="post_button">
                                <c:if test="${!empty prevNext[0]}">
                                    <button type="button" onclick="location.href='<c:url value="/admin/ask-details"/>?bno=${prevNext[0]}'">이전글</button>
                                </c:if>
                                <c:if test="${!empty prevNext[1]}">
                                    <button type="button" onclick="location.href='<c:url value="/admin/ask-details"/>?bno=${prevNext[1]}'">다음글</button>
                                </c:if>
                                <button type="button" onclick="location.href='<c:url value="/admin/ask-mng"/>?page=${sc.page}'" >목록</button>
                            </div>
                        </form>

                        <div class="ask_post">
                            <div class="ask_title">
                                <div><span>Q</span><span><c:out  value="${askingDto[0].title}"/></span></div>
                                <div>
                                    <div>${askingDto[0].user_id}</div>
                                    <div><fmt:formatDate value="${askingDto[0].reg_date}" pattern="yyyy-MM-dd HH:mm" /></div>
                                </div>
                            </div>
                            <div class="ask_contents">
                                <pre><c:out value="${askingDto[0].contents}"/></pre>
                            </div>
                        </div>

                        <c:if test="${not empty askingDto[1]}">
                            <div class="ask_post" style="margin-top: 20px;">
                                <div class="ask_title">
                                    <div><span>A</span><span>관리자 답변</span></div>
                                    <div>
                                        <div>관리자</div>
                                        <div><fmt:formatDate value="${askingDto[1].reg_date}" pattern="yyyy-MM-dd HH:mm" /></div>
                                    </div>
                                </div>
                                <div class="ask_contents">
                                    <pre><c:out value="${askingDto[1].contents}"/></pre>
                                </div>
                            </div>

                            <form method="post" action="<c:url value="/admin/ask-remove"/>">
                                <div class="post_button">
                                    <input type="hidden" name="no" value="${askingDto[1].no}">
                                    <input type="hidden" name="cno" value="2">
                                    <button type="submit" onclick="return deleteConfirm()">삭제</button>
                                </div>
                            </form>
                        </c:if>

                        <c:if test="${empty askingDto[1]}">

                            <form action="<c:url value="/admin/ask-write"/>" method="post">
                                <div class="answer_write">
                                    <h2>답변작성</h2>
                                    <div class="write_flex">
                                        <input type="hidden" name="no" value="${askingDto[0].no}">
                                        <textarea name="contents" rows="5" placeholder="답변작성"></textarea>
                                        <button type="submit" onclick="return confirm('작성하시겠습니까?')">등록</button>
                                    </div>
                                </div>
                            </form>


                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
