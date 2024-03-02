<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<title>J&H</title>
</head>
<body>
<header>
    <div class="header_area">
        <div class="row">
            <div class="logo_area">
                <div><a class="logo" href="<c:url value='/'/>">J&H</a></div>
            </div>
            <div class="menu_list">
                <ul class="top_menu">
                    <li><a href="">HOME</a></li>
                    <li class="gender_on">
                        <a href="">MEN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav">
                            <li><a href="">TOPS</a></li>
                            <li><a href="">BOTTOM</a></li>
                            <li><a href="">OUTER</a></li>
                        </ul>
                    </li>
                    <li class="gender_on">
                        <a href="">WOMEN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav">
                            <li><a href="">TOPS</a></li>
                            <li><a href="">BOTTOM</a></li>
                            <li><a href="">OUTER</a></li>
                        </ul>
                    </li>
                    <li><a href="">SERVICE</a></li>
                    <li class="gender_on" style="display:none;">
                        <a href="">ADMIN&nbsp;<i class="fa-solid fa-chevron-down"></i></a>
                        <ul class="submenu_nav" >
                            <li><a href="">유저관리</a></li>
                            <li><a href="">상품관리</a></li>
                        </ul>
                    </li>
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
                <div><a href="<c:url value='/login'/>"><i class="fa-solid fa-user"></i></a></div>
                <div><a href=""><i class="fa-solid fa-cart-shopping"></i></a></div>
                <div style="font-size:20px;"><a href="<c:url value='/register'/>">Sign UP</a></div>
            </div>
        </div>
    </div>
</header>