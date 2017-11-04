package wego.com.util;

import android.os.Environment;

public class Constants {

    public static final String APP_NAME = "Duomiwan";
    public static final String NET_ERROR = "当前网络不可用，请检查你的网络设置";

    /**
     * 服务器地址
     * test2.duomiwan.com
     */
//    public static final String BASE_URL = "http://test2.duomiwan.com";
    public static final String BASE_URL = "http://app.duomiwan.com";
    public static final String BASE_URL1 = "http://app.duomiwan.com";

    //测试地址2
//    public static final String BASE_URL2 = "http://120.24.157.6:9009/duomiwan-server";
//    public static final String BASE_URL2 = "http://test2.duomiwan.com";
    public static final String BASE_URL2 = "http://app.duomiwan.com";
    /**
     * 响应成功码
     */
    public static final String RESPONSE_SUCCESS = "APP.000";

    /**
     * 广告图比例
     */
    public static final float BANNER_RATIO = 1080 / 300;

    /**
     * 临时文件路径
     */
    public static final String FILE_PATH_TEMP_DIR = Environment.getExternalStorageDirectory() + "/" + APP_NAME + "/Temp";

    /**
     * 开屏广告图文件路径
     */
    public static final String FILE_PATH_ADVERT_DIR = Environment.getExternalStorageDirectory() + "/" + APP_NAME + "/Advert";

    /**
     * 下载文件路径
     */
    public static final String FILE_PATH_DOWNLOAD_DIR = Environment.getExternalStorageDirectory() + "/" + APP_NAME + "/Download";

    /**
     * 个人信息
     */
    public static final String USER_INFO = BASE_URL + "/ums/loadUserInfo";

    /**
     * 热门游戏
     */
    public static final String HOT_GAME = BASE_URL + "/game/queryHotGames";

    /**
     * 推荐游戏
     */
    public static final String RECOMMEND_GAME = BASE_URL + "/game/queryRecommendGames";

    /**
     * 所有游戏
     */
    public static final String ALL_GAME = BASE_URL + "/game/queryGames";

    /**
     * 全部游戏筛选
     */
    public static final String ALL_GAME_TYPE = BASE_URL + "/game/queryGameTypes";

    /**
     * 游戏动作
     */
    public static final String ACTIONS_GAME = BASE_URL + "/game/actionGames";
    /**
     * 查询玩家游戏
     */
    public static final String USER_GAME = BASE_URL + "/game/queryUserGames";
    /**
     * 开服表
     */
    public static final String GAMEBYSERVER = BASE_URL + "/game/queryGamesByServer";
    /**
     * 搜索游戏（模糊搜索）
     */
    public static final String QueryGamesByName = BASE_URL + "/game/queryGamesByNamePattern";

    /**
     * 热门礼包
     */
    public static final int HOT = 1;

    /**
     * 独家礼包
     */
    public static final String SINGLE = "独家礼包";

    /**
     * 本周上新
     */
    public static final int WEEK = 3;
    /**
     * 查询礼包
     */
    public static final String GIFTCENTER = BASE_URL + "/gift/queryGift";

    /**
     * 在玩的游戏
     */
    public static final String MYGAMESGIFT = BASE_URL + "/gift/queryMyGameGift";

    /**
     * 礼包详情
     */
    public static final String GameGiftDetail = BASE_URL + "/gift/getGameGiftDetail";

    /**
     * 领取礼包
     */
    public static final String reveiveGift = BASE_URL + "/gift/receiveGift";

    /**
     * 礼包banner
     */
    public static final String GIFT_BANNER = BASE_URL + "/activity/bannerList";


    /**
     * 登录
     */
    //    public static final String LOGIN = "http://120.24.157.6:9002/muzhike/u/login.shtml";
//    public static final String LOGIN = "http://sdk.duomiwan.com/u/login.shtml";
    public static final String LOGIN = BASE_URL + "/ums/login";

    /**
     * 登录校验
     */
    public static final String CODE = BASE_URL + "/common/getDynamicCode";

    /**
     * 注册
     */
    //    public static final String REGISTER = "http://120.24.157.6:9002/muzhike/u/subRegister.shtml";
    public static final String REGISTER = BASE_URL + "/ums/register";

    /**
     * 修改用户信息
     */
    public static final String UPDATE_USER_INFO = BASE_URL + "/ums/updateUserInfo";

    /**
     * 绑定类型
     */
    public static final int TYPE_PHONE = 1;//手机
    public static final int TYPE_EMAIL = 2;//邮箱
    public static final int TYPE_QQ = 3;//qq
    public static final int TYPE_SINA = 4;//微博
    public static final int TYPE_WECHAT = 5;//微信
    /**
     * 修改密码
     */
    public static final String UPDPWD = BASE_URL + "/ums/updPwd";

