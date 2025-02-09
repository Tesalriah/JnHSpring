package kr.co.jnh.dao;

import kr.co.jnh.domain.Wish;

import java.util.List;
import java.util.Map;

public interface WishDao {
    List<Wish> select(Map map) throws Exception;

    Wish selectOne(Wish wish) throws Exception;

    List<Wish> selectAll() throws Exception;

    int insert(Wish wish) throws Exception;

    int delete(Wish wish) throws Exception;

    int getCount(String id) throws Exception;
}
