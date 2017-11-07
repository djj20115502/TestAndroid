package com.konka.testdemo;

import android.util.Log;

import com.konka.tvpay.data.builder.KonkaPayOrderBuilder;
import com.konka.tvpay.utils.MD5Util;
import com.konka.tvpay.utils.Signature;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DongJunJie on 2016-12-14.
 */

public class ModelOfTest {
    enum CpValue {
        //正式服务器可用
        Z001(CPID001, APPID001, KEY001),
        //正式服务器可用
        T002(CPID002, APPID002, KEY002),//正式服务器可用
        T003(CPID003, APPID003, KEY003),
        T004(CPID004, APPID004, KEY004),//正式服务器可用
        T005(CPID005, APPID005, KEY005);//正式服务器可用
        public String cpId;
        public String appId;
        public String key;

        CpValue(String cpId, String appId, String key) {
            this.cpId = cpId;
            this.appId = appId;
            this.key = key;
        }
    }


    /**
     * 正式服务器可用
     */
    public static final String CPID001 = "1000000000000004";
    public static final String APPID001 = "1000000000000005";
    public static final String KEY001 = "A62D96E909175B6DC578DEA964591C39";

    /**
     * 002
     */
    public static final String CPID002 = "1000000000000003";
    public static final String APPID002 = "1000000000000014";
    public static final String KEY002 = "18E53BD6238BB0892BEE02C655132ACA";
    /**
     * 003
     */
    public static final String CPID003 = "1000000000001178";
    public static final String APPID003 = "1000000000000239";
    public static final String KEY003 = "416068BA84CD6E6E82DE04A63E120707";


    /**
     * 004
     */
    public static final String CPID004 = "1000000000000012";
    public static final String APPID004 = "1000000000000019";
    public static final String KEY004 = "BD4C63C822E041F8D93FF3E65E5F2AF4";


    /**
     * 005辣椒
     */
    public static final String CPID005 = "1000000000000018";
    public static final String APPID005 = "1000000000000021";
    public static final String KEY005 = "D2A604BCF705D0BC944C0C93BE2827C8";
//    public static final String KEY004 = "0E4FDCB6CEE58678AFED645D9A0AD4F0";
//    public static final String KEY004 = "30CFA1296C485A8E1C75BCAB48B44BCC";

    CpValue cpValue = CpValue.T005;


    String notify_url = "http://test.kkapp.com/kkpayserver/NotifyTest/receiveInfo.do";
    String Client_KEY;
    public Map<String, String> defaultRequestdata = new HashMap<String, String>();
    public Map<String, String> requestdata;
    KonkaPayOrderBuilder orderBuilder;
    public static final String[] goodsNames = {"商品001", "商品002", "商品003", "商品004", "商品005",
            "商品006", "商品007"};
    public static final String[] goodsid = {"1001", "1002", "1003", "1004", "1005", "1006", "1007"};
    public static final String[] goodPrice = {"0.05", "0.1", "0.11", "0.13", "0.07", "0.02", "0" +
            ".01"};
    public static final String[] orderid = {"00001", "000012", "000013", "000014", "000015",
            "000016", "00007"};
    public static final String[] payAmount = {"4", "6", "7", "8", "9", "1", "10",};
    public static final String[] userid = {"151215", "32435", "2344", "122", "1213", "445523",
            "101123",};

    public ModelOfTest() {
        Client_KEY = MD5Util.MD5Encode(cpValue.key).toUpperCase();
    }

    private void init() {
        defaultRequestdata.put("cp_id", cpValue.cpId);
        defaultRequestdata.put("app_id", cpValue.appId);
        defaultRequestdata.put("goods_id", "1000000000000010");
        defaultRequestdata.put("goods_name", "康佳电视");
        defaultRequestdata.put("cp_order_id", "4444444444");
        defaultRequestdata.put("price", "" + 0.01f);
        defaultRequestdata.put("pay_amount", "1");
        defaultRequestdata.put("use_konka_user_sys", "1");
        defaultRequestdata.put("app_user_id", "555555555");
        defaultRequestdata.put("distribution_channels", "1");
        defaultRequestdata.put("cp_private_info", "8888888888");
        defaultRequestdata.put("notify_url", notify_url);
    }

    //随机生成
    public Map<String, String> getRandomData() {
        dataPrepare();
        setgoods_name(goodsNames[(int) (Math.random() * goodsNames.length)]);
        setGoodsId(goodsid[(int) (Math.random() * goodsid.length)]);
        setprice(goodPrice[(int) (Math.random() * goodPrice.length)]);
        setcp_order_id(orderid[(int) (Math.random() * orderid.length)]);
        setpay_amount(payAmount[(int) (Math.random() * payAmount.length)]);
        setapp_user_id(userid[(int) (Math.random() * userid.length)]);
        return requestdata;
    }

