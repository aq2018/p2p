package com.aqfun.p2p.mapper;

import com.aqfun.p2p.model.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 获取平台用户数
     * @return
     */
    Integer selectAllUserCount();

    /**
     * 根据手机获取用户
     * @param phone
     * @return
     */
    User selectUserByPhone(String phone);

    /**
     * 通过手机号和密码验证用户登录:1存在用户,0不存在
     * @param user
     * @return
     */
    User selectByPhoneAndLoginPassword(User user);
}