    /**
     * 第三方账号和现有账号关联预校验
     */
    public static final String THIRDPREBINDCHECK = BASE_URL + "/third/preBindCheck";

    /**
     * 绑定第三方
     */
    public static final String BIND_3RD_PLATFORM = BASE_URL + "/third/bind";

    /**
     * 解绑第三方
     */
    public static final String UNBIND_3RD_PLATFORM = BASE_URL + "/third/unbind";

    /**
     * 实名认证
     */
    public static final String NEEDCERTIFICATE = BASE_URL + "/third/need2Certificate";

    /**
     * 是否已设置密码
     */
    public static final String HASSETTLEDPWD = BASE_URL + "/third/hasSettledPwd";

    /**
     * 设置密码
     */
    public static final String SETTLEDPWD = BASE_URL + "/third/settlePwd";

    /**
     * 加关注
     */
    public static final String FOLLOW = BASE_URL + "/ums/follow";

    /**
     * 取消关注
     */
    public static final String CANCEL_FOLLOW = BASE_URL + "/ums/cancelFollow";

    /**
     * 账户安全
     */
    public static final String ACCOUNT_SECURITY = BASE_URL + "/ums/myAccountSecurity";

    /**
     * 更新密码
     */
    public static final String UPDATE_PASSWORD = BASE_URL + "/ums/updatePwd";
    /**
     * 查询好友
     */
    public static final String CQUERY_FRIENDS = BASE_URL + "/ums/queryFriends";
    /**
     * 关注查询
     */
    public static final String CQUERY_FOLLOWS = BASE_URL + "/ums/queryFollows";
    /**
     * 粉丝查询
     */
    public static final String CQUERY_FANS = BASE_URL + "/ums/queryFans";

    /**
     * 发布动态
     */
    public static final String PUBLISH_DYNAMIC = BASE_URL + "/topic/publishTopic";

    /**
     * 查询全部游戏
     */
    public static final String QUERYGAMES = BASE_URL + "/game/queryGames";

    /**
     * 首页轮播图
     */
    public static final String BANNER_HOME = BASE_URL + "/activity/bannerList";
    /**
     * 我的礼包
     */
    public static final String MY_GIFT = BASE_URL + "/gift/myGift";
    /**
     * 我的圈子
     */
    public static final String QUERY_MYGAME_RING = BASE_URL + "/game/queryMyGameRing";
    /**
     * 查询我的收藏
     */
    public static final String QUERY_MYCOLLECT = BASE_URL + "/topic/queryMyCollectedTopic";

    /**
     * 查询头条
     */
    public static final String QUERY_HEADLINE = BASE_URL + "/topic/queryHotHeadTitles";

    /**
     * 查询消息
     */
    public static final String QUERY_MESSAGE = BASE_URL + "/message/queryMessage";

    //获取系统消息详情
    public static final String GET_SYS_MSG_DETAIL = BASE_URL + "/message/getSysMsgDetail";

    /**
     * 搜索好友
     */
    public static final String SEARCH_FRIENDS = BASE_URL + "/search/searchFriends";
    /**
     * 我的钱包
     */
    public static final String MYWALLET = BASE_URL + "/txn/myWallet";
    /**
     * 米卷明细
     */
    public static final String USER_VOUCHER_RECORD = BASE_URL + "/txn/userVoucherRecord";
    /**
     * 米币明细
     */
    public static final String MZB_RECORD = BASE_URL + "/txn/userMzbRecord";
    /**
     * 已打赏
     */
    public static final String USER_TIPLIST = BASE_URL + "/txn/userTipList";
    /**
     * 收到的打赏
     */
    public static final String USER_TIPLIST_RECEIVE = BASE_URL + "/txn/userTipReceivedList";
    /**
     * 查询热门动态
     */
    public static final String QUERY_HOTTOPICS = BASE_URL + "/topic/queryHotTopics";

    /**
     * 对某条动态进行的一些操作
     */
    public static final String ACTIONTOPIC = BASE_URL + "/topic/actionTopic";

    /**
     * 启动页广告图片
     */
    public static final String STARTIMAGE = BASE_URL + "/activity/startImage";

    /**
     * 获取最新的app版本信息
     */
    public static final String GET_LATESTD_APP = BASE_URL + "/app/getLastedApp";


    /**
     * 通过昵称搜索用户
     */
    public static final String SEARCH_BY_NICKNAME = BASE_URL + "/search/searchByNickName";

    /**
     * 查询动态
     */
    public static final String QUERY_TOPICS = BASE_URL + "/topic/queryTopics";
    /**
     * 我的动态
     */
    public static final String QUERY_MYTOPICS = BASE_URL + "/topic/queryMyTopics";

