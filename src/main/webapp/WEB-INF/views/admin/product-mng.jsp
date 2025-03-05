<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/product-mng.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/product-mng.js'/>" defer></script>
    <title>J&H</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/admin.png"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Admin Page</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <h2>상품관리</h2>
                    <div style="display: flex; justify-content: space-between; margin-top:15px; align-items: center">
                        <div class="MNG_nav">
                            <div class="nav_each">
                                <a href="<c:url value="/admin/product-mng"/>?gender=MEN">MEN</a>
                                <div class="sub_MNG_nav">
                                    <div><a href="<c:url value="/admin/product-mng"/>?gender=MEN&category=TOPS">TOPS</a></div>
                                    <div><a href="<c:url value="/admin/product-mng"/>?gender=MEN&category=BOTTOM">BOTTOM</a></div>
                                    <div><a href="<c:url value="/admin/product-mng"/>?gender=MEN&category=OUTER">OUTER</a></div>
                                </div>
                            </div>
                            <div class="nav_each">
                                <a href="<c:url value="/admin/product-mng"/>?gender=WOMEN">WOMEN</a>
                                <div class="sub_MNG_nav">
                                    <div><a href="<c:url value="/admin/product-mng"/>?gender=WOMEN&category=TOPS">TOPS</a></div>
                                    <div><a href="<c:url value="/admin/product-mng"/>?gender=WOMEN&category=BOTTOM">BOTTOM</a></div>
                                    <div><a href="<c:url value="/admin/product-mng"/>?gender=WOMEN&category=OUTER">OUTER</a></div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <form method="get" action="<c:url value="/admin/product-mng"/>${ph.sc.getQueryString(1)}">
                                <input id="admin_keyword" type="text" name="keyword" value="${ph.sc.keyword}" placeholder="품번 또는 상품명" autocomplete="off">
                                <input type="hidden" name="gender" value="${ph.sc.gender}">
                                <input type="hidden" name="category" value="${ph.sc.category}">
                                <input id="admin_search" type="submit" value="검색">
                            </form>
                        </div>
                    </div>
                    <div class="point">MEN <i class="fa-solid fa-angle-right"></i> TOPS</div>
                    <div class="MNG_box">
                        <table class="MNG_table">
                            <tr>
                                <td style="width: 15%;">품번</td>
                                <td style="width: 15%;">상품명</td>
                                <td style="width: 15%;">가격</td>
                                <td style="width: 15%;">할인율(%)</td>
                                <td style="width: 15%;">재고</td>
                                <td style="width: 8%;">상세수정</td>
                            </tr>
                            <c:forEach items="${list}" var="productList">
                                <tr>
                                    <td>${productList.product_id}</td>
                                    <td>${productList.product_name} / ${productList.size}</td>
                                    <td><input type="text" class="product_price" value="${productList.price}" data-product_id="${productList.product_id}"><button type="button" class="send-btn" data-action="<c:url value="/admin/setPrice"/>" data-product_id="${productList.product_id}" data-size="${productList.size}" data-type="price">OK</button></td>
                                    <td><input type="text" class="product_discount" value="${productList.discount}"><button type="button" class="send-btn" data-action="<c:url value=""/>" data-product_id="${productList.product_id}}" data-size="${productList.size}" data-type="discount">OK</button></td>
                                    <td><input type="text" class="product_stock" value="${productList.stock}"><button type="button" class="send-btn" data-action="<c:url value=""/>" data-product_id="${productList.product_id}}" data-size="${productList.size}" data-type="stock">OK</button></td>
                                    <td><a href="">이동</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <div class="paging">
                            <c:if test="${ph.totalCnt != null && ph.totalCnt != 0}">
                                <c:if test="${ph.showPrev}">
                                    <a href="<c:url value="/admin/product-mng"/>${ph.sc.getQueryString(ph.beginPage-1)}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                    <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/admin/product-mng"/>${ph.sc.getQueryString(i)}">${i}</a>
                                </c:forEach>
                                <c:if test="${ph.showNext}">
                                    <a href="<c:url value="/admin/product-mng"/>${ph.sc.getQueryString(ph.endPage+1)}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
