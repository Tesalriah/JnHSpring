<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <link rel="stylesheet" href="<c:url value='/resources/css/sideMenu.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/productList.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <c:if test="${sc.ph.sc.gender.equals('')}"><c:set var="img" value="All"></c:set></c:if>
                    <c:if test="${ph.sc.gender.equals('WOMEN')}"><c:set var="img" value="Women"></c:set></c:if>
                    <c:if test="${ph.sc.gender.equals('MEN')}"><c:set var="img" value="Men"></c:set></c:if>
                    <img src="<c:url value='/resources/img/list${img}.jpg'/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">
                    <c:if test="${!ph.sc.gender.equals('')}">
                        ${ph.sc.gender}
                    </c:if>
                    <c:if test="${ph.sc.gender.equals('')}">
                        ALL
                    </c:if>
                </div>
            </div>
            <div class="move">
                <div>
                    <a href="<c:url value="/"/>">HOME</a>
                    <c:if test="${!ph.sc.gender.equals('')}">
                         > <a href="<c:url value="/productList?gender=${ph.sc.gender}"/>">${ph.sc.gender}</a>
                    </c:if>
                    <c:choose>
                        <c:when test="${!ph.sc.category.equals('')}">
                            > ${ph.sc.category}
                        </c:when>
                        <c:otherwise>
                            > ALL
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="search_option">
                    <a href="<c:url value="/productList${ph.sc.getNonOption(ph.sc.page)}&option=product_id"/>">최신순</a><a href="<c:url value="/productList${ph.sc.getNonOption(ph.sc.page)}&option=rating"/>">평점순</a><a href="<c:url value="/productList${ph.sc.getNonOption(ph.sc.page)}&option=bougth_cnt"/>">판매량순</a>
                </div>
                <c:if test="${grade == 0}">
                    <div>
                        <a href="<c:url value="/addProduct"/> " id="addProduct">상품추가</a>
                    </div>
                </c:if>
            </div>
            <div classs="product_list">
                <c:forEach var="product" items="${list}" varStatus="status">
                    <c:if test="${status.index % 3 == 0}">
                        <div class="product_line">
                    </c:if>
                        <div class="product">
                            <a href="<c:url value="/product${ph.sc.queryString}&product_id=${product.product_id}"/>">
                                <img src="<c:url value='/resources/img/upload/${product.product_id}/${product.image}'/>">
                                <div class="detail">
                                    <div>${product.product_name}</div>
                                    <div>
                                        <c:if test="${product.discount != 0}">
                                            <fmt:formatNumber type="number" maxFractionDigits="0" value="${product.price - (product.price * (product.discount / 100))}"/>₩
                                            <del><fmt:formatNumber type="number" maxFractionDigits="0" value="${product.price}"/>₩</del>
                                        </c:if>
                                        <c:if test="${product.discount == 0}">
                                            {product.price}
                                        </c:if>
                                    </div>
                                </div>
                            </a>
                        </div>
                    <c:if test="${ status.index != 0 && (status.index- 2) % 3 == 0}">
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="paging">
                <c:if test="${totalCnt != null && totalCnt != 0}">
                    <c:if test="${ph.showPrev}">
                        <a href="<c:url value="/productList?${ph.sc.getQueryString(ph.beginPage-1)}"/>"><i class="fa-solid fa-angle-left"></i></a>
                    </c:if>
                    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                        <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/productList${ph.sc.getQueryString(i)}" />">${i}</a>
                    </c:forEach>
                    <c:if test="${ph.showNext}">
                        <a href="<c:url value="/productList?${ph.sc.getQueryString(ph.endpage+1)}"/>"><i class="fa-solid fa-angle-left"></i></a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>