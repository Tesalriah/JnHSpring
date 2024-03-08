<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true"%>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/f988057b70.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/login.css'/>">
    <title>로그인</title>
</head>
<body>
<div class="container">
    <div class="logo"><a href="<c:url value='/'/>">J&H</a></div>
    <script>
        msg = "${msg}";
        if(msg == "LOGIN_FAIL")alert("로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요.");
        if(msg == "SANCTIONED_USER")alert("제재된 사용자입니다. 고객센터에 문의해주세요.");
        if(msg == "WITHDREW")alert("탈퇴한 사용자입니다. 고객센터에 문의해주세요.");
    </script>
    <form method="post" action="<c:url value='/login'/>">
        <div class="input_id"><i class="fa-regular fa-id-card"></i><input type="text" name="id" value="${cookie.id.value}" placeholder="아이디"><br></div>
        <div class="input_pwd"><i class="fa-solid fa-lock"></i><input type="password" name="pwd" placeholder="비밀번호"><br></div>
        <div class="menu"><input type="checkbox" name="rememberId" id="save_id"  ${empty cookie.id.value ? "":"checked"}><label for="save_id">아이디 저장</label> <a href="findId.html">아이디 / 비밀번호 찾기</a></div>
        <input type="hidden" name="prevPage" value="${prevPage}">
        <div class="button_menu">
            <input type="submit" value="로그인">
            <a href="<c:url value='/register'/>">회원가입</a>
        </div>
    </form>
</div>
</body>
</html>