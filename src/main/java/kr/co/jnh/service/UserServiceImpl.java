package kr.co.jnh.service;

import kr.co.jnh.dao.EmailAuthDao;
import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.MailAuthDto;
import kr.co.jnh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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

    @Override
    public User getUser(String id) throws Exception{
        return userDao.selectUserById(id);
    }


    @Override
    public String findId(String email) throws Exception{
        String id = userDao.selectId(email);
        return id;
    }

    @Override
    public String findName(String email) throws Exception{
        String id = userDao.selectId(email);
        User user = userDao.selectUserById(id);
        return user.getUser_name();
    }

    @Override
    public boolean checkBirth(String id, Date birth) throws Exception{
        User user = userDao.selectUserById(id);
        return user.getBirth().equals(birth);
    }

    @Override
    public int changeAddress(String id, String address) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);
        map.put("address", address);
        return userDao.update(map);
    }

    @Override
    public int changePassword(String id, String pwd) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);
        map.put("user_pwd", pwd);
        return userDao.update(map);
    }

    @Override
    public int changeStatus(String id, int status) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);
        map.put("status", status);
        return userDao.update(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changePwd(String id, String pwd) throws Exception{
        User user = userDao.selectUserById(id);
        user.setUser_pwd(pwd);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", id);
        map.put("user_pwd", pwd);
        emailAuthDao.deleteAuth(user.getEmail());
        return userDao.update(map);
    }

    @Override
    public boolean idDupl(String id) throws Exception{
        User user = userDao.selectUserById(id);

        return user != null ? true : false;
    }

    @Override
    public boolean emailDupl(String email) throws Exception{
        String id = userDao.selectId(email);
        User user = userDao.selectUserById(id);

        return user != null ? true : false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int emailAuth(MailAuthDto mailAuthDto, String id) throws Exception{
       String auth_num = emailAuthDao.selectAuthNum(mailAuthDto.getEmail());
        Map<String, Object> map = new HashMap<>();
        if(mailAuthDto.getAuth_number().equals(auth_num)){
           map.put("user_id", id);
           map.put("status", 0);
       }
       emailAuthDao.deleteAuth(mailAuthDto.getEmail());
        return userDao.update(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String emailAuth(MailAuthDto mailAuthDto) throws Exception{
        String auth_num = emailAuthDao.selectAuthNum(mailAuthDto.getEmail());
        User user = null;
        if(mailAuthDto.getAuth_number().equals(auth_num)){
            String id = userDao.selectId(mailAuthDto.getEmail());
            user = userDao.selectUserById(id);
        }
        emailAuthDao.deleteAuth(mailAuthDto.getEmail());
        return user == null ? "" : user.getUser_id();
    }

    @Override
    public boolean loginCheck(Map map) throws Exception{
        User user = userDao.selectUser(map);
        if(user == null){
            return false; // user정보를 가져오질 못할시 false
        }
        return true; // 아이디 비밀번호 일치할시 true 반환
    }
}
