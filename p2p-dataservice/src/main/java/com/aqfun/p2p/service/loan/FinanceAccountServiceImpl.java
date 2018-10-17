package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.mapper.FinanceAccountMapper;
import com.aqfun.p2p.model.user.FinanceAccount;
import com.aqfun.p2p.service.user.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户资金实现类
 * Package : com.aqfun.p2p.service.loan
 * ClassName : FinanceAccountServiceImpl
 *
 * @author aq
 * @date 2018/9/8
 */
@Service
public class FinanceAccountServiceImpl implements FinanceAccountService {
    @Autowired
    private  FinanceAccountMapper financeAccountMapper;

    @Override
    public FinanceAccount queryFinanceAccountByUid(Integer uid) {
        return financeAccountMapper.selectFinanceAccountByUid(uid);
    }

    @Override
    public boolean modifyFinanceAccountByRecharge(FinanceAccount financeAccount) {
        return financeAccountMapper.updateFinanceAccountMoneyByRecharge(financeAccount) == 1;
    }

}
