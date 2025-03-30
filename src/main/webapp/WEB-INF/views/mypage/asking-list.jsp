<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/asking-list.css"/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
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
                    <div class="top_nav">
                        <div style=" border-bottom: none;"><a href="<c:url value="/mypage/asking/list"/>">1:1 문의</a></div>
                        <div><a href="<c:url value="/mypage/asking/question/list"/>">상품문의</a></div>
                    </div>

                    <c:if test="${empty askingDtoList}">
                        <div style="text-align: center; padding:100px 20px; font-size: 24px;">문의내역이 존재하지 않습니다.</div>
                    </c:if>

                    <div class="asking_list">
                        <div>
                            <div>제목</div>
                            <div>상태</div>
                            <div>작성일</div>
                        </div>
                        <c:forEach var="askingDto" items="${askingDtoList}">
                            <div>
                                <div><a href="<c:url value="/mypage/asking/read"/>?no=${askingDto.no}&page=${ph.sc.page}">${askingDto.title}</a></div>
                                <div>${askingDto.state == 1 ? "답변완료" : "대기중"}</div>
                                <div><fmt:formatDate value="${askingDto.reg_date}" pattern="yyyy-MM-dd HH:mm" /></div>
                            </div>
                        </c:forEach>
                    </div>


                    <div class="paging">
                        <c:if test="${ph.showPrev}">
                            <a href="<c:url value='/mypage/asking/list'/>?page=${ph.beginPage-1}">&lt;</a>
                        </c:if>
                        <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                            <a ${i == ph.sc.page ? "style='color:#FFAEC9'" : ""} href="<c:url value='/mypage/asking/list'/>?page=${i}">${i}</a>
                        </c:forEach>
                        <c:if test="${ph.showNext}">
                            <a href="<c:url value='/mypage/asking/list'/>?page=${ph.endPage+1}">&gt;</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>