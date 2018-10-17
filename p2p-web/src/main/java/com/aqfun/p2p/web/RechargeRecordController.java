package com.aqfun.p2p.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aqfun.p2p.utils.HttpClientUtil;
import com.aqfun.p2p.consts.Const;
import com.aqfun.p2p.model.loan.RechargeRecord;
import com.aqfun.p2p.model.user.User;
import com.aqfun.p2p.model.vo.PaginationVO;
import com.aqfun.p2p.service.loan.OnlyNumService;
import com.aqfun.p2p.service.loan.RechargeRecordService;
import com.aqfun.p2p.utils.DateUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Package : com.aqfun.p2p.web
 * ClassName : rechargeRecordController
 *
 * @author aq
 * @date 2018/9/9
 */
@Controller
public class RechargeRecordController {
    @Autowired
    private RechargeRecordService rechargeRecordService;
    @Autowired
    private OnlyNumService onlyNumService;

    @RequestMapping("/loan/myRecharge")
    public String myRecharge (HttpServletRequest request,
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

        PaginationVO<RechargeRecord> paginationVO = rechargeRecordService.queryRechargeRecordListByPage(paramMap);
        //总记录数
        int totalRows = paginationVO.getTotal().intValue();

        int totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows/ pageSize + 1;
        model.addAttribute("totalRows",totalRows);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("rechargeRecordList",paginationVO.getDataList());
        model.addAttribute("currentPage",currentPage);
        return "myRecharge";
    }

    @RequestMapping("/loan/toAlipayRecharge")
    public String toAlipayRecharge(HttpServletRequest request,Model model,
                                   @RequestParam(value = "rechargeMoney",required = true) Double rechargeMoney) {
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        //生成订单编号=时间戳+redis全局唯一数字
        String rechargeNo = DateUtil.getTimeStamp() + onlyNumService.getOnlyNumber();

        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setRechargeNo(rechargeNo);
        rechargeRecord.setRechargeMoney(rechargeMoney);
        rechargeRecord.setRechargeStatus("0");
        rechargeRecord.setRechargeTime(new Date());
        rechargeRecord.setRechargeDesc("支付宝充值");
        rechargeRecord.setUid(user.getId());
        boolean success = rechargeRecordService.addRechargeRecord(rechargeRecord);
        if (success) {
            //调用pay申请支付宝api
            model.addAttribute("rechargeNo",rechargeNo);
            model.addAttribute("rechargeMoney",rechargeMoney);
            model.addAttribute("alipay_pay_url","http://localhost:9090/pay/toAlipay");
        } else {
            model.addAttribute(Const.TRADE_MSG,"充值失败,请稍后重试...");
            return "toRechargeBack";
        }

        return "toAlipay";
    }

    @RequestMapping("/loan/alipayBack")
    public String alipayBack(HttpServletRequest request,Model model,
            @RequestParam(value = "out_trade_no",required = true) String rechargeNo,
            @RequestParam(value = "total_amount",required = true) Double rechargeMoney,
            @RequestParam(value = "signVerified",required = true) String signVerified) {
        System.out.println(request);

        //验证充值签名是否成功
        if (Const.SUCCESS.equals(signVerified)) { //充值成功
            //请求pay工程的query查询订单
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("out_trade_no",rechargeNo);
            String jsonString = HttpClientUtil.doPost("http://localhost:9090/pay/api/aliQuery",paramMap);
            System.out.println(request);
            /**
             *
             * {
             "alipay_trade_query_response": {
             "code": "10000",
             "msg": "Success",
             "buyer_logon_id": "ija***@sandbox.com",
             "buyer_pay_amount": "0.00",
             "buyer_user_id": "2088102176495900",
             "buyer_user_type": "PRIVATE",
             "invoice_amount": "0.00",
             "out_trade_no": "201809112326591",
             "point_amount": "0.00",
             "receipt_amount": "0.00",
             "send_pay_date": "2018-09-11 23:28:45",
             "total_amount": "2.00",
             "trade_no": "2018091121001004900500221830",
             "trade_status": "TRADE_SUCCESS"//支付成功
             },
             "sign": "J9Ivqf7d+SidJ9lKFBYb+L6jOlGh3duYswU9OmS1pYQVKhQyypi1iBZqyV5aLAyrM8FN1TurSj4q3Sz7TaKqx9Z5nQDqasryodYC5Wy9wAc/iqNFWdHF+MwTSfe1DQqEYvB/+S6jSTk3Z9jwIuGIvQkyazpCAWhkao0yTPBNVSZoHA3mhHTOjAxEaq3h1AHHz2d4CNRY9yCaQ3/c2o0XCy9oxCkM4R3MqKKV1/tk4/no+YIHFtHdYk1I4N67H9gr70H+fdgK0Aqy0JqA6EBLm0rlcTvZOPfukkYsQHMD+KUjIXQ7stC52uW+0ZKgMeQK0gH/+GixrTM402Tzk2HlLQ=="
             }
             *
             */
            JSONObject resJsonObj = JSON.parseObject(jsonString).getJSONObject("alipay_trade_query_response");

            //通信成功
            String successCode = "10000";
            if(successCode.equals(resJsonObj.getString("code"))){

                //交易成功标识
                String tradeSuccess = "TRADE_SUCCESS";

                //交易成功
                if(tradeSuccess.equals(resJsonObj.getString("trade_status"))){

                    //更新充值状态
                    RechargeRecord updateRecharge = new RechargeRecord();
                    updateRecharge.setRechargeNo(rechargeNo);
                    updateRecharge.setRechargeStatus("1");
                    boolean updateRechargeSuccess = rechargeRecordService.modifyRechargeRecordByRechargeNo(updateRecharge);
                    if (updateRechargeSuccess) {
                        return "redirect:/loan/myCenter";
                    }
                }
            }
        }

        model.addAttribute(Const.TRADE_MSG,"充值失败,请稍后重试...");
        return "toRechargeBack";
    }

