package kr.co.jnh.controller;

import kr.co.jnh.domain.AskingDto;
import kr.co.jnh.domain.PageHandler;
import kr.co.jnh.domain.SearchCondition;
import kr.co.jnh.service.AskingService;
import kr.co.jnh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import kr.co.jnh.domain.*;
import kr.co.jnh.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminContorller {

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

    // 상품정보 업데이트
    @PostMapping("update-product")
    @ResponseBody
    public Map<String, Object> updateProduct(@RequestBody Map<String, Object> map){
        // 상품을 구별하는 product_id와 size는 map에 이미 할당되어있으므로 type에 따른 동적값을 map에 저장
        String dynamic_value = (String)map.get("dynamic_value");
        String type = (String)map.get("type");
        map.put(type, dynamic_value);

        map.forEach((key, value) -> System.out.println(key + ": " + value));

        if(!type.equals("stock")){ // stock(재고)를 특정사이즈가 아닌 해당 product_id의 모든 값을 변경
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
            int totalCnt = reviewService.readPageByReportCnt(sc);
            PageHandler ph = new PageHandler(totalCnt, sc);
            ph.setNaviSize(5);
            ph.doPaging(totalCnt, sc);
            List<Review> list =  reviewService.readPageByReport(sc);

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
    public String orderStatus(@ModelAttribute OrderList orderList, @RequestParam String[] check_each, SearchCondition sc, RedirectAttributes rattr, HttpSession session){

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

        rattr.addAttribute("category", sc.getCategory());
        if(!sc.getOption().equals("")){
            rattr.addAttribute("option", sc.getOption());
        }if(!sc.getKeyword().equals("")){
            rattr.addAttribute("keyword", sc.getKeyword());
        }
        return "redirect:/admin/order-mng";
    }

    String[] r_category = {"대기중", "처리중", "완료"};

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

    // 주문 리스트
    @PostMapping("return-status")
    public String returnStatus(@ModelAttribute ReturnsList returnsList, @RequestParam String[] check_each, SearchCondition sc, RedirectAttributes rattr, HttpSession session){

        // 페이지의 주문리스트를 모두받기
        List<Returns> returns = returnsList.getReturnsList();
        List<Returns> useReturns = new ArrayList<>();
        // 주문완료일시 배송중으로 배송중일시 배송완료
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

        rattr.addAttribute("category", sc.getCategory());
        if(!sc.getOption().equals("")){
            rattr.addAttribute("option", sc.getOption());
        }if(!sc.getKeyword().equals("")){
            rattr.addAttribute("keyword", sc.getKeyword());
        }
        return "redirect:/admin/return-mng";
    }

    /* 문의목록 가져오기 */
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

    /* 하나의 게시물 읽기 */
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

    @PostMapping("/remove")
    public String remove(Model m, @RequestParam("no") Integer no, @RequestParam(value = "cno", required = false, defaultValue = "2") Integer cno){
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
            int result = askingService.remove(map);

            // 삭제 성공의 경우
            if (result <= 0) {
                throw new Exception("ASKING-MNG_REMOVE_FAIL");
            }
            m.addAttribute("msg", "삭제되었습니다.");
            m.addAttribute("url", "/jnh/admin/ask-mng");

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "삭제에 실패했습니다.");
            m.addAttribute("url", "/jnh/admin/ask-mng");
        }
        return "alert";

    }

}
