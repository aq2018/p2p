package com.aqfun.p2p.mapper;

import com.aqfun.p2p.model.loan.LoanInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface LoanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanInfo record);

    int insertSelective(LoanInfo record);

    LoanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanInfo record);

    /**
     * 获取历史平均年利率
     * @return
     */
    Double selectHistoryAverageRate();

    /**
     * 根据产品类型和页码获取产品列表
     * @return
     */
    List<LoanInfo> selectByProductTypeAndPage(Map<String, Object> paramMap);

    /**
     * 分页查询总记录
     * @param paramMap
     * @return
     */
    Long selectTotalByPage(Map<String, Object> paramMap);

    /**
     * 通过id修改产品剩余金额
     * @param paramMap
     * @return
     */
    int updateLoanInfoLeftMoneyAndVersionById(Map<String, Object> paramMap);

    /**
     * 查询已满标的产品 --> 返回List<产品列表>
     * @param productStatus
     * @return
     */
    List<LoanInfo> selectLoanInfoListByproductStatus(Integer productStatus);
}