    @RequestMapping("/loan/toWeixinRecharge")
    public String toWeixinRecharge(HttpServletRequest request,Model model,
                                   @RequestParam(value = "rechargeMoney") double rechargeMoney){
        User user = (User) request.getSession().getAttribute(Const.SESSION_USER);
        //生成订单号
        String rechargeNo = DateUtil.getTimeStamp() + onlyNumService.getOnlyNumber();
        RechargeRecord rechargeRecord = new RechargeRecord();
        rechargeRecord.setRechargeNo(rechargeNo);
        rechargeRecord.setUid(user.getId());
        rechargeRecord.setRechargeStatus("0");
        rechargeRecord.setRechargeDesc("微信充值");
        rechargeRecord.setRechargeMoney(rechargeMoney);
        rechargeRecord.setRechargeTime(new Date());
        boolean addRechargeFlag = rechargeRecordService.addRechargeRecord(rechargeRecord);
        if (addRechargeFlag) {
            model.addAttribute("body","p2p理财支付");
            model.addAttribute("rechargeNo",rechargeNo);
            model.addAttribute("rechargeMoney",rechargeMoney);
            return "showQR";
            //model.addAttribute("wxpay_pay_url","http://localhost:9090/pay/api/toWxpay");
        } else {
            model.addAttribute(Const.TRADE_MSG,"充值失败,请稍后重试...");
            return "toRechargeBack";
        }
    }


    @RequestMapping("/loan/showQRCode")
    public void showQRCode(HttpServletRequest request,HttpServletResponse response,
                           @RequestParam(value="body",required=true) String body,
                           @RequestParam (value="rechargeNo",required=true) String rechargeNo,
                           @RequestParam (value="rechargeMoney",required=true) double rechargeMoney) throws ServletException, IOException, WriterException {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("body",body);
        paramMap.put("out_trade_no",rechargeNo);
        paramMap.put("total_fee",rechargeMoney);
        String jsonString = HttpClientUtil.doPost("http://localhost:9090/pay/api/toWxpay", paramMap);
        JSONObject jsonObject = JSON.parseObject(jsonString);

        //通信成功
        String success = "SUCCESS";
        if (success.equals(jsonObject.getString("result_code"))) {

            //获取返回二维码链接
            String codeUrl = jsonObject.getString("code_url");
            Map<EncodeHintType,Object> hintType = new HashMap<>();
            hintType.put(EncodeHintType.CHARACTER_SET,"utf-8");

            //创建矩阵对象生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,200,200);

            OutputStream out = response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"jpg",out);
            out.flush();
            out.close();

        } else {
            request.setAttribute(Const.TRADE_MSG,"充值失败,请稍后重试...");
            request.getRequestDispatcher(request.getServletPath() + "/toRechargeBack.jsp").forward(request, response);

        }
    }

}
