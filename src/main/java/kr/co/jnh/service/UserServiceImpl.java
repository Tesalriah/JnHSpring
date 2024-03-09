package kr.co.jnh.service;

import kr.co.jnh.dao.EmailAuthDao;
import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    EmailAuthDao emailAuthDao;

    @Override
    public int addUser(User user) throws Exception{
        return userDao.insert(user);
    }

    @Override
    public User showUser(Map map) throws Exception{
        return userDao.selectUser(map);
    }

    public String findEmail(String id) throws Exception{
        return userDao.selectEmail(id);
    }

    @Override
    public boolean idDupl(String id) throws Exception{
        return userDao.selectUserId(id) != null;
    }

    @Override
    public int emailAuth(MailAuthDto mailAuthDto, String id) throws Exception{
       String auth_num = emailAuthDao.selectAuthNum(mailAuthDto.getEmail());
       Map map =new HashMap();
       if(mailAuthDto.getAuth_number().equals(auth_num)){
           map.put("id", id);
           map.put("status", "0");

       }
        return userDao.updateStatus(map);
    }
}
