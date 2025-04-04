<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>

<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/question-mng.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/question-mng.js'/>" defer></script>
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
                    <div class="top_nav">
                        <div><a href="<c:url value="/admin/ask-mng"/>">1:1 문의</a></div>
                        <div style=" border-bottom: none;"><a href="<c:url value="/admin/question-mng"/>">상품문의</a></div>
                    </div>
                    <div class="questions_contents" data-url="<c:url value="/admin/answer-write"/>?page=${ph.sc.page}">
                        <c:forEach items="${list}" var="question">
                            <div class="product_img">
                                <a href="<c:url value="/product"/>?product_id=${question.product_id}" target="_blank">
                                    <img src="<c:url value="/resources/img/upload/product-img/${question.product_id}/${question.product.image}"/>">
                                    ${question.product.product_name}
                                </a>
                            </div>
                            <div class="questions_each">
                                <div class="question_top">
                                    <span class="question_span">질문</span><a href="">${question.user_id}</a><div style="float: right; font-weight:100;"><fmt:formatDate value="${question.reg_date}" pattern="yyyy-MM-dd" /></div>
                                </div>
                                <div class="question_bottom">
                                    <c:out value="${question.contents}"/>
                                    <button class="answer_btn" data-qno="${question.qno}" data-product_id="${question.product_id}">답변하기</button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="paging">
                        <c:if test="${ph.totalPage >= 1}">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value="/admin/question-mng"/>?page=${ph.beginPage-1}"><i class="fa-solid fa-angle-left"></i></a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9'" : ""} href="<c:url value="/admin/question-mng"/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value="/admin/question-mng"/>?page=${ph.endPage+1}"><i class="fa-solid fa-angle-right"></i></a>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
