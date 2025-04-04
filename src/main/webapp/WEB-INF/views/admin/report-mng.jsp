<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/report-mng.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/report-mng.js'/>" defer></script>
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
                    <h2>신고관리</h2>
                    <div class="report_MNG">
                        <table class="report_table" border="1">
                            <tr>
                                <td style="width:10%;">신고번호</td>
                                <td style="width:10%;">리뷰번호</td>
                                <td style="width:10%;">신고대상</td>
                                <td style="width:45%;">신고사유</td>
                                <td style="width:10%;">신고자</td>
                                <td style="width:15%;">작성일자</td>
                            </tr>
                            <c:if test="${empty list}">
                                <tr>
                                    <td colspan="6">
                                        <div style="text-align: center; padding:100px 20px; font-size: 24px;">신고내역이 존재하지 않습니다.</div>
                                    </td>
                                </tr>
                            </c:if>
                            <c:forEach items="${list}" var="report">
                                <tr>
                                    <td>${report.no}</td>
                                    <td><a target="_blank" href="<c:url value="/admin/review-mng"/>?option=rno&keyword=${report.rno}">${report.rno}</a></td>
                                    <td><a href="<c:url value="/admin/user-mng"/>?keyword=${report.user_id}" target="_blank">${report.user_id}</a></td>
                                    <td class="contents_open">
                                        <pre><c:out value="${report.reason}"/></pre>
                                    </td>
                                    <td>${report.reporter_id}</td>
                                    <td><fmt:formatDate value="${report.reg_date}" pattern="yyyy-MM-dd" /></td>
                                </tr>
                                <tr class="report_contents" >
                                    <td colspan="6" >
                                        <pre><c:out value="${report.contents}"/></pre>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="paging">
                            <c:if test="${ph.totalPage != null && ph.totalPage > 0}">
                                <c:if test="${ph.showPrev}">
                                    <a href="<c:url value="/admin/report-mng"/>?page=${ph.endPage-1}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                    <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/admin/report-mng"/>?page=${i}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}">${i}</a>
                                </c:forEach>
                                <c:if test="${ph.showNext}">
                                    <a href="<c:url value="/admin/report-mng"/>?page=${ph.endPage+1}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                            </c:if>
                        </div>
                        <div class="search_user">
                            <form action="<c:url value="/admin/report-mng"/>" method="get">
                                <input type="text" name="keyword" placeholder="신고대상 아이디" value="${param.keyword}"><button type="submit">검색</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
