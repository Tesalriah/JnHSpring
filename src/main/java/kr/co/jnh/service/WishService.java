package kr.co.jnh.service;

import kr.co.jnh.domain.Wish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WishService {
    List<Wish> read(String id) throws Exception;

    int readOne(Wish wish) throws Exception;

    List<Wish> readAll() throws Exception;

    int write(Wish wish) throws Exception;

    int remove(Wish wish) throws Exception;

    int totalCnt(String id) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int addCart(Wish wish, String size) throws Exception;
}
