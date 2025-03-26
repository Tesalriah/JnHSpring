<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="head.jsp" %>
    <link rel="stylesheet" href="<c:url value='/resources/css/side-menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/about-as.css'/>">
</head>
<body>
    <%@ include file="header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/about-as.jpg"/> ">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">About As</div>
            </div>
            <div class="about_as">
                <div>
                    안녕하세요 J&H 입니다<br><br>
                    저희 브랜드는 트렌디함을  만들고<br><br>
                    당신만의 스타일을 찾을수있게 할 것입니다.<br><br>
                    당신이 원하는 것이 여기있습니다.<br><br>
                    <a href="<c:url value="/product"/>">지금 바로 확인하세요</a>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp" %>
</body>
</html>
