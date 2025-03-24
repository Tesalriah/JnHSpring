<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/faq-write.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <script>
        msg = "${msg}";
        if(msg == "WRT_FAIL")alert("등록에 실패했습니다 다시 시도해 주세요.");
        if(msg == "NOT_BLANK")alert("모든 칸을 다 입력해주세요.");

        function confirmSubmit() {
            return confirm("등록하시겠습니까?");
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
                    <h2>게시글 ${modify == "1" ? "수정" : "작성"}</h2>
                    <div class="notice_write">

                        <form action="<c:url value='/FAQ/write'/>" method="post" onsubmit="return confirmSubmit()">
                            <c:if test="${modify=='1'}">
                                <input type="hidden" name="no" value="${faq.no}"> <!-- 기존 데이터를 유지 -->
                            </c:if>
                            <div class="title">
                                <select name="question_type">
                                    <option value="" style="display: none; color:#dddddd;" ${faq.question_type == "" ? "selected" : ""}>게시판 선택</option>
                                    <option value="상품문의" ${faq.question_type == "상품문의" ? "selected" : ""}>상품문의</option>
                                    <option value="주문/배송" ${faq.question_type == "주문/배송" ? "selected" : ""}>주문/배송</option>
                                    <option value="반품/교환" ${faq.question_type == "반품/교환" ? "selected" : ""}>반품/교환</option>
                                    <option value="기타" ${faq.question_type == "기타" ? "selected" : ""}>기타</option>
                                </select>
                                <input type="text" name="question" placeholder="제목을 입력하세요" value = "<c:out value='${faq.question}'/>">
                            </div>
                            <div class="notice_contents">
                                <textarea name="answer" placeholder="내용을 입력하세요." rows="10"><c:out value="${faq.answer}"/></textarea>
                            </div>
                            <div class="notice_button">
                                <c:choose>
                                    <c:when test="${modify == '1'}">
                                        <input type="submit" formaction="<c:url value='/FAQ/modify'/>" value="수정" >
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit">등록</button>
                                    </c:otherwise>
                                </c:choose>
                                <button type="button" OnClick="if(confirm('취소하시겠습니까?')){ location.href ='<c:url value="/FAQ/list"/>${sc.optionQueryString}';}">취소</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>