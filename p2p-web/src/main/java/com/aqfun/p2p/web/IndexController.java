package com.aqfun.p2p.web;

import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.model.loan.LoanInfo;
import com.aqfun.p2p.service.loan.BidInfoService;
import com.aqfun.p2p.service.loan.LoanInfoService;
import com.aqfun.p2p.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页控制器
 * ClassName : IndexController
 * User : aq
 * Date : 2018/9/5
 */
@Controller
public class IndexController {
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private BidInfoService bidInfoService;

    @RequestMapping("/index")
    public String doIndex(HttpServletRequest request, Model model) {
        ServletContext application = request.getServletContext();

        //历史年化收益率
        Double historyAverageRate = loanInfoService.queryHistoryAverageRate();
        //平台用户数
        Integer allUserCount = userService.queryAllUserCount();

        //累计成交额
        Double allBidMoney = bidInfoService.queryAllBidMoney();

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put(Const.PARAM_CURRENT_PAGE,Const.PRODUCT_TYPE_X);//首页页码

        //获取新手宝产品列表
        paramMap.put(Const.PARAM_PAGE_SIZE,1);
        paramMap.put("productType",Const.PRODUCT_TYPE_U);
        List<LoanInfo> xLoanInfoList = loanInfoService.queryAByProductTypeAndPage(paramMap);

        //获取优选宝产品列表
        paramMap.put(Const.PARAM_PAGE_SIZE,4);
        paramMap.put("productType",Const.PRODUCT_TYPE_S);
        List<LoanInfo> uLoanInfoList = loanInfoService.queryAByProductTypeAndPage(paramMap);

        //获取散标宝产品列表
        paramMap.put(Const.PARAM_PAGE_SIZE,8);
        paramMap.put("productType",2);
        List<LoanInfo> sLoanInfoList = loanInfoService.queryAByProductTypeAndPage(paramMap);

        model.addAttribute(Const.HISTORY_AVERAGE_RATE, historyAverageRate);
        model.addAttribute(Const.ALL_USER_COUNT, allUserCount);
        model.addAttribute(Const.ALL_BID_MONEY, allBidMoney);
        model.addAttribute("xLoanInfoList",xLoanInfoList);
        model.addAttribute("uLoanInfoList",uLoanInfoList);
        model.addAttribute("sLoanInfoList",sLoanInfoList);
        return "index";
    }
}
