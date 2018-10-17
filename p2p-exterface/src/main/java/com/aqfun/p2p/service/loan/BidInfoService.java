package com.aqfun.p2p.service.loan;

import com.aqfun.p2p.model.loan.BidInfo;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.model.vo.ResultObj;
import com.aqfun.p2p.model.vo.TopUser;

import java.util.List;
import java.util.Map;

/**
 * 投资记录接口
 * ClassName : BinInfoService
 * @author aq
 * @date 2018/9/5
 */
public interface BidInfoService {
    /**
     * 获取累计成交额
     * @return
     */
    Double queryAllBidMoney();

    /**
     * 根据产品id 分页显示该产品所有投资记录
     * @param paramMap
     * @return
     */
    PaginationVO<BidInfo> queryBidInfoListByPage(Map<String,Object> paramMap);

    /**
     * 查询用户投资排行榜
     * @return
     */
    List<TopUser> queryTopUserBidList();

    /**
     * 用户投资产品
     * @param paramMap
     * @return
     */
    ResultObj modifyByUid(Map<String, Object> paramMap);

    /**
     * 根据用户标识uid 分页获取用户相关投资记录,同时关联取出产品信息
     * @param paramMap
     * @return
     */
    PaginationVO<BidInfo> queryUserBidListByPage(Map<String, Object> paramMap);
}
