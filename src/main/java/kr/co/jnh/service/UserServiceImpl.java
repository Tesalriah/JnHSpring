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

    public Integer getGrade(String id) throws Exception{
        User user = userDao.selectUserById(id);
        return user.getGrade();
    }

    public Integer getStatus(String id) throws Exception{
        User user = userDao.selectUserById(id);
        return user.getStatus();
    }

    public String findEmail(String id) throws Exception{
        User user = userDao.selectUserById(id);
        return user.getEmail();
    }

    public String findName(String email) throws Exception{
        String id = userDao.selectId(email);
        User user = userDao.selectUserById(id);
        return user.getUser_name();
    }

    @Override
    public boolean idDupl(String id) throws Exception{
        User user = userDao.selectUserById(id);
        if(user != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean emailDupl(String email) throws Exception{
        String id = userDao.selectId(email);
        User user = userDao.selectUserById(id);
        if(user != null){
            return true;
        }else{
            return false;
        }
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

    public String emailAuth(MailAuthDto mailAuthDto) throws Exception{
        String auth_num = emailAuthDao.selectAuthNum(mailAuthDto.getEmail());
        User user = null;
        if(mailAuthDto.getAuth_number().equals(auth_num)){
            String id = userDao.selectId(mailAuthDto.getEmail());
            user = userDao.selectUserById(id);
        }
        return user.getUser_id();
    }
}
