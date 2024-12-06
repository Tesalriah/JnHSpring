<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="kr">
    <head>
        <%@ include file="../head.jsp" %>
        <script type="text/javascript" src="<c:url value='/resources/js/price-cal.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/review-question.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/size-insert.js'/>" defer></script>
        <script type="text/javascript" src="<c:url value='/resources/js/scroll-move.js'/>" defer></script>
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
                    <img src="<c:url value="/resources/img/upload/${product.product_id}/${product.image}"/>">
                </div>
                <div class="info" style="width:45%;">
                    <form action="" method="post">
                        <input type="hidden" name="product_id" value="${product.product_id}">
                        <div class="name">${product.product_name}<span class="wish_btn"><i id="heart" ${wish ? "class='fa-solid fa-heart'" : "class='fa-regular fa-heart'"}></i><span id="wish_cnt">${product.wish_cnt > 999 ? "999+" : product.wish_cnt}</span></span></div>
                        <div class="price">
                            <input name="price" type="hidden" value="${product.dis_price}">
                            <c:if test="${product.discount != 0}">
                                <fmt:formatNumber type="number" maxFractionDigits="0" value="${product.dis_price}"/>₩
                                <del><fmt:formatNumber type="number" maxFractionDigits="0" value="${product.price}"/>₩</del>
                            </c:if>
                            <c:if test="${product.discount == 0}">
                                {product.price}
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
                                    <input name="user_id" type="hidden" value="">
                                    <input name="report_id" type="hidden" value="">
                                    <div style="font-weight:bold;">신고사유</div>
                                    <select name="reason" style="width: 100%;">
                                        <option value="" style="display:none;" selected>사유를 선택하세요.</option>
                                        <option value="부적절한 닉네임">부적절한 닉네임</option>
                                        <option value="욕설">욕설</option>
                                        <option value="부적절한 이미지 게시">부적절한 이미지</option>
                                    </select>
                                </div>
                                <div style="margin:10px 0;">
                                    <div style="font-weight:bold;">내용</div>
                                    <textarea name="report_contents" placeholder="상세내용을 작성해주세요."></textarea>
                                </div>
                                <div class="report_button">
                                    <button type="submit">신고</button>
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
                        <img id="enlarge_img" src="img/best.jpg">
                    </div>
                </div>
                <div class="reviews_title">
                    <div>Reviews</div>
                    <div>
                        <button type="button">Write</button>
                    </div>
                </div>
                <div class="reviews_contents">
                    <div class="reviews_each">
                        <div class="review_top">
                            <div class="id">asd123<input name='review_id' type="hidden" value="asd123"></div>
                            <div class="reviews_tools"><div class="edit_del" style="font-size: 14px;"><a href="#">수정</a>|<a href="#">삭제</a></div><button style="display:none;" class="report_open_btn" type="button">신고하기</button></div>
                        </div>
                        <div class="review_middle" style="display:flex; align-items: center;">
                            <div class="rating">
                                <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                            </div>
                            <div class="reviews_date">2024/01/01</div>
                        </div>
                        <div class="review_bottom">
                            <div>
                                <div class="review_img"><img class="each_img" src="img/best.jpg"></div>
                                리뷰내용
                            </div>
                        </div>
                    </div>
                    <div class="reviews_each">
                        <div class="review_top"><div class="id">zxc123<input name='review_id' type="hidden" value="zxc123"></div><div class="reviews_tools"><button class="report_open_btn" type="button">신고하기</button></div></div>
                        <div class="review_middle" style="display:flex; align-items: center;">
                            <div class="rating">
                                <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                            </div>
                            <div class="reviews_date">2024/01/01</div>
                        </div>
                        <div class="review_bottom">
                            <div>
                                <div class="review_img"><img class="each_img" src="img/weekly.jpg"></div>
                                리뷰내용
                            </div>
                        </div>
                    </div>
                    <div class="reviews_each">
                        <div class="review_top"><div class="id">qeqe111<input name='review_id' type="hidden" value="qeqe111"></div><div class="reviews_tools"><button class="report_open_btn" type="button">신고하기</button></div></div>
                        <div class="review_middle" style="display:flex; align-items: center;">
                            <div class="rating">
                                <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                            </div>
                            <div class="reviews_date">2024/01/01</div>
                        </div>
                        <div class="review_bottom">
                            <div>
                                <div class="review_img"><img class="each_img" src="img/main1.jpg"></div>
                                리뷰내용
                            </div>
                        </div>
                    </div>
                    <div class="reviews_each">
                        <div class="review_top"><div class="id">asd123<input name='review_id' type="hidden" value="asd123"></div><div class="reviews_tools"><button class="report_open_btn" type="button">신고하기</button></div></div>
                        <div class="review_middle" style="display:flex; align-items: center;">
                            <div class="rating">
                                <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                            </div>
                            <div class="reviews_date">2024/01/01</div>
                        </div>
                        <div class="review_bottom">
                            <div>
                                리뷰내용
                            </div>
                        </div>
                    </div>
                    <div class="reviews_each">
                        <div class="review_top"><div class="id">qeqe111<input name='review_id' type="hidden" value="qeqe111"></div><div class="reviews_tools"><button class="report_open_btn" type="button">신고하기</button></div></div>
                        <div class="review_middle" style="display:flex; align-items: center;">
                            <div class="rating">
                                <i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i>
                                <div class="star"><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i><i class="fa-solid fa-star"></i></div>
                            </div>
                            <div class="reviews_date">2024/01/01</div>
                        </div>
                        <div class="review_bottom">
                            <div>
                                <div class="review_img"><img class="each_img" src="img/men.jpg"></div>
                                리뷰내용
                            </div>
                        </div>
                    </div>
                </div>
                <div class="review_paging">
                    <a href=""><i class="fa-solid fa-angle-left"></i></a>
                    <a href="">1</a>
                    <a href="">2</a>
                    <a href="">3</a>
                    <a href="">4</a>
                    <a href="">5</a>
                    <a href=""><i class="fa-solid fa-angle-right"></i></a>
                </div>
            </div>
            <!-- 문의 -->
            <div id="questions">
                <!-- 상품문의 모달 -->
                <div class="question_bg" style="display:none;"></div>
                <div class="add_question" style="display:none;">
                    <div class="question_modal">
                        <div class="question_title"><div>상품문의</div><div><button id="question_x"><i class="fa-solid fa-xmark"></i></button></div></div>
                        <div class="question_contents">
                            <form action="" method="post">
                                <input type="hidden" name="qeustion_id" value="">
                                <div class="question_product_name">
                                    <div style="font-weight:bold;">상품명<span style="font-weight:100; margin-left:5px;">상품명</span></div>
                                </div>
                                <div style="margin:10px 0;">
                                    <div style="font-weight:bold;">내용</div>
                                    <textarea name="question_contents" placeholder="문의사항을 작성해주세요."></textarea>
                                </div>
                                <div class="question_button">
                                    <button type="submit">문의하기</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="questions_title">
                    <div>Questions</div>
                    <div>
                        <button id="open_question" type="button">Write</button>
                    </div>
                </div>
                <div class="questions_contents">
                    <div class="questions_each">
                        <div class="question_top">
                            <span class="question_span">질문</span>asd123<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            질문내용
                        </div>
                    </div>
                    <div class="answer_each">
                        <div class="question_top">
                            <span class="answer_span">답변</span>판매자<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            답변내용
                        </div>
                    </div>
                    <div class="questions_each">
                        <div class="question_top">
                            <span class="question_span">질문</span>asd123<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            질문내용
                        </div>
                    </div>
                    <div class="answer_each">
                        <div class="question_top">
                            <span class="answer_span">답변</span>판매자<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            답변내용
                        </div>
                    </div>
                    <div class="questions_each">
                        <div class="question_top">
                            <span class="question_span">질문</span>asd123<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            질문내용
                        </div>
                    </div>
                    <div class="answer_each">
                        <div class="question_top">
                            <span class="answer_span">답변</span>판매자<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            답변내용
                        </div>
                    </div>
                    <div class="questions_each">
                        <div class="question_top">
                            <span class="question_span">질문</span>asd123<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            질문내용
                        </div>
                    </div>
                    <div class="answer_each">
                        <div class="question_top">
                            <span class="answer_span">답변</span>판매자<div style="float: right; font-weight:100;">2024-01-01</div>
                        </div>
                        <div class="question_bottom">
                            답변내용
                        </div>
                    </div>
                </div>
                <div class="question_paging">
                    <a href=""><i class="fa-solid fa-angle-left"></i></a>
                    <a href="">1</a>
                    <a href="">2</a>
                    <a href="">3</a>
                    <a href="">4</a>
                    <a href="">5</a>
                    <a href=""><i class="fa-solid fa-angle-right"></i></a>
                </div>
            </div>
        </div>
        </div>
    </main>
    <%@ include file="../footer.jsp" %>
</body>
</html>