package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.consts.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 生成唯一数字
 * Package : com.aqfun.p2p.service.loan
 * ClassName : OnlyNumServiceImpl
 *
 * @author aq
 * @date 2018/9/11
 */
@Service
public class OnlyNumServiceImpl implements OnlyNumService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Long getOnlyNumber() {
        return redisTemplate.opsForValue().increment(Const.ONLY_NUM,1);
    }
}
