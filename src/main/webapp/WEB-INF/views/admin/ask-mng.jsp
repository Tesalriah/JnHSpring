<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/ask.css"/>">
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
                    <h2>문의관리</h2>
                    <div class="ask_MNG">
                        <table class="ask_table" border="1">

                            <c:if test="${empty readAll}">
                                <div style="text-align: center; padding:100px 20px; font-size: 24px;">문의내역이 존재하지 않습니다.</div>
                            </c:if>

                            <tr>
                                <td style="width:10%;">문의번호</td>
                                <td style="width:55%;">제목</td>
                                <td style="width:10%;">상태</td>
                                <td style="width:10%;">작성자</td>
                                <td style="width:15%;">작성일자</td>
                            </tr>



                            <c:forEach var="list" items="${readAll}">
                                <tr>
                                    <td>${list.no}</td>
                                    <td><a href="<c:url value="/admin/ask-details"/>?no=${list.no}">${list.title}</a></td>
                                    <td>${list.state == 1? "답변완료":"대기중"}</td>
                                    <td>${list.user_id}</td>
                                    <td><fmt:formatDate value="${list.reg_date}" pattern="yyyy-MM-dd" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="paging">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/admin/ask-mng"/>?page=${ph.beginPage-1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9'" : ""} href="<c:url value="/admin/ask-mng"/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/admin/ask-mng"/>?page=${ph.endPage+1}"><i class="fa-solid fa-angle-right"></i></a>
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
