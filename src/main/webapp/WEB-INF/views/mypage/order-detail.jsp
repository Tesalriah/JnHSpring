<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="<c:url value="/resources/css/side-menu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/order-detail.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/order-list.css"/>">
    <main>
        <div class="container">
            <div class="title">
                <div class="image">
                    <img src="<c:url value="/resources/img/house.jpg"/>">
                </div>
                <div style="font-family: 'Raleway', sans-serif;">Mypage</div>
            </div>
            <div class="nav">
                <%@ include file="left-menu.jsp" %>
                <div class="contents">
                    <h2>주문상세</h2>
                    <p style="padding-top:20px;">주문번호 : ${param.order_no}</p>
                    <div class="order_list">
                        <div class="order">
                            <div class="order_top"><div><fmt:formatDate value="${orderList[0].order_date}" pattern="yyyy.MM.dd"/> 주문</div><div></div></div>
                            <form method="post">
                            <div class="order_contents">
                                <div class="order_img">
                                    <c:forEach var="order" items="${orderList}">
                                        <img src="<c:url value="/resources/img/upload/${order.product.product_id}/${order.product.image}"/>">
                                    </c:forEach>
                                </div>
                                <input type="hidden" name="order_no" value="${orderList[0].order_no}">
                                <div class="order_status">
                                    <c:forEach var="order" items="${orderList}">
                                        <input type="hidden" name="product_id" value="${order.product_id}">
                                        <input type="hidden" name="size" value="${order.size}">
                                        <input type="hidden" name="quantity" value="${order.quantity}">
                                        <div>${order.status}</div>
                                        <div><a href="<c:url value="/product"/>?product_id=${order.product_id}" target="_blank">${order.product.product_name} / ${order.product.color} / ${order.size}</a></div>
                                        <div><fmt:formatNumber type="number" maxFractionDigits="0" value="${order.product.total}"/>원 / ${order.quantity}개</div>
                                    </c:forEach>
                                </div>
                                <div class="order_button">
                                        <div><button type="submit" formaction="<c:url value="/repurchase"/>">재구매</button></div>
                                        <div><button type="submit" formaction="<c:url value="/mypage/return/step1"/>?page=${param.page}">교환, 반품신청</button></div>
                                        <div><button type="button">리뷰작성</button></div>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                    <div class="subheading">받는사람 정보</div>
                    <div class="receiver">
                        <table>
                            <tr>
                                <td>받는사람</td>
                                <td>${orderList[0].name}</td>
                            </tr>
                            <tr>
                                <td>연락처</td>
                                <td>${fn:substring(orderList[0].phone,0,3)}-${fn:substring(orderList[0].phone,3,7)}-${fn:substring(orderList[0].phone,7,11)}</td>
                            </tr>
                            <tr>
                                <td>받는주소</td>
                                <td>${orderList[0].address}</td>
                            </tr>
                            <tr>
                                <td>배송요청사항</td>
                                <td>${orderList[0].del_request}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="subheading">결제정보</div>
                    <div class="payment">
                        <div>
                            <div>
                                <div>결제수단</div>
                                <div>OO카드 / 일시불</div>
                            </div>
                            <div>
                                <div><div>총 상품가격</div><div><fmt:formatNumber type="number" maxFractionDigits="0" value="${total}"/>원</div></div>
                                <div><div>배송비</div><div>3,000원</div></div>
                            </div>
                        </div>
                        <div>
                            <div></div>
                            <div>
                                <div><div>총 결제금액</div><div><fmt:formatNumber type="number" maxFractionDigits="0" value="${total + 3000}"/>원</div></div>
                            </div>
                        </div>
                    </div>
                    <div class="order_detail_button">
                        <button type="button" onclick="location.href = '<c:url value="/mypage/order/list"/>?page=${page}'">주문목록 돌아가기</button>
                        <form action="<c:url value="/mypage/order/del"/>?page=${page}" method="post"><button type="submit" onclick="return confirm('함께 결제된 주문상품은 전체 삭제되며, 복구할 수 없습니다. 주문내역을 삭제하시겠습니까?')">주문내역 삭제</button><input type="hidden" name="order_no" value="${param.order_no}"> </form>
                    </div>
                    <div class="guide">
                        <div>배송상품 주문상태 안내</div>
                        <div>
                            <div>
                                <div>결제완료</div>
                                <div>주문,결제,확인이 완료되었습니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>상품준비중</div>
                                <div>판매자가 발송할 상품을 준비중입니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>배송시작</div>
                                <div>상품준비가 완료되어 곧 배송될 예정입니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>배송중</div>
                                <div>상품이 고객님께 배송중입니다.</div>
                            </div>
                            <div><i class="fa-solid fa-angle-right"></i></div>
                            <div>
                                <div>배송완료</div>
                                <div>상품이 주문자에게 전달완료되었습니다.</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>