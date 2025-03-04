<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/question-list.css"/>">
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
                        <div><a href="<c:url value="/mypage/asking/list"/>">1:1 문의</a></div>
                        <div style=" border-bottom: none;"><a href="<c:url value="/mypage/asking/question/list"/>">상품문의</a></div>
                    </div>
                    <div class="questions_contents">
                        <c:forEach var="list" items="${qList}">
                            <c:choose>
                                <c:when test="${list.ano == 1}">
                                    <div class="questions_each">
                                </c:when>
                                <c:otherwise>
                                    <div class="answer_each">
                                </c:otherwise>
                            </c:choose>
                                <div class="question_top">
                                    <span class="${list.ano == 1 ? "question" : "answer"}_span">${list.ano == 1 ? "질문" : "답변"}</span>
                                    <a href="<c:url value="/product?product_id=${list.product_id}"/>" target="_blank">${list.product.product_name}</a>
                                    <div style="float: right; font-weight:100;"><fmt:formatDate value="${list.reg_date}" pattern="yyyy-MM-dd"/></div>
                                </div>
                                <div class="question_bottom">
                                    질문내용
                                </div>
                            </div>
                        </c:forEach>
                        </div>
                        <div class="paging">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/mypage/asking/question/list"/>?page=${ph.beginPage-1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a style="${ph.sc.page == i ? "color:pink;" : ""}" href="<c:url value="/mypage/asking/question/list"/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/mypage/asking/question/list"/>?page=${ph.endPage+1}"><i class="fa-solid fa-angle-right"></i></a>
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