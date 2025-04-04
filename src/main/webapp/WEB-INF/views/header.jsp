<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty sessionScope.msg}">
    <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
    <c:remove var="msg" scope="session"/>
</c:if>
<header>
    <div class="header_area">
        <div class="row">
            <div class="logo_area">
                <div><a class="logo" href="<c:url value='/'/>">J&H</a></div>
            </div>
            <div class="menu_list">
                <ul class="top_menu">
                    <li class="gender_on">
                        <a href="<c:url value="/product-list"/>">ALL&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav">
                            <li><a href="<c:url value="/product-list"/>?category=TOPS">TOPS</a></li>
                            <li><a href="<c:url value="/product-list"/>?category=BOTTOM">BOTTOM</a></li>
                            <li><a href="<c:url value="/product-list"/>?category=OUTER">OUTER</a></li>
                        </ul>
                    </li>
                    <li class="gender_on">
                        <a href="<c:url value="/product-list"/>?gender=MEN">MEN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav">
                            <li><a href="<c:url value="/product-list"/>?gender=MEN&category=TOPS">TOPS</a></li>
                            <li><a href="<c:url value="/product-list"/>?gender=MEN&category=BOTTOM">BOTTOM</a></li>
                            <li><a href="<c:url value="/product-list"/>?gender=MEN&category=OUTER">OUTER</a></li>
                        </ul>
                    </li>
                    <li class="gender_on">
                        <a href="<c:url value="/product-list?gender=WOMEN"/>">WOMEN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav">
                            <li><a href="<c:url value="/product-list"/>?gender=WOMEN&category=TOPS">TOPS</a></li>
                            <li><a href="<c:url value="/product-list"/>?gender=WOMEN&category=BOTTOM">BOTTOM</a></li>
                            <li><a href="<c:url value="/product-list"/>?gender=WOMEN&category=OUTER">OUTER</a></li>
                        </ul>
                    </li>
                    <li><a href="<c:url value="/notice/list" />">SERVICE</a></li>
                    <c:if test="${sessionScope.user.grade == 0}">
                        <li class="gender_on">
                            <a href="<c:url value="/admin/product-mng"/>">ADMIN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                            <ul class="submenu_nav" >
                                <li><a href="<c:url value="/admin/product-mng"/>">상품관리</a></li>
                                <li><a href="<c:url value="/admin/user-mng"/>">유저관리</a></li>
                            </ul>
                        </li>
                    </c:if>
                </ul>
            </div>
            <div class="tools">
                <div>
                    <a href="javascript:void(0);" id="open_search"><i class="fa-solid fa-magnifying-glass"></i></a>
                    <div class="search_area">
                        <button id="close_search" type="button" class="search_close"><i class="fa-solid fa-xmark"></i></button>
                        <form method="get" action="<c:url value="/product-list"/>${empty ph ? sc.queryString : ph.sc.queryString}">
                            <input type="hidden" name="gender" value="${ph.sc.gender}">
                            <input type="hidden" name="category" value="${ph.sc.category}">
                            <input type="text" id="search" name="keyword" value="${ph.sc.keyword}" placeholder="검색어를 입력해주세요" autocomplete="off"><button type="submit" id="search_button"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </form>
                    </div>
                </div>
                <div id="user_util">
                    <a href="<c:url value='${mypageLink}'/>"><i class="fa-solid fa-user"></i></a>
                    <c:if test="${mypageLink != '/login'}">
                        <ul class="user_menu" >
                            <li><a href="<c:url value="/mypage/order/list"/>">주문목록</a></li>
                            <li><a href="<c:url value="/mypage/return/list"/>">취소/반품</a></li>
                            <li><a href="<c:url value="/mypage/wish/list"/>">찜 목록</a></li>
                        </ul>
                    </c:if>
                </div>
                <div><a href="<c:url value="/cart"/>${empty ph ? sc.queryString : ph.sc.queryString}"><i class="fa-solid fa-cart-shopping"></i></a></div>
                <div style="font-size:20px;"><a href="<c:url value='${logOutLink}'/>">${logOut}</a></div>
            </div>
        </div>
    </div>
</header>
