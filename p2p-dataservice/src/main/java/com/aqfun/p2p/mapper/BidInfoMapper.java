package com.aqfun.p2p.mapper;

import com.aqfun.p2p.model.loan.BidInfo;
import com.aqfun.p2p.model.vo.TopUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    /**
     * 获取累计成交额
     * @return
     */
    Double selectAllBidMoney();

    /**
     * 根据产品id 分页显示该产品所有投资记录
     * @param paramMap
     * @return
     */
    List<BidInfo> selectBidInfoListByPage(Map<String,Object> paramMap);

    /**
     * 根据产品id 分页显示该产品所有投资记录总记录条数
     * @param paramMap
     * @return
     */
    Long selectBidInfoListTotalByPage(Map<String, Object> paramMap);

    /**
     * 用户投资排行榜
     * @return
     */
    List<TopUser> selectTopUserBidList();

    /**
     * 根据用户标识uid 分页获取用户相关投资记录,同时关联取出产品信息
     * @param paramMap
     * @return
     */
    List<BidInfo> selectUserBidListByPage(Map<String, Object> paramMap);

    /**
     * 根据用户标识uid 分页获取用户相关投资记录,同时关联取出产品信息的总条数
     * @param paramMap
     * @return
     */
    Long selectUserBidListTotalByPage(Map<String, Object> paramMap);

    /**
     * 通过产品id查询投资表返回投资列表 --> 返回iList<投资列表>
     * @param loanId
     * @return
     */
    List<BidInfo> selectBidInfoListByLoanId(Integer loanId);
}