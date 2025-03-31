<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <script type="text/javascript" src="<c:url value='/resources/js/price-cal.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/review-question.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/size-insert.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/scroll-move.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/question.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/review.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/report.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/add-wish.js'/>" defer></script>
        <link rel="stylesheet" href="<c:url value='/resources/css/product-info.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/review-question.css'/>">
        <title>J&H</title>
    </head>
    <body>
    <%@ include file="../header.jsp" %>
    <main>
        <div class="container">
            <div class="move">
                <div>
                    <a href="<c:url value="/"/>">HOME</a>
                    <c:if test="${!sc.gender.equals('')}">
                        > <a href="<c:url value="/product-list?gender=${sc.gender}"/>">${sc.gender}</a>
                    </c:if>
                    <c:choose>
                        <c:when test="${!sc.category.equals('')}">
                            > <a href="<c:url value="/product-list?gender=${sc.gender}&category=${sc.category}"/>">${sc.category}</a>
                        </c:when>
                        <c:otherwise>
                            > <a href="<c:url value="/product-list?gender=${sc.gender}"/>">ALL</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <!-- 상품정보 -->
            <div class="product_info">
                <div class="product_img">
                    <img class="product_img_src" src="<c:url value="/resources/img/upload/product-img/${product.product_id}/${product.image}"/>">
                </div>
                <div class="info" style="width:45%;">
                    <form action="" method="post">
                        <input type="hidden" name="product_id" value="${product.product_id}">
                        <div class="name">${product.product_name}<span class="wish_rating"><span class="wish_btn"><i id="heart" ${wish ? "class='fa-solid fa-heart'" : "class='fa-regular fa-heart'"}></i><span id="wish_cnt">&nbsp;<fmt:formatNumber type="number" maxFractionDigits="0" value="${product.wish_cnt}"/></span></span><span><i class="fa-solid fa-star"></i>&nbsp;${product.rating}</span></span></div>
                        <div class="price">
                            <input name="price" type="hidden" value="${product.dis_price}">
                            <c:if test="${product.discount != 0}">
                                <fmt:formatNumber type="number" maxFractionDigits="0" value="${product.dis_price}"/>₩
                                <del><fmt:formatNumber type="number" maxFractionDigits="0" value="${product.price}"/>₩</del>
                            </c:if>
                            <c:if test="${product.discount == 0}">
                                <fmt:formatNumber type="number" maxFractionDigits="0" value="${product.price}"/>₩
                            </c:if>
                        </div>
                        <div class="delivery_fee"><div>Delivery Fee</div><div>3,000₩</div></div>
                        <div class="color">
                            <div>Color</div>
                            <select name="color"><option value="${product.color}" selected>${product.color}</option></select></div>
                        <div class="size">
                            <div>Size</div>
                            <div>
                                <c:forEach var="size" items="${sizeList}">
                                    <button class="size_btn" type="button">${size}</button>
                                </c:forEach>
                                <input name="size" type="hidden" value="">
                            </div>
                        </div>
                        <div class="quantity">
                            <div>Quantity</div>
                            <div>
                                <button type="button" class="quantity_minus"><i class="fa-solid fa-minus"></i></button>
                                <input type="text" name="quantity" value="1">
                                <button type="button" class="quantity_plus"><i class="fa-solid fa-plus"></i></button>
                            </div>
                        </div>
                        <div class="total">
                            <div>Total</div>
                            <div>
                                <input type="hidden" name="total" value="">
                                <span id="total"></span>₩
                            </div>
                        </div>
                        <div class="product_button">
                            <input type="submit" formaction="<c:url value="/add-cart${sc.queryString}"/>" value="Add Cart">
                            <input type="submit" formaction="<c:url value="/product${sc.queryString}&product_id=${product.product_id}"/>" value="Buy">
                        </div>
                    </form>
                </div>
            </div>
            <div class="toggle_move">
                <div>
                    <div id="move_reviews">Reviews</div>
                    <div id="move_questions">Questions</div>
                </div>
            </div>
            <div class="move_top"><i class="fa-solid fa-arrow-up"></i></div>
            <!-- 리뷰 -->
            <div id="reviews">
                <!-- 리뷰신고 모달 -->
                <div class="review_bg" style="display:none;"></div>
                <div class="review_report" style="display:none;">
                    <div class="report_modal">
                        <div class="report_title"><div>리뷰신고</div><div><button id="review_x"><i class="fa-solid fa-xmark"></i></button></div></div>
                        <div class="report_contents">
                            <form action="" method="post">
                                <div>
                                    <input name="report_id" type="hidden" value="">
                                    <input name="rno" type="hidden" value="">
                                    <div style="font-weight:bold;">신고사유</div>
                                    <select name="reason" style="width: 100%;">
                                        <option value="" style="display:none;" selected>사유를 선택하세요.</option>
                                        <option value="1">상품과 관련없는 내용</option>
                                        <option value="2">욕설</option>
                                        <option value="3">부적절한 이미지 게시</option>
                                        <option value="4">기타</option>
                                    </select>
                                </div>
                                <div style="margin:10px 0;">
                                    <div style="font-weight:bold;">내용</div>
                                    <textarea name="report_contents" placeholder="상세내용을 작성해주세요."></textarea>
                                </div>
                                <div class="report_button">
                                    <button type="button" id="report_btn">신고</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- 리뷰 이미지 모달 -->
                <div class="reviews_bg" style="display:none;"></div>
                <div id="reviews_img" style="display:none;">
                    <button id="review_img_x" type="button"><i class="fa-solid fa-xmark"></i></button>
                    <div class="img_modal">
                        <img id="enlarge_img" src="">
                    </div>
                </div>
                <div class="reviews_title">
                    <div>Reviews</div>
                    <div style="font-size: 15px;font-weight: normal;">
                        <button type="button" id="rating" style="font-weight:bold;">평점순</button>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;<button type="button" id="reg_date">최신순</button>
                    </div>
                </div>
                <div class="reviews_contents"></div>
                <div class="review_paging"></div>
            </div>
            <!-- 문의 -->
            <div id="questions">
                <!-- 상품문의 모달 -->
                <div class="question_bg" style="display:none;"></div>
                <div class="add_question" style="display:none;">
                    <div class="question_modal">
                        <div class="question_title"><div>상품문의</div><div><button id="question_x"><i class="fa-solid fa-xmark"></i></button></div></div>
                        <div class="question_contents">
                            <input type="hidden" name="qeustion_id" value="">
                            <div class="question_product_name">
                                <div style="font-weight:bold;">상품명<span style="font-weight:100; margin-left:5px;">${product.product_name}</span></div>
                            </div>
                            <div style="margin:10px 0;">
                                <div style="font-weight:bold;">내용</div>
                                <textarea name="question_contents" placeholder="문의사항을 작성해주세요."></textarea>
                            </div>
                            <div class="question_button">
                                <button type="button" id="write">문의하기</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="questions_title">
                    <div>Questions</div>
                    <div>
                        <button id="open_question" type="button">Write</button>
                    </div>
                </div>
                <div class="questions_contents"></div>
                <div class="question_paging"></div>
            </div>
        </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>