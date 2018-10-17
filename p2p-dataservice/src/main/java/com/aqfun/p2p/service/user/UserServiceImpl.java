package com.aqfun.p2p.service.user;

import com.aqfun.p2p.mapper.FinanceAccountMapper;
import com.aqfun.p2p.mapper.UserMapper;
import com.aqfun.p2p.model.user.FinanceAccount;
import com.aqfun.p2p.model.user.User;
import com.aqfun.p2p.model.vo.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 用户实现类
 * ClassName : UserServiceImpl
 * @author aq
 * @date 2018/9/5
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<Object,Object> template;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;


    @Override
    public int queryAllUserCount() {
        Integer allUserCount = (Integer) template.opsForValue().get("allUserCount");
        if (null == allUserCount) {
            allUserCount = userMapper.selectAllUserCount();
            template.opsForValue().set("allUserCount",allUserCount,1, TimeUnit.DAYS);
        }
        return allUserCount;
    }

    @Override
    public User queryUserByPhone(String phone) {
        return  userMapper.selectUserByPhone(phone);
    }

    @Override
    public ResultObj register(String phone,String loginPassword) {
        boolean flag = true;
        ResultObj resultObj = new ResultObj();
        User user = new User();
        user.setAddTime(new Date());
        user.setLastLoginTime(new Date());
        user.setPhone(phone);
        user.setLoginPassword(loginPassword);
        int count = userMapper.insertSelective(user);
        if( count == 1){//注册成功
            //给用户开户,设置账户默认值 红包
            User userInfo = userMapper.selectUserByPhone(phone);
            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setUid(userInfo.getId());
            financeAccount.setAvailableMoney(888.0);
            int insertFinanceAccount = financeAccountMapper.insertSelective(financeAccount);
            if(insertFinanceAccount != 1){//开户成功
                resultObj.setErrCode("0");
                resultObj.setErrMessage("注册失败:新增账户失败");
                flag = false;
            }
        }else{
            resultObj.setErrCode("0");
            resultObj.setErrMessage("注册失败:新增用户失败");
            flag = false;
        }
        if(!flag){
            throw new RuntimeException();
        }
        return resultObj;
    }

    @Override
    public User loginByPhoneAndLoginPassword(String phone, String loginPassword) {
        User user = new User();
        user.setPhone(phone);
        user.setLoginPassword(loginPassword);

        User retUser = userMapper.selectByPhoneAndLoginPassword(user);
        if(retUser != null){

           User updateUser = userMapper.selectUserByPhone(phone);
           updateUser.setLastLoginTime(new Date());
           updateUser.setId(updateUser.getId());
           int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);

           //修改登录时间
           if(updateCount == 1){
               User loginUser = userMapper.selectUserByPhone(phone);
               return loginUser;
           }

        }
        return retUser;
    }

    @Override
    public int modifyUserById(User sessionUser) {
        return userMapper.updateByPrimaryKeySelective(sessionUser);
    }
}
