package kr.co.jnh.service;

import kr.co.jnh.dao.OrderDao;
import kr.co.jnh.dao.ProductDao;
import kr.co.jnh.dao.ReturnsDao;
import kr.co.jnh.dao.ReviewDao;
import kr.co.jnh.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReturnsServiceImpl implements ReturnsService {

    @Autowired
    ReturnsDao returnsDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ReviewDao reviewDao;

    @Override
    public List<List<Returns>> read(Map map) throws Exception{
        List<Returns> returnsList = returnsDao.selectPage(map);
        List<List<Returns>> returnsListList = new ArrayList<>();
        for(Returns returns : returnsList){
            List<Returns> each = returnsDao.selectOne(returns.getReturn_id());
            for(Returns setProduct : each){
                Product product = productDao.select(setProduct.getProduct_id());
                product.setQuantity(setProduct.getQuantity());
                setProduct.setProduct(product);
            }
            returnsListList.add(each);
        }
        return returnsListList;
    }

    @Override
    public int create(Returns returns) throws Exception{
        return returnsDao.insert(returns);
    }

    @Override
    public int remove(Integer rno) throws Exception{
        return returnsDao.delete(rno);
    }

    @Override
    public int modify(Map map) throws Exception{
        return returnsDao.update(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int mngModify(Map map) throws Exception{
        int result = returnsDao.mngUpdate(map);
        String type = (String)map.get("type");
        String status = (String)map.get("status");
        if(type.equals("exchange")){
            type = "교환";
        }if(type.equals("return")){
            type = "반품";
        }if(type.equals("cancel")){
            type = "취소";
        }
        if(type.equals("반품") && status.equals("완료")){
            Order order = orderDao.selectOrderWithProduct(map).get(0);

            Map<String, String> parameters = new HashMap<>();
            parameters.put("cid", "TC0ONETIME");                            // 가맹점 코드(테스트용)
            parameters.put("tid", order.getTid());                    // 결제 고유번호
            parameters.put("cancel_amount", order.getProduct().getDis_price()+"");
            parameters.put("cancel_tax_free_amount", "0");
            parameters.put("partner_order_id", order.getOrder_no());  // 주문번호
            parameters.put("partner_user_id", order.getUser_id());    // 회원 아이디

            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

            RestTemplate template = new RestTemplate();
            String url = "https://open-api.kakaopay.com/online/v1/payment/cancel";
            CancelResponse cancelResponse = template.postForObject(url, requestEntity, CancelResponse.class);
            log.info("취소승인 응답객체: " + cancelResponse);
        }

        Map<String,Object> orderMap = new HashMap<>();
        map.forEach((key, value) -> orderMap.put((String)key, value));
        orderMap.put("status", type+status);
        if(orderDao.returnUpdate(orderMap) != 1){
            throw new Exception("ORDER_STATUS_UPDATE_FAIL");
        }
        return result;
    }

    @Override
    public int count(String id) throws Exception{
        return returnsDao.getCount(id);
    }

    @Override
    public List<Returns> readAll() throws Exception{
        return returnsDao.selectAll();
    }

    @Override
    public String readId(String return_id) throws Exception{
        return returnsDao.selectId(return_id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int returns(List<Returns> list) throws Exception{
        int result = -1;
        Map map = new HashMap();

        for (Returns returns : list) {
            result = returnsDao.insert(returns);
            if(result != 1){
                throw new Exception("RETURNS_INSERT_FAIL");
            }
            if(returns.getType().equals("exchange")){
                map.put("status", "교환대기중");
            }if(returns.getType().equals("return")){
                map.put("status", "반품대기중");
            }if(returns.getType().equals("cancel")){
                map.put("status", "취소완료");
                map.put("return_id", returns.getReturn_id());
                if(returnsDao.update(map) == 0){
                    throw new Exception("RETURNS_UPDATE_FAIL");
                }
            }
            map.put("order_no", returns.getOrder_no());
            map.put("id", returns.getUser_id());
            map.put("product_id", returns.getProduct_id());
            map.put("size", returns.getSize());
            if(orderDao.returnUpdate(map) <= 0){
                throw new Exception("ORDER_STATUS_UPDATE_FAIL");
            }
            map.put("whether", 2);
            if(!returns.getType().equals("cancel")){
                if(reviewDao.cancelDelete(map) <= 0){
                    throw new Exception("REVIEW_DELETE_FAIL");
                }
            }
        }
        productDao.updateReviewAvg();

        return result;
    }

    @Override
    public int readMngCnt(SearchCondition sc) throws Exception{
        return returnsDao.selectMngCnt(sc);
    }

    @Override
    public List<Returns> readMng(SearchCondition sc) throws Exception{
        return returnsDao.selectMng(sc);
    }
}
