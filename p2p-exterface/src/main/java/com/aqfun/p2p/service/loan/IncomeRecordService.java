package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.model.loan.IncomeRecord;
import com.aqfun.p2p.model.vo.PaginationVO;

import java.util.Map; /**
 * Package : com.aqfun.p2p.service.loan
 * ClassName : IncomeRecordService
 *
 * @author aq
 * @date 2018/9/9
 */
public interface IncomeRecordService {
    /**
     * 通过用户标识uid 获取最近收益记录列表
     * @param paramMap
     * @return
     */
    PaginationVO<IncomeRecord> queryIncomeRecordListByPage(Map<String, Object> paramMap);

    /**
     * 生成收益计划
     */
    void createIncomeRecordPlain();

    /**
     * 返还收益金额到用户账户
     */
    void createIncomeRecordBack();

    void test();
}
