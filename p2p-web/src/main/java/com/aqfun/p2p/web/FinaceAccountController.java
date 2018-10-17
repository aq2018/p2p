package com.aqfun.p2p.web;

import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.model.user.FinanceAccount;
import com.aqfun.p2p.model.user.User;
import com.aqfun.p2p.service.user.FinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Package : com.aqfun.p2p.web
 * ClassName : FinaceAccountController
 *
 * @author aq
 * @date 2018/9/8
 */
@Controller
public class FinaceAccountController {
    @Autowired
    private FinanceAccountService financeAccountService;

    @GetMapping("/loan/myMoney")
    @ResponseBody
    public FinanceAccount loadStat (HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        FinanceAccount financeAccount = financeAccountService.queryFinanceAccountByUid(user.getId());
        return financeAccount;
    }
}
