<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <%@ include file="../head.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/return-mng.css"/>">
    <script type="text/javascript" src="<c:url value='/resources/js/checkbox-ctrl.js'/>" defer></script>
    <script type="text/javascript" src="<c:url value='/resources/js/message.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/return-mng.js'/>" defer></script>
    <title>J&H</title>
</head>
<style>
</style>
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
                    <h2>취소/반품/교환 관리</h2>
                    <c:if test="${not empty sessionScope.msg}">
                        <div id="alert-message" style="display: none;">${sessionScope.msg}</div>
                        <c:remove var="msg" scope="session"/>
                    </c:if>
                    <div class="return_MNG">
                        <div class="status_menu">
                            <div style="${ph.sc.category eq "대기중" ? "border-color:black;" : ""}" data-link="<c:url value="/admin/return-mng"/>?category=대기중">
                                <div>대기중</div>
                                <div>${cntMap["대기중"]}</div>
                            </div>
                            <div style="${ph.sc.category eq "처리중" ? "border-color:black;" : ""}" data-link="<c:url value="/admin/return-mng"/>?category=처리중">
                                <div>처리중</div>
                                <div>${cntMap["처리중"]}</div>
                            </div>
                            <div style="${ph.sc.category eq "완료" ? "border-color:black;" : ""}" data-link="<c:url value="/admin/return-mng"/>?category=완료">
                                <div>완료</div>
                                <div>${cntMap["완료"]}</div>
                            </div>
                        </div>
                        <div class="modal_bg" style="display: none;"></div>
                        <div class="contents_modal" style="display: none;">
                            <div class="modal_title"><div>사유/내용</div><div><button id="modal_x"><i class="fa-solid fa-xmark"></i></button></div></div>
                            <div class="modal_main">
                                <div>
                                    <div style="font-weight:bold;">사유</div>
                                    <div id="modal_reason"></div>
                                </div>
                                <div><div style="font-weight:bold;">내용</div><div id="modal_content"></div></div>
                            </div>
                        </div>
                        <form method="post" action="<c:url value="/admin/return-status"/>?page=${ph.sc.page}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}">
                            <c:if test="${ph.sc.category ne '완료'}">
                                <div class="checked_tools">
                                    <button type="submit">${ph.sc.category eq '대기중' ? '처리' : '완료'}</button>
                                </div>
                            </c:if>
                            <div class="table_box">
                                <table class="return_table" border="1">
                                    <tr>
                                        <td style="width:1%;"><input type="checkbox" name="check_all"></td>
                                        <td style="width:5%">유형</td>
                                        <td style="width:10%">고유번호</td>
                                        <td style="width:10%">주문번호<br>상품ID</td>
                                        <td style="width:10%">색상/사이즈</td>
                                        <td style="width:5%">수량</td>
                                        <td style="width:10%">주문자명</td>
                                        <td style="width:10%">연락처</td>
                                        <td style="width:10%">주문일시</td>
                                        <td style="width:10%">사유/내용</td>
                                        <td style="width:10%">변경사이즈</td>
                                    </tr>
                                    <c:if test="${ph.totalCnt <= 0}">
                                        <tr><td style="padding:150px 0; font-size:24px" colspan="11">데이터가 존재하지 않습니다.</td></tr>
                                    </c:if>
                                    <c:forEach items="${list}" var="returns" varStatus="returnsStatus">
                                        <tr>
                                            <td><input type="checkbox" name="check_each" value="${returnsStatus.index}"></td>
                                            <td><c:choose><c:when test="${returns.type eq 'exchange'}">교환</c:when><c:when test="${returns.type eq 'return'}">반품</c:when><c:otherwise>취소</c:otherwise></c:choose><input type="hidden" name="returnsList[${returnsStatus.index}].type" value="${returns.type}"></td>
                                            <td>${returns.return_id}<input type="hidden" name="returnsList[${returnsStatus.index}].return_id" value="${returns.return_id}"></td>
                                            <td><a href="<c:url value="/product"/>?product_id=${returns.product_id}" target="_blank">${returns.product_id}</a><input type="hidden" name="returnsList[${returnsStatus.index}].product_id" value="${returns.product_id}"><br>${returns.order_no}</td>
                                            <td>${returns.order.color}/${returns.size}<input type="hidden" name="returnsList[${returnsStatus.index}].size" value="${returns.size}"></td>
                                            <td>${returns.quantity}<input type="hidden" name="returnsList[${returnsStatus.index}].order_no" value="${returns.order_no}"></td>
                                            <td>${returns.order.name}<input type="hidden" name="returnsList[${returnsStatus.index}].user_id" value="${returns.user_id}"></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${fn:length(returns.order.phone) == 11}">
                                                        ${fn:substring(returns.order.phone,0,3)}-${fn:substring(returns.order.phone,3,7)}-${fn:substring(returns.order.phone,7,11)}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${fn:substring(returns.order.phone,0,3)}-${fn:substring(returns.order.phone,3,6)}-${fn:substring(returns.order.phone,6,10)}
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><fmt:formatDate value="${returns.return_date}" pattern="yyyy/MM/dd HH:mm"/></td>
                                            <td>
                                                <button type="button" data-reason="${returns.reason}" data-contents="${returns.contents}" class="contents_open_button">Open</button>
                                            </td>
                                            <td>${returns.c_size}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </form>
                        <div class="paging">
                            <c:if test="${ph.totalPage != null && ph.totalPage > 0}">
                                <c:if test="${ph.showPrev}">
                                    <a href="<c:url value="/admin/return-mng"/>?page=${ph.endPage-1}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                                <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                                    <a ${i == ph.sc.page ? "style='color:#FFAEC9;'" : ""}href="<c:url value="/admin/return-mng"/>?page=${i}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}">${i}</a>
                                </c:forEach>
                                <c:if test="${ph.showNext}">
                                    <a href="<c:url value="/admin/return-mng"/>?page=${ph.endPage+1}&category=${ph.sc.category}${not empty ph.sc.option ? "&option=" : ""}${ph.sc.option}${not empty ph.sc.keyword ? "&keyword=" : ""}${ph.sc.keyword}"><i class="fa-solid fa-angle-left"></i></a>
                                </c:if>
                            </c:if>
                        </div>
                        <div class="search_user">
                            <form action="<c:url value="/admin/return-mng"/>" method="get">
                                <select name="option">
                                    <option value="order_no" ${empty param.option || param.option == "order_no" ? "selected" : ""}>주문번호</option>
                                    <option value="return_id" ${param.option == "return_id" ? "selected" : ""}>요청번호</option>
                                    <option value="user_id" ${param.option == "user_id" ? "selected" : ""}>아이디</option>
                                </select>
                                <input type="hidden" name="category" value="${ph.sc.category}">
                                <input type="text" name="keyword" placeholder="검색어를 입력하세요" value="${param.keyword}"><button type="submit">검색</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>
