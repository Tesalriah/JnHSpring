package kr.co.jnh.dao;

import kr.co.jnh.domain.Wish;

import java.util.List;

public interface WishDao {
    List<Wish> select(String id) throws Exception;

    Wish selectOne(Wish wish) throws Exception;

    List<Wish> selectAll() throws Exception;

    int insert(Wish wish) throws Exception;

    int delete(Wish wish) throws Exception;

    int getCount(String id) throws Exception;
}
