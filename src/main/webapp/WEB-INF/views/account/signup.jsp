<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <script src="https://kit.fontawesome.com/f988057b70.js" crossorigin="anonymous"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
    <script type="text/javascript" src="<c:url value="/resources/js/address.js" />" defer></script>
    <script type="text/javascript" src="<c:url value="/resources/js/loading.js"/>" defer></script>
    <link rel="icon" href="<c:url value="/resources/favicon.ico"/>" type="image/x-icon">
    <link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/signup.css'/>">
    <title>J&H 회원가입</title>
</head>
<body>
<div class="container">
    <div class="logo"><a href="<c:url value='/'/>">J&H</a></div>
    <script>
        msg = "${msg}";
        if(msg == "REG_ERR")alert("회원가입에 실패했습니다. 다시 시도해주세요.")
    </script>
    <div class="user">
        <c:url value="/signup" var="registerUrl" />
        <form:form modelAttribute="user" action="${registerUrl}" method="post"> <%--class="user" method="post" action="<c:url value="/signUp"/>"--%>
            <div class="input"><i class="fa-regular fa-id-card"></i><input  type="text" name="user_id" value="${user.user_id}" placeholder="아이디" autocomplete="off"></div>
            <div class="error"><form:errors path="user_id"/></div>
            <div class="input"><i class="fa-solid fa-lock"></i><input type="password" name="user_pwd" placeholder="비밀번호"></div>
            <div class="error"><form:errors path="user_pwd"/></div>
            <div class="input"><i class="fa-solid fa-check"></i><input type="password" name="pwd_check" placeholder="비밀번호 확인"></div>
            <div class="loading_fix"><div class="loading_circle"></div></div>
            <div class="input"><i class="fa-solid fa-user"></i><input type="text" name="user_name" value="${user.user_name}" placeholder="이름" autocomplete="off"></div>
            <div class="error"><form:errors path="user_name"/></div>
            <div class="input"><i class="fa-solid fa-envelope"></i><input type="email" name="email" value="${user.email}" placeholder="이메일" autocomplete="off"></div>
            <div class="error"><form:errors path="email"/></div>
            <div class="input"><i class="fa-solid fa-mobile-screen"></i><input type="text" name="phone" value="${user.phone}" placeholder="휴대폰 번호(- 제외)" autocomplete="off"></div>
            <div class="error"><form:errors path="phone"/></div>
            <div class="input"><i class="fa-solid fa-location-dot"></i><input class="address" type="text" name="address1" value="${address1}" placeholder="주소" readonly><button class="find_address" type=button>주소찾기</button></div>
            <div class="error"><form:errors path="address"/></div>
            <div class="input" ${empty address2 ? 'style="display:none;"' : ""}><input type="text" class="address_detail" name="address2" value="${address2}" placeholder="상세주소" autocomplete="off"></div>
            <div class="input"><i class="fa-solid fa-venus-mars"></i><select name="gender"><option value="" ${empty user.gender ? "selected" : ""} disabled>성별</option><option value="남성" ${user.gender=="남성" ? "selected" : ""}>남성</option><option value="여성" ${user.gender=="여성" ? "selected" : ""}>여성</option></select></div>
            <div class="error"><form:errors path="gender"/></div>
            <div class="input"><i class="fa-solid fa-cake-candles"></i><input type="date" name="birth" value="${birth}"></div>
            <div class="error"><form:errors path="birth"/></div>
            <div class="button_menu">
                <input id="submit" type="submit" value="가입하기">
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
