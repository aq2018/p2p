package com.aqfun.p2p.consts;

/**
 * 静态常量
 * Package : com.aqfun.p2p
 * ClassName : Const
 * User : aq
 * Date : 2018/9/5
 */
public class Const {
    /**
     * 历史年化收益率:historyAverageRate
     */
    public static final String HISTORY_AVERAGE_RATE = "historyAverageRate";
    /**
     * 平台用户数:allUserCount
     */
    public static final String ALL_USER_COUNT = "allUserCount";
    /**
     * 累计成交额:allBidMoney
     */
    public static final String ALL_BID_MONEY = "allBidMoney";

    /**
     * 验证码:captcha
     */
    public static final String SESSION_CAPTCHA = "captcha";
    /**
     * ok
     */
    public static final String OK = "ok";

    /**
     * redis全局自增变量
     */
    public static final String ONLY_NUM = "1";

    /**
     * 分页页码:默认9
     */
    public static int PAGE_SIZE = 9;

    /**
     * 用户session
     */
    public static final String SESSION_USER = "user";
    /**
     * errorMessage
     */
    public static String ERROR_MESSAGE = "errorMessage";

    /**
     * 失败标识
     */
    public static final String Fail = "0";

    /**
     * 成功标识
     */
    public static final String SUCCESS = "1";
    /**
     * 投资记录中投资列表分页页码
     */
    public static int BIDINFO_PAGE_SIZE = 5;
    /**
     * 分页页码大小变量标识
     */
    public static String PARAM_PAGE_SIZE = "pageSize";
    /**
     * 分页页码变量标识
     */
    public static String PARAM_CURRENT_PAGE = "currentPage";

    /**
     * 新手宝类型:0
     */
    public static Integer PRODUCT_TYPE_X = 0;
    /**
     * 优选类型:1
     */
    public static Integer PRODUCT_TYPE_U = 1;
    /**
     * 散标类型:2
     */
    public static Integer PRODUCT_TYPE_S = 2;

    /**
     * 充值失败响应的变量名
     */
    public static String TRADE_MSG = "trade_msg";

    /**
     * 用户投资排行榜
     */
    public static final String INVEST_TOP = "investTop";
}
