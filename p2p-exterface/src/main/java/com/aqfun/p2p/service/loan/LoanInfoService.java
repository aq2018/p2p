package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.model.loan.LoanInfo;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.model.vo.ResultObj;

import java.util.List;
import java.util.Map;

/**
 * 产品业务接口层
 * ClassName : LoanInfoService
 * Date : 2018/9/4
 * User : aq
 */
public interface LoanInfoService {
    /**
     * 获取平台产品历史平均年化收益率
     * @return
     */
    Double queryHistoryAverageRate();

    /**
     * 根据产品类型和页码获取产品列表
     * @return
     */
    List<LoanInfo> queryAByProductTypeAndPage(Map<String, Object> paramMap);

    /**
     * 通过产品id获取产品列表
     * @param loanId
     * @return
     */
    LoanInfo queryByLoanId(Integer loanId);

    /**
     * 分页查询产品
     * @param paramMap
     * @return
     */
    PaginationVO<LoanInfo> queryLoanInfoByPage(Map<String, Object> paramMap);

}
