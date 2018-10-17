package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.model.loan.RechargeRecord;
import com.aqfun.p2p.model.vo.PaginationVO;

import java.util.Map; /**
 * 用户充值接口
 * Package : com.aqfun.p2p.service.loan
 * ClassName : RechargeRecordService
 *
 * @author aq
 * @date 2018/9/9
 */
public interface RechargeRecordService {
    /**
     * 通过用户标识uid 获取最近充值记录列表
     * @param paramMap
     * @return
     */
    PaginationVO<RechargeRecord> queryRechargeRecordListByPage(Map<String, Object> paramMap);

    /**
     * 新增充值订单
     * @param rechargeRecord
     * @return
     */
    boolean addRechargeRecord(RechargeRecord rechargeRecord);

    /**
     * 通过充值订单号查询充值记录
     * @param rechargeNo
     * @return
     */
    RechargeRecord queryRechargeRecordByRechargeNo(String rechargeNo);

    /**
     * 修改充值订单状态为1
     * @param updateRecharge
     * @return
     */
    boolean modifyRechargeRecordByRechargeNo(RechargeRecord updateRecharge);
}
