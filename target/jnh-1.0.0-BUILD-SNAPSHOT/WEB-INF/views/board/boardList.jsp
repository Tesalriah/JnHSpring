<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/sideMenu.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/noticeList.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/js/productMNG.js'/>" defer></script>
    <script type="text/javascript" src="<c:url value='/resources/js/category.js'/>" defer></script>
    <title>J&H</title>
</head>
<body>
<%@ include file="../header.jsp" %>
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
                <h2>공지사항</h2>
                <div class="top_nav" style="margin-top: 25px;">
                    <div style=" border-bottom: none;"><a href="">전체</a></div>
                    <div><a href="">공지</a></div>
                    <div><a href="">이벤트</a></div>
                </div>
                <div class="notice_list">
                    <table>
                        <tr>
                            <td style="width: 10%;">번호</td>
                            <td style="width: 60%;">제목</td>
                            <td style="width: 10%;">작성자</td>
                            <td style="width: 20%;">작성일</td>
                        </tr>
                        <tr>
                            <td>필독</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>필독</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>필독</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>필독</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>필독</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>1000</td>
                            <td><span>이벤트</span><a href="">이벤트</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>999</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>998</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>997</td>
                            <td><span>이벤트</span><a href="">이벤트</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>996</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>995</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>994</td>
                            <td><span>이벤트</span><a href="">이벤트</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>993</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>992</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                        <tr>
                            <td>991</td>
                            <td><span>공지</span><a href="">일반 공지사항</a></td>
                            <td>관리자</td>
                            <td>2024-01-01 00:00</td>
                        </tr>
                    </table>
                    <div class="paging">
                        <a href=""><i class="fa-solid fa-angle-left"></i></a>
                        <a href="">1</a>
                        <a href="">2</a>
                        <a href="">3</a>
                        <a href="">4</a>
                        <a href="">5</a>
                        <a href=""><i class="fa-solid fa-angle-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<%@ include file="../footer.jsp" %>
</body>
</html>
