package com.hr.dao;

import com.hr.domain.QueryVo;
import com.hr.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface IUserDao {

    List<User> findAll();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    User findById(Integer id);

    /**
     * 根据姓名模糊查询
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总的用户数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo中的查询条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);
}
