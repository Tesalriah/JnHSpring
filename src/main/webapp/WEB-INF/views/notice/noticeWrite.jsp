<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/sideMenu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/noticeWrite.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <script>
        msg = "${msg}";
        if(msg == "MUST_READ_5")alert("필독은 5개 이상 작성하실 수 없습니다.");
        if(msg == "NOT_BLANK")alert("모든 칸을 다 입력해주세요.");
    </script>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value='/resources/img/book.jpg'/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Notice</div>
            </div>
            <div class="nav">
                <div class="left_menu">
                    <div><a href="">공지사항</a></div>
                    <div><a href="">이벤트</a></div>
                    <div><a href="">FAQ</a></div>
                </div>
                <div class="contents">
                    <h2>게시글 작성</h2>
                    <div class="notice_write">
                        <form action="<c:url value='/noticeWrite'/>" method="post">
                            <div class="mustread">
                                <input type="checkbox" id="mustread" name="mustread"><label for="mustread">필독</label>
                            </div>
                            <div class="title">
                                <select name="category">
                                    <option value="" style="display: none; color:#dddddd;" selected>게시판 선택</option>
                                    <option value="notice">공지사항</option>
                                    <option value="event">이벤트</option>
                                </select>
                                <input type="text" name="title" placeholder="제목을 입력하세요" value = "<c:out value='${noticeDto.title}'/>">
                            </div>
                            <div class="notice_contents">
                                <textarea name="contents" placeholder="내용을 입력하세요" rows="15"><c:out value="${noticeDto.contents}"/></textarea>
                            </div>
                            <div class="notice_button">
                                <button type="submit" onclick="confirm('등록하시겠습니까?')">등록</button>
                                <button type="button" OnClick="if(confirm('취소?') == true){javascript:history.back(-1)}else{return false }">취소</button>
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