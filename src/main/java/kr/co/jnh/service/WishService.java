package kr.co.jnh.service;

import kr.co.jnh.domain.Wish;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface WishService {
    List<Wish> read(Map map) throws Exception;

    boolean isThere(Wish wish) throws Exception;

    List<Wish> readAll() throws Exception;

    int write(Wish wish) throws Exception;

    int remove(Wish wish) throws Exception;

    int totalCnt(String id) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int addCart(Wish wish, String size) throws Exception;
}
