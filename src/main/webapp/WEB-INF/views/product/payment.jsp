<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
        <script type="text/javascript" src="<c:url value="/resources/js/address.js"/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/order.js'/>" defer></script>
        <link rel="stylesheet" href="<c:url value='/resources/css/payment.css'/>">
        <title>J&H 상품결제</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <form action="<c:url value="/buy"/>" method="post" id="buy">
                <div class="payment">
                    <div class="title">주문 / 결제</div>
                    <div class="subheading">구매자 정보</div>
                    <table>
                        <tr>
                            <td>이름</td>
                            <td>${user.user_name}</td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <td>휴대폰 번호</td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:length(user.phone) == 11}">
                                        ${fn:substring(user.phone,0,3)}-${fn:substring(user.phone,3,7)}-${fn:substring(user.phone,7,11)}
                                    </c:when>
                                    <c:otherwise>
                                        ${fn:substring(user.phone,0,3)}-${fn:substring(user.phone,3,6)}-${fn:substring(user.phone,6,10)}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </table>
                    <div class="subheading" style="margin-top:25px;">받는사람 정보</div>
                    <table>
                        <tr>
                            <td>이름</td>
                            <td><input name="name" type="text" value="${empty order.name ? user.user_name : order.name}"></td>
                        </tr>
                        <tr>
                            <td>배송주소</td>
                            <td>
                                <input name="address" class="address" type="text" value="${empty order.address ? user.address :order.address}" readonly><button class="find_address" type="button">주소변경</button>
                                <br><div style="display: none; margin-top: 10px;"><input style="width:50%;" name="address2" class="address_detail" type="text" placeholder="상세주소"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>연락처</td>
                            <td><input name="phone" type="text" value="${empty order.phone ? user.phone : order.phone}" placeholder="- 구문없이 입력해주세요"></td>
                        </tr>
                        <tr>
                            <td>배송 요청사항</td>
                            <td>
                                <select name="del_request">
                                    <option value="" style="display: none;" ${order.del_requset == "" ? "selected" : ""} ${empty order ? "selected": ""}}>배송요청사항 선택</option>
                                    <option value="문앞에 두고가주세요" ${order.del_requset == "문앞에 두고가주세요" ? "selected" : ""}>문앞에 두고가주세요</option>
                                    <option value="경비실에 맡겨주세요" ${order.del_requset == "경비실에 맡겨주세요" ? "selected" : ""}>경비실에 맡겨주세요</option>
                                    <option value="배송시 연락주세요" ${order.del_requset == "배송시 연락주세요" ? "selected" : ""}>배송시 연락주세요</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                    <div class="subheading" style="margin-top:25px;">구매목록</div>
                    <div class="product">
                        <div class="product_each">
                            <c:forEach items="${list}" var="product">
                                <input type="hidden" name="product_id" value="${product.product_id}"><input type="hidden" name="size" value="${product.size}"><input type="hidden" name="quantity" value="${product.quantity}">
                                <div><span><a href="product?product_id=${product.product_id}" target="_blank">${product.product_name}</a></span>/<span>${product.color}</span>/<span>${product.size}</span>/<span>${product.quantity}개</span>/<span><fmt:formatNumber type="number" maxFractionDigits="0" value="${product.total}"/>₩</span></div>
                            </c:forEach>
<%--                        <div><span>옥스포드 셔츠</span>/<span>Blue</span>/<span>XL</span>/<span>1개</span></div>--%>
<%--                        <div><span>라이더 자켓</span>/<span>Black</span>/<span>L</span>/<span>1개</span></div>--%>
<%--                        <div><span>와이드 팬츠</span>/<span>Pink</span>/<span>XL</span>/<span>1개</span></div>--%>
<%--                        <div><span>옥스포드 셔츠</span>/<span>Black</span>/<span>XL</span>/<span>1개</span></div>--%>
                        </div>
                    </div>
                    <table style="margin-top:85px;">
                        <tr>
                            <td>총 상품가격</td>
                            <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${total}"/>₩</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td>3,000₩
                            </td>
                        </tr>
                        <tr>
                            <td>총결제금액</td>
                            <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${total+3000}"/>₩</td>
                        </tr>
                        <!-- <tr>
                            <td>결제방법</td>
                            <td class="payment_method">
                                <div>
                                    <div>
                                        <input name="method" type="radio" id="account"><label for="account">계좌이체</label>
                                    </div>
                                    <div>
                                        <input name="method" type="radio" id="card"><label for="card">신용/체크카드</label>
                                    </div>
                                    <div>
                                        <input name="method" type="radio" id="phone"><label for="phone">휴대폰</label>
                                    </div>
                                    <div>
                                        <input name="method" type="radio" id="bank"><label for="bank">무통장입금(가상계좌)</label>
                                    </div>
                                </div>
                                <div>
                                    <div class="account">
                                        <div>은행선택</div>
                                        <div>
                                            <select name="bank" >
                                                <option value="" selected style="display:none;">선택</option>
                                                <option value="카카오뱅크">카카오뱅크</option>
                                                <option value="기업은형">기업은행</option>
                                                <option value="국민은형">국민은행</option>
                                                <option value="농협은형">농협은행</option>
                                                <option value="하나은형">하나은행</option>
                                                <option value="신한은형">신한은행</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="card" style="flex-wrap:wrap;">
                                        <div>카드선택</div>
                                        <div style="width: 90%;">
                                            <select name="card" >
                                                <option value="" selected style="display:none;">선택</option>
                                                <option value="카카오뱅크">카카오뱅크카드</option>
                                                <option value="기업은형">BC카드</option>
                                                <option value="국민은형">국민카드</option>
                                                <option value="농협은형">농협카드</option>
                                                <option value="하나은형">하나카드</option>
                                                <option value="신한은형">신한카드</option>
                                            </select>
                                        </div>
                                        <div style="width: 100%;"><hr></div>
                                        <div>
                                            할부기간
                                        </div>
                                        <div>
                                            <select name="installment">
                                                <option value="일시불" selected>일시불</option>
                                                <option value="1개월">1개월</option>
                                                <option value="2개월">2개월</option>
                                                <option value="3개월">3개월</option>
                                                <option value="4개월">4개월</option>
                                                <option value="5개월">5개월</option>
                                                <option value="6개월">6개월</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="phone">
                                        <div>통신사종류</div>
                                        <div>
                                            <select name="agency" >
                                                <option value="" selected style="display:none;">선택</option>
                                                <option value="SKT">SKT</option>
                                                <option value="KT">KT</option>
                                                <option value="헬로모바일">헬로모바일</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="transfer">
                                        <div>입금은행</div>
                                        <div>
                                            <select name="bank" >
                                                <option value="" selected style="display:none;">선택</option>
                                                <option value="카카오뱅크">카카오뱅크</option>
                                                <option value="기업은형">기업은행</option>
                                                <option value="국민은형">국민은행</option>
                                                <option value="농협은형">농협은행</option>
                                                <option value="하나은형">하나은행</option>
                                                <option value="신한은형">신한은행</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr> -->
                    </table>
                    <div class="payment_btn">
                        <button type="button" id="submit_btn" >결제하기</button><button type="button" onclick="window.history.go(-1);">취소</button>
                    </div>
                </div>
            </form>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>