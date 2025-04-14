package kr.co.jnh.controller;

import kr.co.jnh.domain.AskingDto;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.AskingService;
import kr.co.jnh.service.UserService;
import kr.co.jnh.util.FileMultiSaveUtil;
import kr.co.jnh.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import kr.co.jnh.domain.*;
import kr.co.jnh.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    ReportReviewService reportReviewService;

    @Autowired
    AskingService askingService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReturnsService returnsService;

    @Autowired
    QuestionService questionService;
    // 상품리스트
    @GetMapping("product-mng")
    public String productMng(SearchCondition sc, Model m){
        sc.setPageSize(20); // 한페이지에 가져오는 상품갯수 20개

        // option 기본값 product_id로 설정 (최신순 )
        if(sc.getOption() == null || sc.getOption().equals("")){
            sc.setOption("product_id");
        }

        try {
            // SearchCondition 정보에 따른 총 상품갯수 가져오기
            int totalCnt = productService.getProductAdminCnt(sc);
            m.addAttribute("totalCnt", totalCnt);
            // SearchCondition과 총 상품갯수를 PageHandler에 할당하여 페이징처리 계산
            PageHandler ph = new PageHandler(totalCnt, sc);

            // SearchCondition 정보에 따른 상품 list에 저장(20개)
            List<Product> list = productService.getProductAdmin(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/product-mng";
    }

    // 상품 추가 페이지 이동 (인터셉터로 관리자 확인)
    @GetMapping("product-add")
    public String getAddProduct(){
        return "admin/product-add";
    }

    // 상품 추가
    @PostMapping("/product-add")
    public String addProduct(Product product, HttpServletRequest request, @RequestParam("uploadFile")MultipartFile file, Model m){
        HttpSession session = request.getSession();
        // 메서드를 이용하여 검증
        String msg = productValidation(product, file);
        int result = -1;
        // 사이즈, 갯수가 여러개일 경우 각각의 배열에 저장
        String[] sizeArr = {};
        String[] stockArr = {};
        if(product.getSize() != null){
            sizeArr = product.getSize().split(",");
            stockArr = product.getStock().split(",");
        }
        // 실패시 가지고있던 정보를 넘겨주는 list 생성
        List<Product> list = new ArrayList<>();
        if(product.getSize() != null || product.getStock() != null){
            for (int i = 0; i < sizeArr.length; i++) {
                product.setSize(sizeArr[i]);
                product.setStock(stockArr[i]);
                list.add(product);
            }
        }
        // size와 stock의 값이 여러개가 아닐경우 list에 product의 정보 추가
        if(list.isEmpty()){
            product.setStock("");
            product.setSize("");
            list.add(product);
        }
        // 검증에 실패했을 경우 작성했던 값과함께 원래 페이지로 리턴
        if(!msg.isBlank()){
            session.setAttribute("msg", msg);
            m.addAttribute("list",list);
            return "admin/product-add";
        }
        // 상품 id를 생성하기위해 현재 날짜 가져오기
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        long product_id;

        try {
            // 현재 날짜에 생성한 상품이 없으면 현재날짜001로 생성, 상품이 있으면 마지막 product_id에 +1한 product_id값 생성 일일 최대 999개 생성
            if(productService.productIdCheck(today)){
                String product_id_str = productService.returnId(today);
                product_id = Long.parseLong(product_id_str);
                product_id += 1;
                if(product_id + "" == today + "999"){
                    throw new Exception("PRODUCT_LIMITED");
                }
            }else{
                product_id = Long.parseLong(today + "001");
            }
            product.setProduct_id(product_id + "");
            product.setState("판매");

            // 이미지를 경로에 저장하고 생성하여 저장된 파일이름을 반환하는 메서드
            String fileName = FileMultiSaveUtil.uploadImg(file, request, "product-img", product_id + "");

            // 바꾼 이미지이름+확장자를 product.image에 set
            product.setImage(fileName);

            // 사이즈가 하나가 아닐때 모든 정보 데이터베이스에 추가
            if(sizeArr.length > 1 || stockArr.length > 1){
                for(int i=0; i<sizeArr.length; i++){
                    product.setSize(sizeArr[i]);
                    product.setStock(stockArr[i]);
                    result = productService.addProduct(product);
                }
            }else{ // 사이즈가 하나일때
                result = productService.addProduct(product);
            }
            if(result != 1){
                throw new Exception("ADD_FAIL");
            }
            session.setAttribute("msg", "상품을 등록하였습니다.");
            return "redirect:/product-list";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "상품등록에 실패했습니다.");
            return "admin/product-add";
        }
    }

    // 상품수정 정보가져오기
    @GetMapping("product-modify")
    public String productModify(@RequestParam String product_id, SearchCondition sc, Model m){
        String[] sizeFrame = {"XS", "S", "M", "L", "XL", "XXL", "XXXL"}; // 사이즈 순으로 정렬하기 위해 선언

        try {
            List<Product> list = productService.readOne(product_id);

            // 사이즈순으로 정렬하여 productList에 담기
            List<Product> productList = new ArrayList<>();
            for (String size : sizeFrame) {
                for (Product product : list) {
                    if(size.equals(product.getSize())){
                        productList.add(product);
                    }
                }
            }

            m.addAttribute("sc", sc);
            m.addAttribute("list", productList);
            m.addAttribute("modify", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "admin/product-add";
    }

    // 상품수정
    @PostMapping("product-modify")
    public String productModifyPost(Product product, HttpServletRequest request, @RequestParam("uploadFile") MultipartFile file, SearchCondition sc){
        HttpSession session = request.getSession();
        product.setUp_date(new Date());
        // 이미지를 변경하지않으면 원래이미지 파일명을 넘겨받기
        if(request.getParameter("not_change") != null){
            product.setImage(request.getParameter("not_change"));
        }
        String[] sizeArr = product.getSize().split(",");
        String[] stockArr = product.getStock().split(",");

        try {
            // 원래의 이미지파일명이 넘어오지 않았을시 새 이미지 저장후 파일명을 db에 저장
            if(product.getImage() == null){
                // 이미지를 경로에 저장하고 생성하여 저장된 파일이름을 반환하는 메서드
                String fileName = FileMultiSaveUtil.uploadImg(file, request, "product-img", product.getProduct_id() + "");
                product.setImage(fileName);
            }
            for (int i = 0; i < stockArr.length; i++) {
                product.setStock(stockArr[i]);
                product.setSize(sizeArr[i]);

                if(productService.modifyInfo(product) != 1){
                    throw new Exception("PRODUCT_MODIFY_FAIL");
                }
            }
            session.setAttribute("msg", "수정되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "수정에 실패하였습니다. 다시 시도해주세요.");
        }
        return "redirect:/adming/product-mng"+ sc.getQueryString();
    }

    // 상품삭제
    @GetMapping("product-remove")
    public String productRemove(@RequestParam String product_id, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user == null){
            session.setAttribute("msg", "잘못된 접근입니다.");
            return "redirect:/";
        }
        try {
            if(user != null && user.getGrade() != 0){
                throw new Exception("WRONG_APPROACH");
            }
            if(productService.remove(product_id) <= 0){
                throw new Exception("PRODUCT_REMOVE_FAIL");
            }
            session.setAttribute("msg", "상품이 삭제되었습니다.");
            return "redirect:/product-list";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "삭제에 실패했습니다.");
            return "redirect:/product?product_id=" + product_id;
        }
    }

    // 상품정보 Ajax로 업데이트
    @PostMapping("update-product")
    @ResponseBody
    public Map<String, Object> updateProduct(@RequestBody Map<String, Object> map){
        // 상품을 구별하는 product_id와 size는 map에 이미 할당되어있으므로 type에 따른 동적값을 map에 저장
        String dynamic_value = (String)map.get("dynamic_value");
        String type = (String)map.get("type");
        map.put(type, dynamic_value);

        if(!type.equals("stock")){ // stock(재고)이 아닐경우 특정사이즈가 아닌 해당 product_id의 모든 값을 변경
            map.remove("size");
        }

        try {
            if(productService.updateProduct(map) <= 0){
                throw new Exception("PRODUCT_UPDATE_FAIL");
            }
            map.put("msg", "값이 변경되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "변경에 실패했습니다.");
        }

        return map;
    }

    // 유저 리스트
    @GetMapping("user-mng")
    public String userMng(SearchCondition sc, Model m){
        sc.setPageSize(20); // 한페이지에 가져오는 유저정보 20개

        try {
            int totalCnt = userService.getSearchUserCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);

            List<User> list = userService.getSearchUser(sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/user-mng";
    }

    // 유저 상태 변경
    @PostMapping("change-status")
    @ResponseBody
    public Map<String, Object> changeStatus(@RequestBody Map<String,Object> map){
        String id = (String)map.get("user_id");
        int status = (int)map.get("status");

        try {
            if(userService.changeStatus(id, status) != 1){
                throw new Exception("STATUS_CHANGE_FAIL");
            }
            map.put("msg", "상태를 변경하였습니다.");
            map.put("result", "success");
        } catch (Exception e){
            e.printStackTrace();
            map.put("msg", "상태 변경에 실패했습니다.");
        }

        return map;
    }

    // 리뷰 신고 리스트
    @GetMapping("report-mng")
    public String reportMng(SearchCondition sc, Model m){
        try {
            int totalPage = reportReviewService.readPageCnt(sc);
            PageHandler ph = new PageHandler(totalPage, sc);

            List<ReportReview> list = reportReviewService.readPage(sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/report-mng";
    }

    // 리뷰 리스트
    @GetMapping("review-mng")
    public String reviewMng(SearchCondition sc, Model m){
        try {
            int totalCnt = reviewService.readPageByReviewCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);
            ph.setNaviSize(5);
            ph.doPaging(totalCnt, sc);
            List<Review> list =  reviewService.readPageByReview(sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/review-mng";
    }

    // 리뷰 삭제 처리
    @PostMapping("blind-review")
    @ResponseBody
    public Map<String, Object> blindReview(@RequestBody Map<String,Object> map){
        String user_id = (String)map.get("user_id");
        String rno = (String)map.get("rno");

        Review review = new Review();
        review.setUser_id(user_id);
        review.setRno(Integer.parseInt(rno));
        review.setWhether(2);

        try {
            Review pointReview = reviewService.readOne(Integer.parseInt(rno));
            if(pointReview.getWhether() == 2){
                map.put("msg", "이미 삭제되었습니다.");
                throw new Exception("ALREADY_BLINDED");
            }
            if(reviewService.modify(review) != 1){
                map.put("msg", "실패했습니다.");
                throw new Exception("BLIND_REVIEW_FAIL");
            }
            map.put("msg", "삭제처리 되었습니다.");
            map.put("result", "success");
        } catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }

    String[] category = {"주문완료", "배송중", "배송완료"};

    // 주문 리스트
    @GetMapping("order-mng")
    public String orderMng(SearchCondition sc, Model m){
        if(sc.getOption().equals("")){
            sc.setOption("order_no");
        }
        if(sc.getCategory().equals("")){
            sc.setCategory(category[0]);
        }
        try {
            // 페이징 처리
            sc.setGender("order_no의 중복제거를 위함");
            int totalCnt = orderService.readMngCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);
            sc.setGender(""); // 다시 초기화
            ph.setNaviSize(5);
            ph.doPaging(totalCnt, sc);

            List<Order> list =  orderService.readMng(sc);

            // 각 옵션의 count map에 저장
            Map<String,Integer> map = new HashMap<>();
            for(String categoryStr : category){
                SearchCondition sc2 = new SearchCondition();
                sc2.setCategory(categoryStr);
                int cnt = orderService.readMngCnt(sc2);
                map.put(categoryStr, cnt);
            }

            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
            m.addAttribute("cntMap", map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/order-mng";
    }

    // 주문 status 변경
    @PostMapping("order-status")
    public String orderStatus(@ModelAttribute OrderList orderList, @RequestParam(required = false) String[] check_each, SearchCondition sc, RedirectAttributes rattr, HttpSession session){
        rattr.addAttribute("category", sc.getCategory());
        if(!sc.getOption().equals("")){
            rattr.addAttribute("option", sc.getOption());
        }if(!sc.getKeyword().equals("")){
            rattr.addAttribute("keyword", sc.getKeyword());
        }
        if(check_each == null || check_each.length <= 0){
            session.setAttribute("msg", "상품을 선택해주세요.");
            return "redirect:/admin/order-mng";
        }
        // 페이지의 주문리스트를 모두받기
        List<Order> orders = orderList.getOrderList();
        List<Order> useOrder = new ArrayList<>();
        // 주문완료일시 배송중으로 배송중일시 배송완료
        int index = Arrays.asList(category).indexOf(sc.getCategory());
        if(index != 2){
            index++;
        }

        // checkBox에서 넘겨받은 index값의 주문만 userOrder에 저장
        for (String checkEach : check_each) {
            useOrder.add(orders.get(Integer.parseInt(checkEach)));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status", category[index]);

        try{
            for (Order order : useOrder) {
                map.put("order_no", order.getOrder_no());
                map.put("id", order.getUser_id());
                map.put("product_id", order.getProduct_id());
                map.put("size", order.getSize());

                if(orderService.statusModify(map) != 1){
                    throw new Exception("ORDER_STATUS_MODIFY_FAIL");
                }
            }
            session.setAttribute("msg", category[index] + "(으)로 처리되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("msg", "실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/admin/order-mng";
    }

    String[] r_category = {"대기중", "처리중", "완료"};

    // return 리스트
    @GetMapping("return-mng")
    public String returnMng(SearchCondition sc, Model m){
        if(sc.getCategory().equals("")){
            sc.setCategory(r_category[0]);
        }

        try{
            // 페이징 처리
            sc.setGender("return_id의 중복제거를 위함");
            int totalCnt = returnsService.readMngCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);
            sc.setGender(""); // 다시 초기화
            ph.setNaviSize(5);
            ph.doPaging(totalCnt, sc);

            // 각 옵션의 count map에 저장
            Map<String, Object> map = new HashMap<>();
            for (String categoryStr : r_category) {
                SearchCondition sc2 = new SearchCondition();
                sc2.setCategory(categoryStr);
                int cnt = returnsService.readMngCnt(sc2);
                map.put(categoryStr, cnt);
            }

            List<Returns> list = returnsService.readMng(sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", ph);
            m.addAttribute("cntMap", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/admin/return-mng";
    }

    // return 상태 변경
    @PostMapping("return-status")
    public String returnStatus(@ModelAttribute ReturnsList returnsList, @RequestParam(required = false) String[] check_each, SearchCondition sc, RedirectAttributes rattr, HttpSession session){
        rattr.addAttribute("category", sc.getCategory());
        if(!sc.getOption().equals("")){
            rattr.addAttribute("option", sc.getOption());
        }if(!sc.getKeyword().equals("")){
            rattr.addAttribute("keyword", sc.getKeyword());
        }
        if(check_each == null || check_each.length <= 0){
            session.setAttribute("msg", "상품을 선택해주세요.");
            return "redirect:/admin/return-mng";
        }
        // 페이지의 주문리스트를 모두받기
        List<Returns> returns = returnsList.getReturnsList();
        List<Returns> useReturns = new ArrayList<>();
        // 대기중일시 처리중으로 처리중일시 완료
        int index = Arrays.asList(r_category).indexOf(sc.getCategory());
        if(index != 2){
            index++;
        }

        // checkBox에서 넘겨받은 index값의 주문만 userReturns에 저장
        for (String checkEach : check_each) {
            useReturns.add(returns.get(Integer.parseInt(checkEach)));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status", r_category[index]);

        try{
            for (Returns setReturns : useReturns) {
                map.put("return_id", setReturns.getReturn_id());
                map.put("id", setReturns.getUser_id());
                map.put("product_id", setReturns.getProduct_id());
                map.put("size", setReturns.getSize());
                map.put("type", setReturns.getType());
                map.put("order_no", setReturns.getOrder_no());

                if(returnsService.mngModify(map) != 1){
                    throw new Exception("ORDER_STATUS_MODIFY_FAIL");
                }
            }

            session.setAttribute("msg", r_category[index] + "(으)로 처리되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            session.setAttribute("msg", "실패했습니다. 다시 시도해주세요.");
        }

        return "redirect:/admin/return-mng";
    }


    /* admin 문의관리*/
    // 문의목록 가져오기
    @GetMapping("/ask-mng")
    public String ask(Model m, SearchCondition sc){
        sc.setPageSize(15);

        try {
            // 전체게시글 갯수
            int total = askingService.getCount();

            // 전체 게시글 불러오기
            List< AskingDto> readAll = askingService.readAll(sc);
            m.addAttribute("readAll", readAll);

            // 전체 게시물 갯수를 통해 PageHandler를 이용한 페이징 처리
            PageHandler ph = new PageHandler(total,sc);
            m.addAttribute("ph",ph);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/ask-mng";
    }

    // 하나의 게시물 읽기
    @GetMapping("/ask-details")
    public String askDetail(Model m, SearchCondition sc, @RequestParam int no){
        try {
            // no를 통해 게시물 불러오기
            List<AskingDto> askingDto = askingService.read(no);
            m.addAttribute("askingDto", askingDto);
            m.addAttribute("sc", sc);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/ask-details";
    }

    @PostMapping("/ask-remove")
    public String askRemove(Model m, @RequestParam("no") Integer no, @RequestParam(value = "cno", required = false, defaultValue = "2") Integer cno){
        try {
            // no 값이 없으면 오류 처리
            if (no == null) {
                m.addAttribute("msg", "삭제할 게시글을 찾을 수 없습니다.");
                m.addAttribute("url", "/jnh/admin/ask-mng");
                return "alert";
            }

            Map<String, Object> map = new HashMap<>();
            map.put("no", no);
            map.put("cno", cno);

            // 삭제 실행
            int result = askingService.adminRemove(map);

            // 삭제 성공의 경우
            if (result <= 0) {
                throw new Exception("ASKING-MNG_REMOVE_FAIL");
            }
            m.addAttribute("msg", "삭제되었습니다.");
            m.addAttribute("url", "/jnh/admin/ask-details?no=" + no);

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "/jnh/admin/ask-details?no=" + no);

        }
        return "alert";

    }

    @PostMapping("/ask-write")
    public String askWrite(AskingDto askingDto, Model m){
        try {
            askingDto.setCno(2);
            askingDto.setUser_id("관리자");
            askingDto.setTitle("관리자 답변");
            askingDto.setState(0);

            if (askingRequired(askingDto)) {
                throw new Exception("NOT_BLANK");
            }

            if (askingService.adminWrite(askingDto)!=1) {
                throw new Exception("WRT_FAIL");
            } else {
                m.addAttribute("msg","작성되었습니다.");
                m.addAttribute("url", "/jnh/admin/ask-details?no=" + askingDto.getNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(e.getMessage().equals("NOT_BLANK")){
                m.addAttribute("msg", "필수값을 입력해주세요");
            } else {
                m.addAttribute("msg", "다시 입력해주세요");
            }
            m.addAttribute("url","/jnh/admin/ask-details?no=" + askingDto.getNo());
        }
        return "alert";
    }

    @GetMapping("question-mng")
    public String questionMng(SearchCondition sc, Model m){
        Map<String,Object> map = new HashMap<>();
        map.put("sc", sc);
        try {
            int totalCnt = questionService.readMngCnt();
            PageHandler ph = new PageHandler(totalCnt,sc);

            List<Question> list = questionService.readMng(map);

            m.addAttribute("ph", ph);
            m.addAttribute("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/question-mng";
    }

    @PostMapping("answer-write")
    public String answerWrite(SearchCondition sc, Question question, HttpServletRequest request, HttpSession session){
        // 빈 값 체크
        if (question == null || question.getContents() == null || question.getContents().trim().isEmpty()) {
            session.setAttribute("msg", "내용을 입력해주세요.");
            return "redirect:/admin/question-mng?page=" + sc.getPage();
        }
        String id = SessionUtils.getSessionId(request);
        question.setUser_id(id);
        question.setAno(2);

        try {
            if(questionService.write(question) != 1){
                throw new Exception("QUESTION_ANSWER_WRITE_FAIL");
            }
            session.setAttribute("msg", "작성되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "작성에 실패했습니다.");
        }

        return "redirect:/admin/question-mng?page="+sc.getPage();
    }

    private boolean askingRequired(AskingDto askingDto) {
        return askingDto.getContents() == null || askingDto.getContents().isBlank();
    }

    private String productValidation(Product product, MultipartFile file){
        if(product.getProduct_name().isBlank() || product.getGender().isBlank() || product.getCategory().isBlank()
                || product.getColor().isBlank() || (product.getSize() == null || product.getSize().isEmpty())
                || (product.getStock() == null || product.getStock().isEmpty()) || (product.getPrice() < 1
                || product.getPrice() == null)){
            return "모든 값을 입력해주세요.";
        }
        String[] arr = product.getSize().split(",");
        String[] arr2 = product.getStock().split(",");
        if(arr.length < 1 || arr2.length < 1){
            return "사이즈와 재고를 추가해주세요.";
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i].equals("") || arr2[i].equals("")){
                return "사이즈와 재고값을 입력해주세요.";
            }
        }
        if(file.isEmpty()){
            return "이미지를 추가해주세요.";
        }
        if(product.getPrice() > 1000000000){
            return "가격은 1,000,000,000을 초과 할 수 없습니다.";
        }
        if(product.getDiscount() == null){
            product.setDiscount(0);
        }
        if((product.getDiscount() < 0 || product.getDiscount() > 90) && product.getDiscount() != null){
            return "할인율은 90까지 입력가능합니다.";
        }

        if(product.getDiscount() == null){
            product.setDiscount(0);
        }
        return "";
    }

}
