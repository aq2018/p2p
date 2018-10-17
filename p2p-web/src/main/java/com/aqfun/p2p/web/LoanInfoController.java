package com.aqfun.p2p.web;

import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.model.loan.BidInfo;
import com.aqfun.p2p.model.loan.LoanInfo;
import com.aqfun.p2p.model.user.FinanceAccount;
import com.aqfun.p2p.model.user.User;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.model.vo.TopUser;
import com.aqfun.p2p.service.loan.BidInfoService;
import com.aqfun.p2p.service.user.FinanceAccountService;
import com.aqfun.p2p.service.loan.LoanInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 产品信息控制器
 * ClassName : LoanInfoController
 *
 * @author aq
 * @date 2018/9/5
 */
@Controller
public class LoanInfoController {
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private BidInfoService bidInfoService;
    @Autowired
    private FinanceAccountService financeAccountService;

    @RequestMapping("/loan/loanInfo")
    public String doLoInfo(HttpServletRequest request,
                           @RequestParam(value = "loanId") Integer loanId,
                           @RequestParam(value = "currentPage",required = false) Integer currentPage,
                           Model model){
        LoanInfo loanInfo = loanInfoService.queryByLoanId(loanId);
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        if (null != user) {
            //账户资金余额
            FinanceAccount financeAccount = financeAccountService.queryFinanceAccountByUid(user.getId());
            model.addAttribute("financeAccount",financeAccount);
        }

        //获取该产品所有投资记录
        //分页参数
        if (null == currentPage) {
            currentPage = 1;
        }
        int pageSize = Const.BIDINFO_PAGE_SIZE;
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put(Const.PARAM_CURRENT_PAGE,(currentPage - 1) * Const.BIDINFO_PAGE_SIZE);
        paramMap.put(Const.PARAM_PAGE_SIZE,pageSize);
        paramMap.put("loanId",loanId);
        PaginationVO<BidInfo> pvList = bidInfoService.queryBidInfoListByPage(paramMap);

        //总记录数
        int totalRows = pvList.getTotal().intValue();
        int totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows/ pageSize + 1;
        model.addAttribute("loanInfo",loanInfo);
        model.addAttribute("bidInfoList",pvList.getDataList());
        model.addAttribute("totalRows",totalRows);
        model.addAttribute(Const.PARAM_PAGE_SIZE,Const.BIDINFO_PAGE_SIZE);
        model.addAttribute(Const.PARAM_CURRENT_PAGE,currentPage);
        model.addAttribute("totalPage",totalPage);
        return "loanInfo";
    }

    @RequestMapping("/loan/loanInfoMore")
    @ResponseBody
    public Object loanInfoMore(HttpServletRequest request,
                           @RequestParam(value = "loanId") Integer loanId,
                           @RequestParam(value = "currentPage") Integer currentPage,
                           Model model){

        //分页参数
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put(Const.PARAM_CURRENT_PAGE,(currentPage - 1) * Const.BIDINFO_PAGE_SIZE);
        paramMap.put(Const.PARAM_PAGE_SIZE,Const.BIDINFO_PAGE_SIZE);
        paramMap.put("loanId",loanId);
        PaginationVO<BidInfo> pvList = bidInfoService.queryBidInfoListByPage(paramMap);
        System.out.println(pvList.getDataList());
        return pvList.getDataList();
    }

    @RequestMapping("/loan/loan")
    public String loan(HttpServletRequest request, Model model,
                       @RequestParam(value = "ptype",required = false) String productType,
                       @RequestParam(value = "currentPage",required = false)Integer currentPage){
        if(null == currentPage){
            currentPage = 1;
        }
        //调用产品业务接口中的方法：分页查询产品信息列表 -》 产品信息列表List,总记录数【分页模型对象PaginationVO<T>】
        //准备分页请求的参数
        Map<String,Object> paramMap = new ConcurrentHashMap<String,Object>();
        paramMap.put(Const.PARAM_CURRENT_PAGE, (currentPage-1)*Const.PAGE_SIZE);//页码
        paramMap.put(Const.PARAM_PAGE_SIZE, Const.PAGE_SIZE);//每页显示大小
        //首先判断产品类是否为空，为空：默认查询所有产品
        if(null != productType && !"".equals(productType)) {
            paramMap.put("productType", productType);//产品类型
        }

        PaginationVO<LoanInfo> paginationVO = loanInfoService.queryLoanInfoByPage(paramMap);

        //计算总页数 = 总记录数 / 每页大小;
        int totalRows = paginationVO.getTotal().intValue();
        int totalPage = totalRows / Const.PAGE_SIZE;
        totalPage = totalRows % Const.PAGE_SIZE == 0 ? totalPage : totalPage + 1;

        //投资排行榜************************************************
        List<TopUser> topUserBidList = bidInfoService.queryTopUserBidList();
        System.out.println("topUserBidList"+topUserBidList);

        //将以上查询的参数存放到model对象中
        model.addAttribute("topUserBidList", topUserBidList);//用户投资排行榜
        model.addAttribute("totalPage", totalPage);//总页数
        model.addAttribute("totalRows", totalRows);//总记录数
        model.addAttribute("loanInfoList", paginationVO.getDataList());//总记录数据
        model.addAttribute(Const.PARAM_CURRENT_PAGE, currentPage);//当前页码
        if(null != productType) {
            model.addAttribute("ptype", productType);//产品类型
        }
        return "loan";
    }
}