    /**
     * 米币web充值页面
     */
//    public static final String PREORDER = BASE_URL + "/pay/preOrder/{0}";
    public static final String PREORDER = BASE_URL + "/pay/preOrder";

    /**
     * 余额web充值页面
     */
//    public static final String PREORDER = BASE_URL + "/pay/preOrder/{0}";
    public static final String PRECASHORDER = BASE_URL + "/pay/ preCashOrder";

    /**
     * 查询自定义Tab（我喜欢的圈子和我不喜欢的）
     */
    public static final String QUERY_MY_GAMES = BASE_URL + "/game/queryMyGames";

    /**
     * editGameToMyLiking
     */
    public static final String EDIT_GAME_TO_MY_LIKING = BASE_URL + "/game/editGameToMyLiking";


    public static final String CAN_TALK = BASE_URL + "/common/canTalk";

    /**
     * 实名认证
     */
    public static final String THIRDCERTIFICATE = BASE_URL + "/third/certificate";
    /**
     * 第三方账号是否已使用
     */
    public static final String HASUSEDT_HIRDACCOUNT = BASE_URL + "/third/thirdAccountHasUsed";

    /**
     * 第三方账号登录
     */
    public static final String THIRD_LOGIN = BASE_URL + "/third/login";
    /**
     * 获取邮箱验证码
     */
    public static final String BINDEMAIL = BASE_URL + "/common/sendEmail";
    /**
     * 能否找回密码
     */
    public static final String CANFINDPWD = BASE_URL + "/ums/canFindPwd";
    /**
     * 根据条件筛选冬天
     */
    public static final String GET_MIRINGDYNAMICLIST_BY_FIL = BASE_URL + "/topic/queryTopics";


    /**
     * 获取其他账号用户的信息
     */
    public static final String GET_OTHER_TOPICS = Constants.BASE_URL + "/topic/queryOtherTopics";

    /**
     * 对当前用户的一些操作
     */

    public static final String ACTIONUSER = Constants.BASE_URL + "/ums/queryUserTopicAction";

    /**
     * 获取游戏详情
     */
    public static final String GET_GAME_DETAIL = Constants.BASE_URL + "/game/getGameDetail";

    /**
     * 获取动态详情
     */
    public static final String GET_TOPIC_DETAIL1 = Constants.BASE_URL + "/topic/getTopicDetail1";

    public static final String GET_TOPIC_DETAIL2 = Constants.BASE_URL + "/topic/getTopicDetail2";


    /**
     * 打赏
     */
    public static final String USER_TIP = Constants.BASE_URL + "/txn/userTip";


    /**
     * 获取其他用户信息
     */
    public static final String LOAD_OTHER_USER_INFO = Constants.BASE_URL + "/ums/loadOtherUserInfo";


    /**
     * 是否关注当前用户
     */
    public static final String ISFOLLOW_USER = Constants.BASE_URL + "/search/isFollow";


    /**
     * 发表评论
     */

    public static final String PUBLISHCOMMENT = Constants.BASE_URL + "/comment/publishComment";

    /**
     * 回复
     */
    public static final String REPLYCOMMENT = Constants.BASE_URL + "/comment/replyComment";

    /**
     * 游戏交易汇总
     */
    public static final String GET_GAMETXNSUMMARY = Constants.BASE_URL + "/txn/getGameTxnSummary";

    /**
     * 获取最近成功的交易
     */
    public static final String GET_LASTEDTXNACCOUNT = Constants.BASE_URL + "/txn/getLastedTxnAccount";

    /**
     * 获取交易动态
     */
    public static final String GET_TXNDYNAMICS_BY_FILTER = Constants.BASE_URL + "/txn/getTxnDynamics";


    /**
     * 获取可回收的官方游戏
     */
    public static final String GET_OFFICIALCANRETURNGAMES = Constants.BASE_URL + "/txn/getOfficialCanReturnGames";

    /**
     * 获取官方回收详情
     */
    public static final String RETURN_2OFFICIALDETAIL = Constants.BASE_URL + "/txn/return2OfficialDetail";

    /**
     * 获取可交易的游戏列表
     */
    public static final String GET_TXNGAMELIST = Constants.BASE_URL + "/txn/txnGameList";

    /**
     * 获取交易子账号详情
     */
    public static final String GET_TXNACCTDETAIL = Constants.BASE_URL + "/txn/getTxnAcctDetail";


    /**
     * 能否获取密码
     */
    public static final String CAN_FIND_PWD = Constants.BASE_URL + "/ums/canFindPwd";


    /**
     * 获取我的竞拍
     */
    public static final String GET_MY_AUCTION = Constants.BASE_URL + "/txn/getMyActTxn";

