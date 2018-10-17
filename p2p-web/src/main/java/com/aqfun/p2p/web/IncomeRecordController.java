package com.aqfun.p2p.web;

import com.aqfun.p2p.service.loan.IncomeRecordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Package : com.aqfun.p2p.web
 * ClassName : IncomeRecordController
 *
 * @author aq
 * @date 2018/9/9
 */
@Controller
public class IncomeRecordController {
    @Autowired
    private IncomeRecordService incomeRecordService;


    @RequestMapping("/loan/myIncome")
    public String myIncome () {

        return "myIncome";
    }

}
