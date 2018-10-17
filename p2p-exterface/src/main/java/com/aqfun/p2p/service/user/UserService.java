package com.aqfun.p2p.service.user;

import com.aqfun.p2p.model.user.User;
import com.aqfun.p2p.model.vo.ResultObj;

/**
 * 用户接口
 * ClassName : UserService
 * @author aq
 * @date 2018/9/5
 */
public interface UserService {
    /**
     *平台用户数
     * @return
     */
    int queryAllUserCount();

    /**
     * 根据手机号获取用户
     * @param phone
     * @return
     */
    User queryUserByPhone(String phone);

    /**
     * 通过手机号注册用户
     * @param phone
     * @param loginPassword
     * @return
     */
    ResultObj register(String phone, String loginPassword);

    /**
     * 用户登录
     * @param phone
     * @param loginPassword
     * @return
     */
    User loginByPhoneAndLoginPassword(String phone, String loginPassword);

    /**
     * 通过用户id更新用户信息
     * @param sessionUser
     * @return
     */
    int modifyUserById(User sessionUser);
}
