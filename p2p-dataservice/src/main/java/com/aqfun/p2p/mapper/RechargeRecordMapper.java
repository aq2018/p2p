package com.aqfun.p2p.mapper;

import com.aqfun.p2p.model.loan.RechargeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeRecord record);


    /**
     * 通过用户标识uid 获取最近充值记录列表
     * @param paramMap
     * @return
     */
    List<RechargeRecord> selectRechargeRecordListByPage(Map<String, Object> paramMap);

    /**
     * 通过用户标识uid 获取最近充值记录列表总数
     * @param paramMap
     * @return
     */
    Long selectRechargeRecordListTotalByPage(Map<String, Object> paramMap);


    /**
     * 通过充值订单号查询充值记录
     * @param rechargeNo
     * @return
     */
    RechargeRecord selectRechargeRecordByRechargeNo(String rechargeNo);

    /**
     * 通过充值订单更改充值状态
     * @param updateRecharge
     * @return
     */
    int updateByRechargeNo(RechargeRecord updateRecharge);
}