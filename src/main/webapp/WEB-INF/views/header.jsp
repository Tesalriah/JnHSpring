<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="header_area">
        <div class="row">
            <div class="logo_area">
                <div><a class="logo" href="<c:url value='/'/>">J&H</a></div>
            </div>
            <div class="menu_list">
                <ul class="top_menu">
                    <li><a href="<c:url value='/'/>">HOME</a></li>
                    <li class="gender_on">
                        <a href="<c:url value="/productList?gender=MEN"/>">MEN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav">
                            <li><a href="<c:url value="/productList?gender=MEN&category=TOPS"/>">TOPS</a></li>
                            <li><a href="<c:url value="/productList?gender=MEN&category=BOTTOM"/>">BOTTOM</a></li>
                            <li><a href="<c:url value="/productList?gender=MEN&category=OUTER"/>">OUTER</a></li>
                        </ul>
                    </li>
                    <li class="gender_on">
                        <a href="<c:url value="/productList?gender=WOMEN"/>">WOMEN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav">
                            <li><a href="<c:url value="/productList?gender=WOMEN&category=TOPS"/>">TOPS</a></li>
                            <li><a href="<c:url value="/productList?gender=WOMEN&category=BOTTOM"/>">BOTTOM</a></li>
                            <li><a href="<c:url value="/productList?gender=WOMEN&category=OUTER"/>">OUTER</a></li>
                        </ul>
                    </li>
                    <li><a href="<c:url value="/noticeList" />">SERVICE</a></li>
                    <c:if test="${grade == 0}">
                        <li class="gender_on">
                            <a href="">ADMIN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                            <ul class="submenu_nav" >
                                <li><a href="">유저관리</a></li>
                                <li><a href="">상품관리</a></li>
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
                        <form method="post" action="">
                            <input type="text" id="search" name="search" placeholder="검색어를 입력해주세요" autocomplete="off"><button id="search_button"><i class="fa-solid fa-magnifying-glass"></i></button>
                        </form>
                    </div>
                </div>
                <div><a href="<c:url value='${mypageLink}'/>"><i class="fa-solid fa-user"></i></a></div>
                <div><a href="<c:url value="/cart"/>${empty ph ? sc.queryString : ph.sc.queryString}"><i class="fa-solid fa-cart-shopping"></i></a></div>
                <div style="font-size:20px;"><a href="<c:url value='${logOutLink}'/>">${logOut}</a></div>
            </div>
        </div>
    </div>
</header>