    public KonkaPayOrderBuilder getMakeBuilder() throws Exception {
        return getBuilder(requestdata);
    }

    public KonkaPayOrderBuilder getMakeTestBuilder() throws Exception {
        defaultRequestdata.put("cp_id", cpValue.cpId);
        defaultRequestdata.put("app_id", cpValue.appId);
        defaultRequestdata.put("goods_id", "1000000000000280");
        defaultRequestdata.put("goods_name", "好礼拿到手抽筋");
        defaultRequestdata.put("cp_order_id", "54399");
        defaultRequestdata.put("price", "0.01");
        defaultRequestdata.put("pay_amount", "1");
        defaultRequestdata.put("use_konka_user_sys", "1");
        defaultRequestdata.put("distribution_channels", "1");
        defaultRequestdata.put("notify_url", "http://pay.lajoin.com/index/konka/index");
//        getCommonSign();

        return getTestBuilder(defaultRequestdata);
    }
    private static String getCommonSign( ) {

        Map<String, String> requestdata = new HashMap<String, String>(); //
        requestdata.put("cp_id", "1000000000000018");
        requestdata.put("app_id", "1000000000000021");//
        requestdata.put("goods_id", "1000000000000280");
        requestdata.put("goods_name", "好礼拿到手抽筋");
        requestdata.put("cp_order_id", "54399");
        requestdata.put("price", "0.01");
        requestdata.put("pay_amount", "1");
        requestdata.put("use_konka_user_sys", "1");
        requestdata.put("distribution_channels", "1");
        requestdata.put("notify_url", "http://pay.lajoin.com/index/konka/index");

        // rt_comm_Key:合作方app对应的 key,该key值在康佳服务端存有一份。
        String Client_KEY = MD5Util.MD5Encode("D2A604BCF705D0BC944C0C93BE2827C8").toUpperCase();
        // 获取最终的签名结果
        String sign = Signature.doSign(requestdata, Client_KEY, "UTF-8");
        Log.i("djjtest","sign："+sign);
        return sign;

    }




    private KonkaPayOrderBuilder getTestBuilder(Map<String, String> map) throws Exception {
        orderBuilder = new KonkaPayOrderBuilder();
        orderBuilder
                .setCpId(map.get("cp_id"))
                .setAppId(map.get("app_id"))
                .setGoodsId(map.get("goods_id"))
                .setGoodsName(map.get("goods_name"))
                .setCpOrderId(map.get("cp_order_id"))
                .setPrice(map.get("price"))
                .setPayAmount(Integer.valueOf(map.get("pay_amount")))
                .setAppUserId(map.get("app_user_id"))
                .setDistributionChannels(
                        map.get("distribution_channels"))
                .setCpPrivateInfo(map.get("cp_private_info"))
                .setNotifyUrl(map.get("notify_url"))
                .setUseKonkaUserSys(map.get("use_konka_user_sys"));
        String sign = Signature.doSign(map, MD5Util.MD5Encode(cpValue.key
        ).toUpperCase(), "UTF-8");
        orderBuilder
                .setSign(Signature.doSign(map, MD5Util.MD5Encode(cpValue.key
                ).toUpperCase(), "UTF-8"));
        return orderBuilder;
    }

    private KonkaPayOrderBuilder getBuilder(Map<String, String> map) throws Exception {
//        orderBuilder = new KonkaPayOrderBuilder();
//        orderBuilder
//                .setCpId(map.get("cp_id"))
//                .setAppId(map.get("app_id"))
//                .setGoodsId(map.get("goods_id"))
//                .setGoodsName(map.get("goods_name"))
//                .setCpOrderId(map.get("cp_order_id"))
//                .setPrice(map.get("price"))
//                .setPayAmount(Integer.valueOf(map.get("pay_amount")))
//                .setAppUserId(map.get("app_user_id"))
//                .setDistributionChannels(
//                        map.get("distribution_channels"))
//                .setCpPrivateInfo(map.get("cp_private_info"))
//                .setNotifyUrl(map.get("notify_url"));
//        orderBuilder
//                .setSign(Signature.doSign(requestdata, Client_KEY, "UTF-8"));
//        return orderBuilder;
        return getMakeTestBuilder();
    }


    public void dataPrepare() {
        requestdata = new HashMap<String, String>();
        requestdata.put("cp_id", cpValue.cpId);
        requestdata.put("app_id", cpValue.appId);
        requestdata.put("use_konka_user_sys", "1");
        requestdata.put("distribution_channels", "1");
        requestdata.put("cp_private_info", "8888888888");
        requestdata.put("notify_url", notify_url);
    }

