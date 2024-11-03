<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <%@ include file="head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>">
    <script type="text/javascript" src="<c:url value='/resources/js/mainMenu.js'/>" defer></script>
    <script type="text/javascript" src="<c:url value='/resources/js/bannerSlide.js'/>" defer></script>
    <title>J&H</title>
</head>
<body>
    <%@ include file="header.jsp" %>
    <script>
        msg = "${empty msg ? param.msg : msg}";
        if(msg == "REG_OK")alert("이메일 인증이 완료되었습니다.");
        if(msg == "WRONG_APPROACH")alert("잘못된 접근입니다.");
        if(msg == "PWD_CHANGED")alert("비밀번호가 변경되었습니다.");
    </script>
    <main>
        <div class="container">
            <div class="move_img">
                <div class="banner">
                    <div class="banner_writing">
                        J&H
                        <div style="color:#333333">Make a Trandy</div>
                    </div>
                    <img src='<c:url value='/resources/img/main1.jpg'/>'>
                </div>
                <div class="banner">
                    <div class="banner_writing">
                        STYLE
                        <div>Found Your Style</div>
                    </div>
                    <img src='<c:url value='/resources/img/main2.jpg'/>'>
                </div>
                <div class="banner">
                    <div class="banner_writing">
                        POPULAR
                        <div>Here Is You Want</div>
                    </div>
                    <img src='<c:url value='/resources/img/main3.jpg'/>'>
                </div>
            </div>
            <button class="banner_left"><i class="fa-solid fa-angle-left" id=banner_left></i></button>
            <button class="banner_right"><i class="fa-solid fa-angle-right" id="banner_right"></i></button>
            <div class="banner_curcle">
                <button class="curcle1"><i class="fa-solid fa-circle" id="banner_curcle1"></i></button>
                <button class="curcle2"><i class="fa-solid fa-circle" id="banner_curcle2"></i></button>
                <button class="curcle3"><i class="fa-solid fa-circle" id="banner_curcle3"></i></button>
            </div>
        </div>
        <div class="menu_nav">
            <div class="two_menu">
                <div class="menu_img" onclick="location.href=''">
                    <div>MEN</div>
                    <img src="<c:url value='/resources/img/men.jpg'/>">
                </div>
                <div class="menu_img" onclick="location.href=''">
                    <div>WOMEN</div>
                    <img src="<c:url value='/resources/img/women.jpg'/>">
                </div>
            </div>
            <div class="two_menu">
                <div class="menu_img" onclick="location.href=''">
                    <div>BEST</div>
                    <img src="<c:url value='/resources/img/best.jpg'/>">
                </div>
                <div class="menu_img" onclick="location.href=''">
                    <div>WEEKLY PICK</div>
                    <img src="<c:url value='/resources/img/weekly.jpg'/>">
                </div>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>