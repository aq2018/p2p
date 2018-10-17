package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.mapper.LoanInfoMapper;
import com.aqfun.p2p.model.loan.LoanInfo;
import com.aqfun.p2p.model.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 产品实现类
 * ClassName : LoanInfoServiceImpl
 * User : aq
 * Date : 2018/9/5
 */
@Service
public class LoanInfoServiceImpl implements LoanInfoService {
    @Autowired
    private LoanInfoMapper loanInfoMapper;

    @Autowired
    private RedisTemplate<Object, Object> template;

    public Double queryHistoryAverageRate() {
        Double historyAverageRate = (Double)this.template.opsForValue().get(Const.HISTORY_AVERAGE_RATE);
        if (null == historyAverageRate) {
            historyAverageRate = this.loanInfoMapper.selectHistoryAverageRate();
            this.template.opsForValue().set(Const.HISTORY_AVERAGE_RATE, historyAverageRate, 15, TimeUnit.MINUTES);
        }

        return historyAverageRate;
    }

    @Override
    public List<LoanInfo> queryAByProductTypeAndPage(Map<String, Object> paramMap) {
        return loanInfoMapper.selectByProductTypeAndPage(paramMap);
    }

    @Override
    public LoanInfo queryByLoanId(Integer loanId) {
        LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(loanId);
        return loanInfo;
    }

    @Override
    public PaginationVO<LoanInfo> queryLoanInfoByPage(Map<String, Object> paramMap) {
        PaginationVO<LoanInfo> paginationVO = new PaginationVO<LoanInfo>();
        paginationVO.setDataList(loanInfoMapper.selectByProductTypeAndPage(paramMap));
        paginationVO.setTotal(loanInfoMapper.selectTotalByPage(paramMap));
        return paginationVO;
    }
}
