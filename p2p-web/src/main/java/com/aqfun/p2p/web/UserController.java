package com.aqfun.p2p.web;

import com.alibaba.fastjson.JSONObject;
import com.aqfun.p2p.config.Config;
import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.model.loan.BidInfo;
import com.aqfun.p2p.model.loan.IncomeRecord;
import com.aqfun.p2p.model.loan.RechargeRecord;
import com.aqfun.p2p.model.user.FinanceAccount;
import com.aqfun.p2p.model.user.User;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.model.vo.ResultObj;
import com.aqfun.p2p.service.loan.*;
import com.aqfun.p2p.service.user.FinanceAccountService;
import com.aqfun.p2p.service.user.UserService;
import com.aqfun.p2p.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

/**
 * 用户控制器
 * ClassName : UserController
 *
 * @author aq
 * @date 2018/9/6
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoanInfoService loanInfoService;
    @Autowired
    private BidInfoService bidInfoService;
    @Autowired
    private FinanceAccountService financeAccountService;
    @Autowired
    private RechargeRecordService rechargeRecordService;
    @Autowired
    private IncomeRecordService incomeRecordService;
    @Autowired
    private Config config;

    @RequestMapping("/loan/checkPhone")
    @ResponseBody
    public Object checkPhone(HttpServletRequest request,
                             @RequestParam(value = "phone",required = true) String phone) {
        Map<String,Object> retMap = new HashMap<>();
        User user = userService.queryUserByPhone(phone);
        if (null == user) {
            retMap.put(Const.ERROR_MESSAGE, Const.OK);
            return retMap;
        }
        retMap.put(Const.ERROR_MESSAGE,"该手机号已被注册");
        return retMap;
    }

    @RequestMapping("/loan/checkCaptcha")
    @ResponseBody
    public Object checkCaptcha(HttpServletRequest request,
                             @RequestParam(value = "captcha",required = true) String captcha) {
        Map<String,Object> retMap = new HashMap<>();
        String session_captcha = (String) request.getSession().getAttribute(Const.SESSION_CAPTCHA);
        if(session_captcha.equalsIgnoreCase(captcha)){
            retMap.put(Const.ERROR_MESSAGE, Const.OK);
            return retMap;
        }
        retMap.put(Const.ERROR_MESSAGE,"验证码错误");
        return retMap;
    }

    @RequestMapping(value = "/loan/register",method = RequestMethod.POST)
    @ResponseBody
    public Object register(HttpServletRequest request,
                           @RequestParam(value = "phone",required = true) String phone,
                           @RequestParam(value = "loginPassword",required = true) String loginPassword,
                           @RequestParam(value = "replayLoginPassword",required = true) String replayLoginPassword,
                           @RequestParam(value = "captcha",required = true) String captcha) {
        Map<String,Object> retMap = new HashMap<>();
        if (StringUtils.isEmpty(phone)) {
            retMap.put(Const.ERROR_MESSAGE,"请输入手机号");
            return retMap;
        }

        if(!Pattern.matches("^1[1-9]\\d{9}$",phone)){
            retMap.put(Const.ERROR_MESSAGE,"请输入正确的手机号");
            return retMap;
        }

        if (StringUtils.isEmpty(loginPassword)) {
            retMap.put(Const.ERROR_MESSAGE,"请输入登录密码");
            return retMap;
        }


        if(!StringUtils.equals(loginPassword,replayLoginPassword)){
            retMap.put(Const.ERROR_MESSAGE,"两次输入密码不一致");
            return retMap;
        }

        if (StringUtils.isEmpty(captcha)) {
            retMap.put(Const.ERROR_MESSAGE,"请输入验证码");
            return retMap;
        }

        String session_captcha = (String) request.getSession().getAttribute(Const.SESSION_CAPTCHA);
        if(!StringUtils.equalsIgnoreCase(session_captcha,captcha)){
            retMap.put(Const.ERROR_MESSAGE,"验证码错误");
            return retMap;
        }

        //用户注册(手机号,用户密码)
        ResultObj resultObj = userService.register(phone,loginPassword);
        if("1".equals(resultObj.getErrCode())){//注册失败
            retMap.put(Const.ERROR_MESSAGE,resultObj.getErrMessage());
            return retMap;
        }else{//注册成功 -- 将用户信息放到session中
            User userSession = userService.queryUserByPhone(phone);
            request.getSession().setAttribute(Const.SESSION_USER,userSession);
            retMap.put(Const.ERROR_MESSAGE, Const.OK);
        }

        return retMap;
    }

    @GetMapping("/loan/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/index";
    }

    @PostMapping("/loan/login")
    @ResponseBody
    public Object login(HttpServletRequest request,
                        @RequestParam(value = "phone",required = true)String phone,
                        @RequestParam(value = "loginPassword",required = true) String loginPassword,
                        Model model) {
        Map<String,Object> retMap = new HashMap<>();

        if (StringUtils.isEmpty(phone)){
            retMap.put(Const.ERROR_MESSAGE,"请输入手机号");
            return retMap;
        }

        if (StringUtils.isEmpty(loginPassword)){
            retMap.put(Const.ERROR_MESSAGE,"请输入密码");
            return retMap;
        }
        User loginUser = userService.loginByPhoneAndLoginPassword(phone, loginPassword);
        if (null != loginUser){
            retMap.put(Const.ERROR_MESSAGE,Const.OK);
            request.getSession().setAttribute(Const.SESSION_USER,loginUser);
            return retMap;
        }
        retMap.put(Const.ERROR_MESSAGE,"用户名或密码错误");
        return retMap;
    }

    @PostMapping("/loan/verifyRealName")
    @ResponseBody
    public Object verifyRealName(HttpServletRequest request,
                                 @RequestParam(value = "realName",required = true) String realName,
                                 @RequestParam(value = "idCard",required = true) String idCard,
                                 @RequestParam(value = "replayIdCard",required = false)String replayIdCard){
        Map<String,Object> retMap = new HashMap<>();
        if(StringUtils.isEmpty(realName)){
            retMap.put(Const.ERROR_MESSAGE,"请输入姓名");
            return  retMap;
        }
        if(!Pattern.matches("[\\u4e00-\\u9fa5]+",realName)){
            retMap.put("errorMessage", "真实姓名只支持中文");
            return retMap;
        }
        if(StringUtils.isEmpty(idCard)){
            retMap.put(Const.ERROR_MESSAGE,"请输入身份证号");
            return  retMap;
        }
        if(!Pattern.matches("(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)",idCard)){
            retMap.put(Const.ERROR_MESSAGE,"请输入正确的身份证号码");
            return  retMap;
        }
        if(null != replayIdCard && !"".equals(replayIdCard) && !idCard.equals(replayIdCard)){
            retMap.put(Const.ERROR_MESSAGE,"两次输入身份证号码不一致");
            return  retMap;
        }
        //https://way.jd.com/freedt/api_rest_police_identity接口参数
        //name=您的姓名&idCard=您的身份证号&appkey=
        Map<String,Object> paramMap = new ConcurrentHashMap<>();
        paramMap.put("realName",realName);
        paramMap.put("cardNo",idCard);
        paramMap.put("appkey", config.getRealNameAppKey());//实名认证:appKey

        String resultJson = HttpClientUtil.doPost(config.getRealNameUrl(), paramMap);
        System.out.println(resultJson);
        //api接口返回参数
        //String resultJson = "{\"code\":\"10000\",\"charge\":false,\"msg\":\"查询成功\",\"result\":{\"error_code\":0,\"reason\":\"成功\",\"result\":{\"realname\":\"乐天磊\",\"idcard\":\"350721197702134399\",\"isok\":true}}}";
        JSONObject jsonObj = JSONObject.parseObject(resultJson);
        String code = jsonObj.getString("code");
        //请求远程api成功
        if(StringUtils.equals(code,"10000")){
            boolean isOk = jsonObj.getJSONObject("result").getJSONObject("result").getBoolean("isok");
            System.out.println("================= >>"+isOk);
            if(isOk){
                //更新用户实名信息,并存放到session
                User sessionUser = (User) request.getSession().getAttribute(Const.SESSION_USER);
                sessionUser.setIdCard(idCard);
                sessionUser.setName(realName);
                int modifyCount = userService.modifyUserById(sessionUser);
                if (modifyCount == 1) {
                    request.getSession().setAttribute(Const.SESSION_USER,userService.queryUserByPhone(sessionUser.getPhone()));
                    retMap.put(Const.ERROR_MESSAGE,Const.OK);
                    return retMap;
                }
            }
        }
        retMap.put(Const.ERROR_MESSAGE,"实名认证失败,请重新输入");
        return retMap;
    }

    //静态模拟实名认证api
    @PostMapping("/loan/verifyRealNameTest")
    @ResponseBody
    public Object verifyRealNameTest(HttpServletRequest request,
                         @RequestParam(value = "appkey") String appkey,
                         @RequestParam(value = "realName") String realName,
                         @RequestParam(value = "cardNo") String cardNo){
        /**
         *模拟京东万象实名api
         * {
             "code": 1000,
             "result": {
                 "result": {
                     "isok": true,
                     "realName": "张三",
                     "cardNo": "1223456"
                }
            }
         }
         */
        Map<String,String> realNameMap = new HashMap<>();
        realNameMap.put("张三","111111111111111111");
        realNameMap.put("李四","111111111111111112");
        realNameMap.put("王五","111111111111111113");
        realNameMap.put("赵六","111111111111111114");

        Map<String,Object> retMap = new HashMap<>();

        Map<String,Object> map1 = new HashMap<>();
        retMap.put("result",map1);

        Map<String,Object> map2 = new HashMap<>();
        map1.put("result",map2);

        if(!"ff290ab5e11a86d8fbcd2b92dae85ec2".equals(appkey)){
            retMap.put("code","10001");//通信失败
            return retMap;
        }
        retMap.put("code","10000");
        if(realNameMap.containsKey(realName)){
            String cardId = realNameMap.get(realName);
            if(cardId.equals(cardNo)){
                map2.put("isok",true);
                map2.put("realName",realName);
                map2.put("cardNo",cardNo);
                return retMap;
            }
        }
            //不匹配
        map2.put("isok",false);
        return retMap;
    }

    @GetMapping("/loan/loadStat")
    @ResponseBody
    public Map<String,Object> loadStat () {
        Map<String,Object> map = new HashMap<>();
        //历史年化收益率
        Double historyAverageRate = loanInfoService.queryHistoryAverageRate();
        //平台用户数
        Integer allUserCount = userService.queryAllUserCount();
        //累计成交额
        Double allBidMoney = bidInfoService.queryAllBidMoney();
        map.put("historyAverageRate",historyAverageRate);
        map.put("allUserCount",allUserCount);
        map.put("allBidMoney",allBidMoney);
        return map;
    }

    @RequestMapping("/loan/myCenter")
    public String myCenter (HttpServletRequest request, Model model) {
        //可用余额
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        FinanceAccount financeAccount = financeAccountService.queryFinanceAccountByUid(user.getId());

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put(Const.PARAM_CURRENT_PAGE,0);
        paramMap.put(Const.PARAM_PAGE_SIZE,3);

        //最近投资记录
        paramMap.put("uid",user.getId());
        PaginationVO<BidInfo> bidInfoPaginationVO = bidInfoService.queryUserBidListByPage(paramMap);
        //最近充值记录
        PaginationVO<RechargeRecord> rechargeRecordPaginationVO = rechargeRecordService.queryRechargeRecordListByPage(paramMap);
        //最近收益记录
        PaginationVO<IncomeRecord> incomeRecordPaginationVO = incomeRecordService.queryIncomeRecordListByPage(paramMap);

        model.addAttribute("availableMoney",financeAccount.getAvailableMoney());
        model.addAttribute("bidInfoList",bidInfoPaginationVO.getDataList());
        model.addAttribute("rechargeRecordList",rechargeRecordPaginationVO.getDataList());
        model.addAttribute("incomeRecordList",incomeRecordPaginationVO.getDataList());
        return "myCenter";
    }

    @RequestMapping("login")
    public String loginRedirect(){
        return "login";
    }

    @RequestMapping("register")
    public String registerRedirect(){
        return "register";
    }

    @RequestMapping("loan/realName")
    public String realNameRedirect(){
        return "realName";
    }
}
