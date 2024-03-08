package kr.co.jnh.service;

import kr.co.jnh.dao.UserDao;
import kr.co.jnh.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {

    @Autowired
    UserDao userDao;

    public int addUser(User user){

        return 0;
    }
}
