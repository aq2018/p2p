package com.aqfun.p2p.mapper;

import com.aqfun.p2p.model.loan.IncomeRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeRecord record);

    /**
     * 通过用户标识uid 获取最近收益记录列表
     * @param paramMap
     * @return
     */
    Long selectIncomeRecordListTotalByPage(Map<String, Object> paramMap);

    /**
     * 通过用户标识uid 获取最近收益记录列表总记录条数
     * @param paramMap
     * @return
     */
    List<IncomeRecord> selectIncomeRecordListByPage(Map<String, Object> paramMap);

    /**
     * 查询收益未返还(状态为0)的收益记录
     * @param incomeStatus
     * @return
     */
    List<IncomeRecord> selectIncomeRecordListByIncomeStatus(Integer incomeStatus);
}