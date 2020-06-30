package com.hr.test;

import com.hr.dao.IUserDao;
import com.hr.domain.QueryVo;
import com.hr.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = sqlSessionFactory.openSession(true);
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for(User user :users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("mybatis last insert");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前" + user);
        userDao.saveUser(user);
        System.out.println("保存操作之后" + user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(49);
        user.setUsername("mybatis updateuser");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }


    @Test
    public void testDelete() {
        userDao.deleteUser(48);
    }

    @Test
    public void testQueryById() {
        User user = userDao.findById(41);
        System.out.println(user);  //sout
    }


    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%王%");
        System.out.println("模糊查询姓名");
        for(User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() {
        int total = userDao.findTotal();
        System.out.println(total);
    }

    /**
     * 实际开发中应用较多,
     * 开发中通过pojo传递查询条件,查询条件是综合的查询条件,(比如将用户购买的商品信息也作为查询条件)
     * 这时候可以使用包装对象传递输入参数, pojo类中包含pojo
     */
    @Test
    public void testFindUserByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        for(User u : users) {
            System.out.println(u);
        }

    }

}
