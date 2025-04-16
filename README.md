🛍️ JnH Spring - 의류 쇼핑몰 프로젝트<br><br>
AWS EC2로 배포중입니다<br>
https://jnh.kro.kr ( PC 브라우저 환경에 최적화되어 있습니다. )<br><br>
ID/PWD<br>
관리자 asdf/1234<br>
사용자 asd123/asd123<br>
<br>
프론트제작Git(와이어프레임,플로우차트 등)  : https://github.com/Tesalriah/JNH

<b>프로젝트 개요</b><br>
JnH Spring은 Spring Framework 기반의 의류 쇼핑몰 프로젝트입니다.<br>
사용자는 상품을 검색, 주문, 결제, 리뷰 작성 등의 기능을 이용할 수 있으며<br>
관리자는 상품 및 주문 등을 관리할 수 있습니다.<br>
<br>
개발 목표:<br>
실제 서비스 환경을 고려한 쇼핑몰 구현<br>
Ajax 기반으로 빠른 사용자 경험(UX) 제공<br>
보안 및 데이터 최적화 적용 (비밀번호 해싱, 페이징 처리 등)<br>
<br>주요 기능<br><br>
사용자 기능<br>
🔍 상품 목록 및 상세 페이지 조회<br>
🛒 장바구니 담기 및 주문 결제<br>
✍️ 상품문의 및 리뷰 작성<br>
🔑 회원가입 및 로그인 (Spring Security 적용)<br>
<br>
관리자 기능<br>
📦 상품 관리 (등록/수정/삭제)<br>
📊 주문 관리 (상태 변경, 처리 내역 확인)<br>
<br>
구분	기술<br>
Back-End : Java 11, Spring Framework, MyBatis<br>
Front-End : HTML, CSS, JavaScript, jQuery, Ajax<br>
Database : MySQL 8.0<br>
Security : Spring Security (BCryptPasswordEncoder)<br>
Build Tool : Maven<br>
Server : Apache Tomcat 9.0, AWS EC2 (Ubuntu)
