package com.aqfun.p2p.web;

import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.model.loan.BidInfo;
import com.aqfun.p2p.model.user.User;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.model.vo.ResultObj;
import com.aqfun.p2p.service.loan.BidInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 投资记录控制器
 * Package : com.aqfun.p2p.web
 * ClassName : BidInfoController
 *
 * @author aq
 * @date 2018/9/9
 */
@Controller
public class BidInfoController {
    @Autowired
    private BidInfoService bidInfoService;

    @PostMapping("/loan/invest")
    @ResponseBody
    public Object invest(HttpServletRequest request,
                         @RequestParam(value = "loanId") Integer loanId,
                         @RequestParam(value = "bidMoney") double bidMoney) {
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("uid",user.getId());
        paramMap.put("phone",user.getPhone());
        paramMap.put("loanId",loanId);
        paramMap.put("bidMoney",bidMoney);
        ResultObj resultObj = bidInfoService.modifyByUid(paramMap);

        Map<String,Object> retMap = new HashMap<>();
        if (StringUtils.equals(Const.SUCCESS,resultObj.getErrCode())){
            retMap.put(Const.ERROR_MESSAGE,Const.OK);
            return retMap;
        }

        retMap.put(Const.ERROR_MESSAGE,resultObj.getErrMessage());
        return  retMap;
    }

    @RequestMapping("/loan/myInvest")
    public String myInvest (HttpServletRequest request,
                            @RequestParam(value = "currentPage",required = false) Integer currentPage,
                            Model model) {
        if (null == currentPage) {
            currentPage = 1;
        }
        Integer pageSize = 3;
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put(Const.PARAM_CURRENT_PAGE,(currentPage - 1) *pageSize);
        paramMap.put(Const.PARAM_PAGE_SIZE,pageSize);
        paramMap.put("uid", user.getId());

        PaginationVO<BidInfo> paginationVO = bidInfoService.queryUserBidListByPage(paramMap);
        //总记录数
        int totalRows = paginationVO.getTotal().intValue();

        int totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows/ pageSize + 1;
        model.addAttribute("totalRows",totalRows);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("bidInfoList",paginationVO.getDataList());
        model.addAttribute(Const.PARAM_CURRENT_PAGE,currentPage);
        return "myInvest";
    }
}
