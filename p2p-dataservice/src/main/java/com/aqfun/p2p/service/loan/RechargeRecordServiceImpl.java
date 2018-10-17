package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.mapper.FinanceAccountMapper;
import com.aqfun.p2p.mapper.RechargeRecordMapper;
import com.aqfun.p2p.model.loan.RechargeRecord;
import com.aqfun.p2p.model.user.FinanceAccount;
import com.aqfun.p2p.model.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 充值实现类
 * Package : com.aqfun.p2p.service.loan
 * ClassName : RechargeRecordServiceImpl
 *
 * @author aq
 * @date 2018/9/9
 */
@Service
public class RechargeRecordServiceImpl implements RechargeRecordService {
    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Override
    public PaginationVO<RechargeRecord> queryRechargeRecordListByPage(Map<String, Object> paramMap) {
        PaginationVO<RechargeRecord> pv = new PaginationVO<>();
        pv.setTotal(rechargeRecordMapper.selectRechargeRecordListTotalByPage(paramMap));
        pv.setDataList(rechargeRecordMapper.selectRechargeRecordListByPage(paramMap));
        return pv;
    }

    @Override
    public boolean addRechargeRecord(RechargeRecord rechargeRecord) {
        return rechargeRecordMapper.insertSelective(rechargeRecord) == 1;
    }

    @Override
    public RechargeRecord queryRechargeRecordByRechargeNo(String rechargeNo) {
        return rechargeRecordMapper.selectRechargeRecordByRechargeNo(rechargeNo);
    }

    @Override
    public boolean modifyRechargeRecordByRechargeNo(RechargeRecord updateRecharge) {
        //更新充值状态
        int updateRechargeCount = rechargeRecordMapper.updateByRechargeNo(updateRecharge);
        if (updateRechargeCount == 1) {

            //为用户充值金额
            RechargeRecord rechargeRecord = rechargeRecordMapper.selectRechargeRecordByRechargeNo(updateRecharge.getRechargeNo());
            FinanceAccount updateFinanceAccount = new FinanceAccount();
            updateFinanceAccount.setUid(rechargeRecord.getUid());
            updateFinanceAccount.setAvailableMoney(rechargeRecord.getRechargeMoney());
            int updateFinanceAccountCount = financeAccountMapper.updateFinanceAccountMoneyByRecharge(updateFinanceAccount);
            if (updateFinanceAccountCount == 1) {
                return true;
            }
        }
        return false;
    }
}
