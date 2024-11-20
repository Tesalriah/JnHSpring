package kr.co.jnh.dao;

import kr.co.jnh.domain.Returns;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ReturnsDaoImplTest {

    @Autowired
    ReturnsDao returnsDao;

    @Test
    public void select() throws Exception{
        String id = "asd123";
        List<Returns> returnsList = returnsDao.selectAll();
        System.out.println(returnsList);
    }

    @Test
    public void insert() throws Exception{
        Date date = new Date();
        Returns returns = new Returns("asd123", "서울", "20241121001", "20241121001", "return", "M", 1, "신청완료", date, "마음에 들지않음", "마음에 안들어서 반품해요", "");
        for (int i = 0; i < 10; i++) {
            returnsDao.insert(returns);
        }
    }

    @Test
    public void delete() throws Exception{
        int rno = 1;
        returnsDao.delete(rno);
    }

    @Test
    public void update() throws  Exception{
        Map map = new HashMap();
        map.put("rno" , 2);
        map.put("status", "취소완료");
        returnsDao.update(map);
    }

    @Test
    public void count() throws  Exception {
        int cnt = returnsDao.getCount("asd123");
        System.out.println("cnt = " + cnt);
    }
}