<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/faq.css'/>">
        <script type="text/javascript" src="<c:url value="/resources/js/faq.js"/>" defer></script>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>

    <script>
        function confirmDelete() {
            return confirm("정말 삭제하시겠습니까?");
        }
    </script>

    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/faq.jpg'/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">FAQ</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <div class="top_nav">
                        <a href="<c:url value='/FAQ/list'/>" ${empty param.option ? "style='text-decoration: underline;'" : ""}>전체</a>
                        <a href="<c:url value='/FAQ/list'/>?option=상품문의" ${param.option == "상품문의"? "style='text-decoration: underline;'" : ""}>상품문의</a>
                        <a href="<c:url value='/FAQ/list'/>?option=주문/배송" ${param.option == "주문/배송"? "style='text-decoration: underline;'" : ""}>주문/배송</a>
                        <a href="<c:url value='/FAQ/list'/>?option=반품/교환" ${param.option == "반품/교환"? "style='text-decoration: underline;'" : ""}>반품/교환</a>
                        <a href="<c:url value='/FAQ/list'/>?option=기타" ${param.option == "기타"? "style='text-decoration: underline;'" : ""}>기타</a>
                    </div>

                    <div class="faq">

                        <c:if test="${empty listAll}">
                            <div style="text-align: center; padding:50px 20px; font-size: 24px;">준비중입니다.</div>
                        </c:if>

                        <c:forEach var="list" items="${listAll}">
                            <div class="faq_each">
                                <div class="question">
                                <div style="flex-grow: 0; flex-shrink: 1; flex-basis: 5%;">Q</div>
                                    <div class="question_type">
                                        <c:out value="${list.question_type}"/>
                                    </div>

                                    <div class="question_contents">
                                        <c:out value="${list.question}"/>
                                    </div>

                                    <c:if test="${user.grade==0}">
                                        <div class="admin_btn">
                                            <form action="" method="post" style="flex-grow: 0; flex-shrink: 1; flex-basis: 7%;">
                                                    <input type="hidden" name="no" value="${list.no}">
                                                    <input type="submit" formaction="<c:url value='/FAQ/remove'/>" value="삭제" onclick="return confirmDelete();">
                                                    <button type="button" onclick="location.href='<c:url value='/FAQ/modify'/>?no=${list.no}'">수정</button>
                                            </form>
                                        </div>
                                    </c:if>

                                </div>



                                <div class="answer">
                                    <div style="flex-grow: 0; flex-shrink: 1; flex-basis: 5%;">A</div>
                                    <div class="answer_contents">
                                        <p><c:out value="${list.answer}"/></p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${user.grade == 0}">
                        <div class="post_button">
                            <button type="button" onclick="location.href='<c:url value="/FAQ/write"/>'">글 작성</button>
                        </div>
                    </c:if>


                    <c:if test="${ph.totalPage} != 0">
                        <div class="paging">
                            <c:if test="${ph.showPrev}">
                                <a href="<c:url value='/FAQ/list'/>?page=${ph.beginPage-1}">&lt;</a>
                            </c:if>
                            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                <a ${i == ph.sc.page ? "style='color:#FFAEC9'" : ""} href="<c:url value='/FAQ/list'/>?page=${i}">${i}</a>
                            </c:forEach>
                            <c:if test="${ph.showNext}">
                                <a href="<c:url value='/FAQ/list'/>?page=${ph.endPage+1}">&gt;</a>
                            </c:if>
                        </div>
                    </c:if>
                </div>



            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>