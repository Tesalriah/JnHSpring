<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/user-mng.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/user-mng.js'/>" defer></script>
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
                    <h2>유저관리</h2>
                    <div class="user_MNG">
                        <table class="user_table" border="1">
                            <tr>
                                <td style="width:5%;">등급</td>
                                <td style="width:7%;">누적신고수</td>
                                <td style="width:11%;">아이디</td>
                                <td style="width:8%;">이름</td>
                                <td style="width:17%;">메일</td>
                                <td style="width:10%;">휴대폰번호</td>
                                <td style="width:5%;">주소</td>
                                <td style="width:5%;">성별</td>
                                <td style="width:10%;">생년월일</td>
                                <td style="width:5%;">상태</td>
                                <td style="width:7%;">계정정지</td>
                                <td style="width:10%;">가입일</td>
                            </tr>
                            <c:if test="${ph.totalCnt < 1}">
                                <tr>
                                    <td colspan="12" style="text-align: center; padding: 70px 0;">일치하는 회원이 없습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${list}" var="user">
                                <tr>
                                    <td>회원</td>
                                    <td>${user.cumulative_report}</td>
                                    <td>${user.user_id}</td>
                                    <td>${user.user_name}</td>
                                    <td>${user.email}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${fn:length(user.phone) == 11}">
                                                ${fn:substring(user.phone,0,3)}-${fn:substring(user.phone,3,7)}-${fn:substring(user.phone,7,11)}
                                            </c:when>
                                            <c:otherwise>
                                                ${fn:substring(user.phone,0,3)}-${fn:substring(user.phone,3,6)}-${fn:substring(user.phone,6,10)}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><button type="button" class="address_blind" data-address="${user.address}">blind</button></td>
                                    <td>${user.gender}</td>
                                    <td><fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.status == 0}"><span data-user_id="${user.user_id}" style="color:lightgreen">정상</span></c:when>
                                            <c:when test="${user.status == 1}"><span data-user_id="${user.user_id}" style="color:red">정지</span></c:when>
                                            <c:when test="${user.status == 2}"><span data-user_id="${user.user_id}" style="color:red">탈퇴</span></c:when>
                                            <c:when test="${user.status == 3}"><span data-user_id="${user.user_id}" style="color:orange">대기</span></c:when>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${user.status == 0}"><button type="button" class="user_suspension" data-user_id="${user.user_id}">정지</button></c:when>
                                            <c:when test="${user.status == 1}"><button type="button" class="user_unsuspend" data-user_id="${user.user_id}">해제</button></c:when>
                                            <c:otherwise><button type="button" class="user_nothing" style="color:#dddddd;">정지</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><fmt:formatDate value="${user.member_since}" pattern="yyyy-MM-dd" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="paging">
                        <c:if test="${ph.totalPage != null && ph.totalPage > 0}">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/admin/user-mng"/>?page=${ph.endPage-1}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/admin/user-mng"/>?page=${i}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/admin/user-mng"/>?page=${ph.endPage+1}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="search_user">
                        <form action="<c:url value="/admin/user-mng"/>" method="get">
                            <input type="text" name="keyword" value="${param.keyword}" placeholder="아이디를 입력해주세요"><button type="submit">검색</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
