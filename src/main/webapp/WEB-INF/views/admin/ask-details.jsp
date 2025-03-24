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
                <div style="font-family: 'Raleway', sans-serif;">여기수정중Admin Page</div>
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
                                <button type="button" onclick="location.href='<c:url value="/admin/ask-mng"/>'" >목록</button>
                            </div>
                        </form>

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

                        <c:if test="${not empty askingDto[1]}">
                            <div class="ask_post" style="margin-top: 20px;">
                                <div class="ask_title">
                                    <div><span>A</span><span>관리자 답변</span></div>
                                    <div>
                                        <div>관리자</div>
                                        <div><fmt:formatDate value="${askingDto[1].reg_date}" pattern="yyyy-MM-dd HH:mm" /></div>
                                    </div>
                                </div>
                                <div class="ask_contents">${askingDto[1].contents}</div>
                            </div>

                            <form method="post" action="<c:url value="/admin/remove"/>">
                                <div class="post_button">
                                    <input type="hidden" name="no" value="${askingDto[1].no}">
                                    <input type="hidden" name="cno" value="2">
                                    <button type="submit" onclick="return deleteConfirm()">삭제</button>
                                </div>
                            </form>
                        </c:if>

                        <c:if test="${empty askingDto[1]}">
                            <div class="answer_write">
                                <h2>답변작성</h2>
                                <div class="write_flex">
                                    <textarea name="report_answer" rows="5" placeholder="답변작성"></textarea><button type="button">등록</button>
                                </div>
                            </div>
                        </c:if>
                    </div>





                    <div class="list_box">
                        <h2>전체글</h2>
                        <div class="post_list">
                            <div class="post_each">
                                <div><a href="">문의합니다.</a></div>
                                <div class="id_date"><div>red121</div><div>2024-01-01 00:00</div></div>
                            </div>
                            <div class="post_each">
                                <div><a href="">문의합니다.</a></div>
                                <div class="id_date"><div>asd123</div><div>2024-01-01 00:00</div></div>
                            </div>
                            <div class="post_each" style="background-color: #f5f5f5;">
                                <div><a href="">문의합니다.</a></div>
                                <div class="id_date"><div>asd123</div><div>2024-01-01 00:00</div></div>
                            </div>
                            <div class="post_each">
                                <div><a href="">문의합니다.</a></div>
                                <div class="id_date"><div>zxc123</div><div>2024-01-01 00:00</div></div>
                            </div>
                            <div class="post_each">
                                <div><a href="">문의합니다.</a></div>
                                <div class="id_date"><div>abc123</div><div>2024-01-01 00:00</div></div>
                            </div>
                        </div>
                    </div>
                    <div class="post_button">
                        <button type="button" onclick="location.href='<c:url value="/admin/ask-mng"/>'" >목록</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
