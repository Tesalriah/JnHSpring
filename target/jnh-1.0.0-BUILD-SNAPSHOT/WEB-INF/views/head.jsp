<script src="https://kit.fontawesome.com/f988057b70.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/header.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/footer.css'/>">
<script type="text/javascript" src="<c:url value='/resources/js/openSearch.js'/>" defer></script>
<script type="text/javascript" src="<c:url value='/resources/js/fixedMenu.js'/>" defer></script>
<c:set var="loginId" value="${pageContext.request.getSession(false) == null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="mypageLink" value="${empty loginId ? '/login' : '/mypage'}"/>
<c:set var="logOutLink" value="${empty loginId ? '/register' : '/logout'}"/>
<c:set var="logOut" value="${empty loginId ? 'SignUp' : 'LogOut'}"/>