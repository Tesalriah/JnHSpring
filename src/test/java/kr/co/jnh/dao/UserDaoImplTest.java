package kr.co.jnh.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    private UserDao userdao;

    @Test
    public void selectUser() throws Exception{
        Map map = new HashMap();
        map.put("id", "asdf");
        map.put("pwd", "1234");

        userdao.selectUser(map);
    }

    @Test
    public void select() {
    }
}