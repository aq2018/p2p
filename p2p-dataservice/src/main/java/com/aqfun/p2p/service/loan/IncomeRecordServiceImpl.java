package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.mapper.BidInfoMapper;
import com.aqfun.p2p.mapper.FinanceAccountMapper;
import com.aqfun.p2p.mapper.IncomeRecordMapper;
import com.aqfun.p2p.mapper.LoanInfoMapper;
import com.aqfun.p2p.model.loan.BidInfo;
import com.aqfun.p2p.model.loan.IncomeRecord;
import com.aqfun.p2p.model.loan.LoanInfo;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.utils.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 收益实现类
 * Package : com.aqfun.p2p.service.loan
 * ClassName : IncomeRecordServiceImpl
 *
 * @author aq
 * @date 2018/9/9
 */
@Service
public class IncomeRecordServiceImpl implements IncomeRecordService {

    private Logger logger =  Logger.getLogger(this.getClass());

    @Autowired
    private IncomeRecordMapper incomeRecordMapper;
    @Autowired
    private LoanInfoMapper loanInfoMapper;
    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;

    @Override
    public PaginationVO<IncomeRecord> queryIncomeRecordListByPage(Map<String, Object> paramMap) {
        PaginationVO<IncomeRecord> pv = new PaginationVO<>();
        pv.setTotal(incomeRecordMapper.selectIncomeRecordListTotalByPage(paramMap));
        pv.setDataList(incomeRecordMapper.selectIncomeRecordListByPage(paramMap));
        return pv;
    }

    @Override
    public void createIncomeRecordPlain() {
        //1.查询已满标的产品 --> 返回List<产品列表>
        Integer productStatus = 1;
        List<LoanInfo> loanInfoList = loanInfoMapper.selectLoanInfoListByproductStatus(productStatus);
        //2.遍历List<产品列表> 取出每一个产品记录
        for (LoanInfo loanInfo : loanInfoList) {
            Integer loanId = loanInfo.getId();
            //3.通过产品id,并且成功投资查询投资表返回投资列表 --> 返回iList<投资列表>
            List<BidInfo> bidInfoList = bidInfoMapper.selectBidInfoListByLoanId(loanId);
            //4.遍历List<投资列表> 取出每个投资记录
            for(BidInfo bidInfo : bidInfoList) {
                // 5.新增对应的收益记录
                IncomeRecord incomeRecord = new IncomeRecord();
                incomeRecord.setBidMoney(bidInfo.getBidMoney());//投资金额
                incomeRecord.setBidId(bidInfo.getId());//投资标识
                incomeRecord.setIncomeStatus(0);//收益标识0未返回,1已返回
                incomeRecord.setLoanId(loanId);//收益产品标识
                incomeRecord.setUid(bidInfo.getUid());
                Date incomeDate = null;//收益时间
                Double incomeMoney = null;//收益金额
                if(Const.PRODUCT_TYPE_X == loanInfo.getProductType()){//新手宝
                    //收益时间 = 产品满标时间+产品周期
                    incomeDate = DateUtil.getDateByDays(loanInfo.getProductFullTime(),loanInfo.getCycle());
                    //收益金额=投资金额*天利率*天数
                    incomeMoney = bidInfo.getBidMoney() * (loanInfo.getRate() / 100 / 360) * loanInfo.getCycle();
                }else {//散标或优选
                    incomeDate = DateUtil.getDateByMonth(loanInfo.getProductFullTime(),loanInfo.getCycle());
                    incomeMoney = bidInfo.getBidMoney() * (loanInfo.getRate() / 100 / 12) * loanInfo.getCycle();
                }
                incomeMoney = Math.round(incomeMoney * Math.pow(10,2)) / Math.pow(10,2);
                incomeRecord.setIncomeDate(incomeDate);
                incomeRecord.setIncomeMoney(incomeMoney);

                int insertIncomeRecordCount = incomeRecordMapper.insertSelective(incomeRecord);
                if (insertIncomeRecordCount > 0) {
                    logger.info("用户标识uid为" + incomeRecord.getUid() + ",生成收益成功,投资标识为" + incomeRecord.getLoanId());
                }else {
                    logger.info("用户标识uid为" + incomeRecord.getUid() + ",生成收益【失败】,投资标识为" + incomeRecord.getLoanId());
                }
            }

            //6.更改产品状态为2(满标已生成收益)
            LoanInfo updateLoanInfo = new LoanInfo();
            updateLoanInfo.setId(loanId);
            updateLoanInfo.setProductStatus(2);
            int updateCount = loanInfoMapper.updateByPrimaryKeySelective(updateLoanInfo);
            if(updateCount > 0) {
                logger.info("产品标识为" + loanId + "的状态更新为满标且成功收益计划成功");
            } else {
                logger.info("产品标识为" + loanId + "的状态更新为满标且成功收益计划【失败】");
            }
        }

    }

    @Override
    public void createIncomeRecordBack() {
        //1.查询收益状态0的收益记录 --> List<收益记录>
        Integer incomeStatus = 0;//0:未返还金额,1:已返还
        List<IncomeRecord> incomeRecordList = incomeRecordMapper.selectIncomeRecordListByIncomeStatus(incomeStatus);
        for(IncomeRecord incomeRecord : incomeRecordList) {
            //2.返还用户收益金额
            Map<String,Object> paramMap = new ConcurrentHashMap<>();
            paramMap.put("uid",incomeRecord.getUid());
            paramMap.put("bidMoney",incomeRecord.getBidMoney());
            paramMap.put("incomeMoney",incomeRecord.getIncomeMoney());
            int updateFinanceCount = financeAccountMapper.updateFinanceAccountMoneyByIncomeBack(paramMap);
            if(updateFinanceCount > 0) {
                logger.info("用户标识为" + incomeRecord.getUid() + "的账户资金收益返还成功");
            } else {
                logger.info("用户标识为" + incomeRecord.getUid() + "的账户资金收益返还【失败】");
            }
            //更新收益记录状态为1
            IncomeRecord incomeRecord_1 = new IncomeRecord();
            incomeRecord_1.setId(incomeRecord.getId());
            incomeRecord_1.setIncomeStatus(1);
            int updateIncomeStateCount = incomeRecordMapper.updateByPrimaryKeySelective(incomeRecord_1);
            if(updateIncomeStateCount > 0) {
                logger.info("收益标识为" + incomeRecord_1.getUid() + "更新收益返还状态成功");
            } else {
                logger.info("收益标识为" + incomeRecord_1.getUid() + "更新收益返还状态【失败】");
            }
        }

    }

    @Override
    public void test() {
        logger.info("打印日志");
    }
}