    public void setGoodsId(String goodsId) {
        requestdata.put("goods_id", goodsId);
    }

    public void setgoods_name(String goods_name) {
        requestdata.put("goods_name", goods_name);
    }

    public void setcp_order_id(String cp_order_id) {
        requestdata.put("cp_order_id", cp_order_id);
    }

    public void setapp_user_id(String app_user_id) {
        requestdata.put("app_user_id", app_user_id);
    }

    public void setprice(String price) {
        Log.i("KKPay", "setprice:" + price);
        requestdata.put("price", price);
    }

    public void setpay_amount(String pay_amount) {
        requestdata.put("pay_amount", pay_amount);
    }

    public void destroyData() {
        requestdata = null;
    }

    public boolean dataIsOk() {
        return requestdata == null ? false : true;
    }

    public String getDataString() {
        if (requestdata == null) {
            return "订单为空";
        } else {
            String ret = requestdata.toString().replaceAll(",", ",\n");
            ret += "\nsign=" + Signature.doSign(requestdata, Client_KEY, "UTF-8");
            return ret;
        }
    }
//    public void onProductOrderNo(String goodsName, GetOrderResult orderResult) {
//        if (orderResult != null && "0".equals(orderResult.getStatus())) {
//            mOrderResult = orderResult;
//            mGoodsName = goodsName;
//            float money = Float.parseFloat(orderResult.getMoney()) / 100;//以元为单位
//            BigDecimal decimal = new BigDecimal(money);
//            money = decimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
//            //money = 0.01f;  //for test
//
//            KonkaPayOrderBuilder orderBuilder = null;
//            try {
//                orderBuilder = new KonkaPayOrderBuilder()
//                        .setCpId(AppConfig.getInstance().getPayCpId())
//                        .setAppId(AppConfig.getInstance().getPayAppId())
//                        .setGoodsId(orderResult.getPid())
//                        .setGoodsName(goodsName)
//                        .setCpOrderId(orderResult.getOrderid())
//                        .setPrice(String.valueOf(money))
//                        .setPayAmount(1)
//                        .setAppUserId(AppConfig.getInstance().getUid())
//                        .setDistributionChannels("1")//支付分发渠道
//                        .setCpPrivateInfo("Konka")//商户私有信息
//                        .setNotifyUrl(orderResult.getNotifyUrl());
//            } catch (Exception e) {
//                KLog.d(TAG, "KonkaPayOrderBuilder --> ");
//                e.printStackTrace();
//                return;
//            }
//            Map<String, String> requestdata = new HashMap<>();
//            requestdata.put("cp_id", AppConfig.getInstance().getPayCpId());
//            requestdata.put("app_id", AppConfig.getInstance().getPayAppId());
//            requestdata.put("goods_id", orderResult.getPid());
//            requestdata.put("goods_name", goodsName);
//            requestdata.put("cp_order_id", orderResult.getOrderid());
//            requestdata.put("price", "" + money);
//            requestdata.put("pay_amount", "1");
//            requestdata.put("use_konka_user_sys", "1");
//            requestdata.put("app_user_id", AppConfig.getInstance().getUid());
//            requestdata.put("distribution_channels", "1");//支付分发渠道
//            requestdata.put("cp_private_info", "Konka");//商户私有信息
//
//            KLog.d(TAG, "cp_id: " + AppConfig.getInstance().getPayCpId());
//            KLog.d(TAG, "app_id: " + AppConfig.getInstance().getPayAppId());
//            KLog.d(TAG, "goods_id: " + orderResult.getPid());
//            KLog.d(TAG, "goods_name: " + goodsName);
//            KLog.d(TAG, "cp_order_id: " + orderResult.getOrderid());
//            KLog.d(TAG, "notify_url:  " + orderResult.getNotifyUrl());
//            KLog.d(TAG, "app_user_id" + AppConfig.getInstance().getUid());
//
//            String notifyUrl = orderResult.getNotifyUrl();
//            KLog.d(TAG, "notifyUrl " + notifyUrl);
//            requestdata.put("notify_url", notifyUrl);
//
//            String Client_KEY = MD5Util.MD5Encode(AppConfig.getInstance().getPayRtCommKey())
// .toUpperCase();
//            try {
//                orderBuilder.setSign(Signature.doSign(requestdata, Client_KEY, "UTF-8"));
//            } catch (Exception e) {
//                e.printStackTrace();
//                KLog.d(TAG, "setSign");
//            }
//            kkPayClient.pay(this, orderBuilder);
//        }
//    }
}
