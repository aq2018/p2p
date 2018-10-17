package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.mapper.BidInfoMapper;
import com.aqfun.p2p.mapper.FinanceAccountMapper;
import com.aqfun.p2p.mapper.LoanInfoMapper;
import com.aqfun.p2p.model.loan.BidInfo;
import com.aqfun.p2p.model.loan.LoanInfo;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.model.vo.ResultObj;
import com.aqfun.p2p.model.vo.TopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 投资记录接口实现类
 * ClassName : BidInfoServiceImpl
 * @author aq
 * @date 2018/9/5
 */
@Service
public class BidInfoServiceImpl implements BidInfoService {
    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;
    @Autowired
    private RedisTemplate<Object, Object> template;
    @Autowired
    private LoanInfoMapper loanInfoMapper;

    @Override
    public Double queryAllBidMoney() {
        Double allBidMoney = (Double)this.template.opsForValue().get(Const.ALL_BID_MONEY);
        if (null == allBidMoney) {
            allBidMoney = bidInfoMapper.selectAllBidMoney();
            this.template.opsForValue().set(Const.ALL_BID_MONEY, allBidMoney,1, TimeUnit.DAYS);
        }
        return allBidMoney;
    }

    @Override
    public PaginationVO<BidInfo> queryBidInfoListByPage(Map<String,Object> paramMap) {
        PaginationVO<BidInfo> pv = new PaginationVO<>();
        pv.setTotal(bidInfoMapper.selectBidInfoListTotalByPage(paramMap));
        pv.setDataList(bidInfoMapper.selectBidInfoListByPage(paramMap));
        return pv;
    }

    /**
     * 从数据库获取用户投资排行榜
     * @return
     */
    /*@Override
    public List<TopUser> queryTopUserBidList() {
        return bidInfoMapper.selectTopUserBidList();
    }*/

    /**
     * 从redis获取用户投资排行榜**********************************************************
     * @return
     */
    @Override
    public List<TopUser> queryTopUserBidList() {
        List<TopUser> topUserList = new ArrayList<>();
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = template.opsForZSet().reverseRangeWithScores(Const.INVEST_TOP, 0, 9);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = typedTuples.iterator();
        while (iterator.hasNext()) {
            ZSetOperations.TypedTuple<Object> next = iterator.next();
            //获取手机号
            String phone = (String) next.getValue();
            //获取用户投资金额
            Double score = next.getScore();
            TopUser topUser = new TopUser();
            topUser.setPhone(phone);
            topUser.setTotalMoney(score);
            topUserList.add(topUser);
        }
        System.out.println("redis获取投资排行榜");
        return topUserList;
    }

    @Override
    public ResultObj modifyByUid(Map<String, Object> paramMap) {
        template.setKeySerializer(new StringRedisSerializer());
        ResultObj resultObj = new ResultObj();
        resultObj.setErrCode(Const.Fail);
        resultObj.setErrMessage("投资失败");

        boolean flag = false;
        Integer uid = (Integer) paramMap.get("uid");
        double bidMoney = (double) paramMap.get("bidMoney");
        Integer loanId = (Integer) paramMap.get("loanId");
        String phone = (String) paramMap.get("phone");


        //更改产品剩余募集资金
        LoanInfo updateLoanInfo = loanInfoMapper.selectByPrimaryKey(loanId);
        paramMap.put("version",updateLoanInfo.getVersion());//修改版本号
        int updateLoanInfoLeftMoneyCount = loanInfoMapper.updateLoanInfoLeftMoneyAndVersionById(paramMap);

        if ( updateLoanInfoLeftMoneyCount > 0) {//修改产品剩余金额成功
            //更改用户剩余可用资金
            int updateFinanceAccountCount = financeAccountMapper.updateFinanceAccountMoneyByUid(paramMap);

            //新增用户投资记录
            if ( updateFinanceAccountCount > 0) {
                BidInfo bidInfo = new BidInfo();
                bidInfo.setBidMoney(bidMoney);//投资金额
                bidInfo.setBidTime(new Date());//投资时间
                bidInfo.setUid(uid);//投资用户
                bidInfo.setLoanId(loanId);//投资产品
                bidInfo.setBidStatus(1);//投资状态：0失败，1成功
                int insertBidInfoCount = bidInfoMapper.insertSelective(bidInfo);

                if ( insertBidInfoCount > 0) {
                    //判断产品剩余可投金额是否为0，如果为0，说明产品投资完成，并且更新当前产品的状态为1已满标
                    //再次根据产品标识获取产品详情
                    LoanInfo checkLoanInfo = loanInfoMapper.selectByPrimaryKey(loanId);
                    if ( 0 == checkLoanInfo.getLeftProductMoney()) {
                        checkLoanInfo.setProductStatus(1);
                        checkLoanInfo.setProductFullTime(new Date());
                        int updateCheckLoanInfoCount = loanInfoMapper.updateByPrimaryKeySelective(checkLoanInfo);

                        if ( updateCheckLoanInfoCount > 0) { //更新状态成功
                            resultObj.setErrCode(Const.SUCCESS);
                            resultObj.setErrMessage("投资成功");
                            flag = true;

//                            template.delete(Const.ALL_BID_MONEY);
                            //用户投资排行榜:向redis缓存中添加投资记录
                            template.opsForZSet().incrementScore(Const.INVEST_TOP,phone,bidMoney);
                        }
                    } else { //未满标继续募集资金
                        resultObj.setErrCode(Const.SUCCESS);
                        resultObj.setErrMessage("投资成功");
                        flag = true;
                        //用户投资排行榜:向redis缓存中添加投资记录
                        template.opsForZSet().incrementScore(Const.INVEST_TOP,phone,bidMoney);
                    }
                }
            }
        }

        if(!flag){
            throw new RuntimeException();
        }

        return resultObj;
    }

    @Override
    public PaginationVO<BidInfo> queryUserBidListByPage(Map<String, Object> paramMap) {
        PaginationVO<BidInfo> pv = new PaginationVO<>();
        pv.setDataList(bidInfoMapper.selectUserBidListByPage(paramMap));
        pv.setTotal(bidInfoMapper.selectUserBidListTotalByPage(paramMap));
        return pv;
    }
}
