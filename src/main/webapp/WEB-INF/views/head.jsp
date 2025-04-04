        <script src="https://kit.fontawesome.com/f988057b70.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="<c:url value='/resources/css/reset.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/header.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/footer.css'/>">
        <script type="text/javascript" src="<c:url value='/resources/js/open-search.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/fixed-menu.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>" defer></script>
        <c:set var="loginId" value="${pageContext.request.getSession(false) == null ? '' : sessionScope.user.user_id}"/>
        <c:set var="mypageLink" value="${empty loginId ? '/login' : '/mypage/order/list'}"/>
        <c:set var="logOutLink" value="${empty loginId ? '/register' : '/logout'}"/>
        <c:set var="logOut" value="${empty loginId ? 'SignUp' : 'LogOut'}"/>