    /**
     * 获取子账号列表
     */
    public static final String GET_GAMEACCOUNT = Constants.BASE_URL + "/account/getGameAccount";

    /**
     * 发布交易动态
     */
    public static final String PUBLISHTXNDYNAMIC = Constants.BASE_URL + "/txn/publishTxnDynamic";

    /**
     * 设置交易提醒
     */
    public static final String SETTLETXNWARNING = Constants.BASE_URL + "/txn/settleTxnWarning";

    /**
     * 交纳保证金
     */
    public static final String PAYMARGIN = Constants.BASE_URL + "/txn/payMargin";

    /**
     * 提现记录查询
     */
    public static final String QUERYWITHDRAWRECORDS = Constants.BASE_URL + "/txnQuery/queryWithdrawRecords";

    /**
     * 获取第三方账号
     */
    public static final String GETTHIRDACCTS = Constants.BASE_URL + "/account/getThirdAccts";

    /**
     * 设置默认账号
     */
    public static final String SETDEFAULTWITHDRAWACCT = Constants.BASE_URL + "/account/setDefaultWithdrawAcct";

    /**
     * 绑定第三方付款账号
     */
    public static final String BINDTHIRDACCOUNT = Constants.BASE_URL + "/account/bindThirdAccount";

    /**
     * 现金账户提现
     */
    public static final String CASHWITHDRAW = Constants.BASE_URL + "/pay/cashWithdraw";

    /**
     * 设置提现密码
     */

    public static final String SETTLEWITHDRAWPWD = Constants.BASE_URL + "/account/settleWithdrawPwd";

    /**
     * 修改提现密码
     */
    public static final String UPDATEWITHDRAWPWD = Constants.BASE_URL + "/account/updateWithdrawPwd";

    /**
     * 是否已设置提现密码
     */
    public static final String HASSETTLEDWITHDRAWPWD = Constants.BASE_URL + "/account/hasSettledWithdrawPwd";

    /**
     * 删除第三方账号
     */
    public static final String DELETETHIRDACCOUNT = Constants.BASE_URL + "/account/deleteThirdAccount";

    /**
     * 修改第三方账户
     */
    public static final String UPDATETHIRDACCOUNT = Constants.BASE_URL + "/account/updateThirdAccount";

    /**
     * 获取提现相关参数
     */
    public static final String GETWITHDRAWPARAMS = Constants.BASE_URL + "/account/getWithdrawParams";

    /**
     * 是否交过交易保证金
     */
    public static final String HASPAYMARGIN = Constants.BASE_URL + "/txn/hasPayMargin";

    /**
     * 官方回收子账号
     */
    public static final String RETURN2OFFICIAL = Constants.BASE_URL + "/txn/return2Official";

    /**
     * 查看出价记录
     */
    public static final String GETAUCTIONS = Constants.BASE_URL + "/txn/getAuctions";

    /**
     * 结束拍卖
     */
    public static final String FINISHAUCTION = Constants.BASE_URL + "/txn/finishAuction";

    /**
     * 删除竞拍
     */
    public static final String DELETEAUCTION = Constants.BASE_URL + "/txn/deleteAuction";

    /**
     * 再次出价
     */
    public static final String RESALE = Constants.BASE_URL + "/txn/reSale";

    /**
     * 获取验证码
     */
    public static final String GETDYNAMICCODE = Constants.BASE_URL + "/common/getDynamicCode";

    /**
     * 获取交易当前价
     */
    public static final String GETTXNCURRENTPRICE = Constants.BASE_URL + "/txn/getTxnCurrentPrice";


    //首页的游戏推广
    public static final String POP_GAME = Constants.BASE_URL + "/activity/popGame";

    //是否有聊天权限
    public static final String HAS_CHAT_PERMISSION = Constants.BASE_URL + "/cms/hasChatPermission";

    //获取随机客服
    public static final String GET_ACTIVED_CS = Constants.BASE_URL + "/cms/getActivedCs";

    //获取累计充值门槛
    public static final String GET_RECHARGE_THRESHOLD = Constants.BASE_URL + "/cms/getRechargeThreshold";

    /**
     * 加价、竞拍
     */
    public static final String PLUSPRICE = Constants.BASE_URL + "/txn/auctionAcct";

    /**
     * 
     */
    public static final String GET_ALL_CS_INFO = Constants.BASE_URL + "/cms/getAllCsInfo";

    //获取所有礼包类型
    public static final String GET_ALL_GIFT_TYPES = Constants.BASE_URL + "/gift/getAllGiftTypes";

    //账户余额明细
    public static final String QUERY_CASH_LIST = Constants.BASE_URL + "/txnQuery/queryCashList";

    public static final String BIND_DEVICE = Constants.BASE_URL + "/device/bindDevice";
}
