package com.aqfun.p2p.service.user;

import com.aqfun.p2p.model.user.FinanceAccount;

import java.util.Map;

/**
 * ClassName : FinanceAccountService
 *
 * @author aq
 * @date 2018/9/8
 */
public interface FinanceAccountService {

    /**
     * 通过用户id获取账户资金
     * @param id
     * @return
     */
    FinanceAccount queryFinanceAccountByUid(Integer uid);

    /**
     * 充值
     * @param updateFinanceAccount
     * @return
     */
    boolean modifyFinanceAccountByRecharge(FinanceAccount financeAccount);
}
