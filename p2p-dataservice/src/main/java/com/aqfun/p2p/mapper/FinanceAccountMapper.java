package com.aqfun.p2p.mapper;

import com.aqfun.p2p.model.user.FinanceAccount;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    /**
     * 通过用户id查询用户资金
     * @param uid
     * @return
     */
    FinanceAccount selectFinanceAccountByUid(Integer uid);

    /**
     * 通过uid更改用户剩余可用资金
     * @param paramMap
     * @return
     */
    int updateFinanceAccountMoneyByUid(Map<String, Object> paramMap);

    /**
     * 返还收益
     * @param paramMap
     * @return
     */
    int updateFinanceAccountMoneyByIncomeBack(Map<String, Object> paramMap);

    /**
     * 充值
     * @param financeAccount
     * @return
     */
    int updateFinanceAccountMoneyByRecharge(FinanceAccount financeAccount);
}