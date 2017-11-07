package com.konka.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.konka.kkuserpaydemo.R;
import com.konka.tvpay.KKPayClient;
import com.konka.tvpay.data.BusinessConstant;
import com.konka.tvpay.data.builder.KonkaPayOrderBuilder;

import java.text.DecimalFormat;

/**
 * Created by DongJunJie on 2016-12-14.
 */

public class PresenterOfTest implements View.OnClickListener {

    KKPayClient mKKPayClient;

    Activity mActivity;

    ModelOfTest mModel;
    LayoutHolder mView;

    public PresenterOfTest(Activity activity, View root) {
        mKKPayClient = new KKPayClient(activity);
        mActivity = activity;
        mView = new LayoutHolder(root, this);
        mModel = new ModelOfTest();

    }


    public void onPayBack(int requestCode, int resultCode, Intent data) {
        String result = "requestCode:" + requestCode + " resultCode:" + resultCode + " " +
                "data:";
        String ret_code = "";
        String ret_msg = "";
        if (data != null) {
            ret_code = data.getStringExtra("ret_code");
            ret_msg = data.getStringExtra("ret_msg");
            result += "{ret_code:" + ret_code + "  ret_msg:" + ret_msg + "}";
        } else {
            result += "null";
        }
        result += "\n参数含义：";
        if (requestCode == BusinessConstant.PAY) {
            result += "支付页面返回，";
        }
        switch (resultCode) {
            case Activity.RESULT_CANCELED:
                result += "支付页面取消，";
                break;
            case Activity.RESULT_OK:
                result += "支付正常返回，";
                break;
        }
        if ("0".equals(ret_code)) {
            result += "支付成功";
        }
        mView.setPayReturnText(result);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.make_order:
                makeOrder();
                break;
            case R.id.random_make_order:
                randomMake();
                break;
            case R.id.button_pay:
//                testCTOpay();
                pay();
                break;
            case R.id.use_konka:
                usekonkausersys();
                break;
        }
    }


    private void testCTOpay() {
        KonkaPayOrderBuilder orderBuilder = null;
        try {
            orderBuilder = new KonkaPayOrderBuilder().setCpId("1000000000000001")
                    .setAppId("1000000000000009").setGoodsId("105").setGoodsName("黄冈名师")
                    .setCpOrderId("13").setPrice("0.01").setPayAmount(1).setAppUserId("555555555")
                    .setDistributionChannels("1").setCpPrivateInfo("8888888888").setNotifyUrl
                            ("http://test.kkapp.com/kknyxserver/NotifyInfo/notifyPayResult.do")
                    .setSign("26E5E8681791D4BDC60100F23B4D5647");
        } catch (Exception e) {
            Toast.makeText(mActivity, "参数不合法", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        try {
            mKKPayClient.pay(mActivity, orderBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pay() {
        if (mModel.dataIsOk()) {
            KonkaPayOrderBuilder builder = null;
            try {
                builder = mModel.getMakeBuilder();
            } catch (Exception e) {
                Toast.makeText(mActivity, "订单参数异常，无法支付", Toast.LENGTH_LONG).show();
                e.printStackTrace();
                return;
            }
            try {
                mKKPayClient.pay(mActivity, builder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mActivity, "请先创建订单", Toast.LENGTH_LONG).show();
        }
    }

    private void randomMake() {
        mView.setViewData(mModel.getRandomData());
        mView.setorderText(mModel.getDataString());
    }


    private void makeOrder() {
        if (!getViewdata()) {
            mModel.destroyData();
        }
        mView.setorderText(mModel.getDataString());
    }

    private void usekonkausersys() {
        Intent intent = new Intent("com.konka.testdemo.usersystem.MainActivity");
        mActivity.startActivity(intent);
    }

    private boolean getViewdata() {
        mModel.dataPrepare();
        String temp;
        if (TextUtils.isEmpty(temp = mView.getgoodsNameText())) {
            Toast.makeText(mActivity, "请添加商品名称", Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp.length() > 32) {
            Toast.makeText(mActivity, "商品名称不能大于32个字符", Toast.LENGTH_LONG).show();
            return false;
        }
        mModel.setgoods_name(temp);
        if (TextUtils.isEmpty(temp = mView.getGoodsidText())) {
            Toast.makeText(mActivity, "请添加商品ID", Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp.length() > 20) {
            Toast.makeText(mActivity, "商品ID不能大于20个字符", Toast.LENGTH_LONG).show();
            return false;
        }
        mModel.setGoodsId(temp);
        if (TextUtils.isEmpty(temp = mView.getpriceText())) {
            Toast.makeText(mActivity, "请添加商品单价", Toast.LENGTH_LONG).show();
            return false;
        }
        float price;
        try {
            price = Float.valueOf(temp);
        } catch (Exception e) {
            Toast.makeText(mActivity, "价格参数不是float", Toast.LENGTH_LONG).show();
            return false;
        }
        if (price < 0 || price > 999999.99f) {
            Toast.makeText(mActivity, "价格参数必须大于0小于100W", Toast.LENGTH_LONG).show();
            return false;
        }
        DecimalFormat df = new DecimalFormat();
        String style = "#####0.00";//定义要显示的数字的格式
        df.applyPattern(style);// 将格式应用于格式化器
        mModel.setprice(df.format(price));
        if (TextUtils.isEmpty(temp = mView.getpayAmountText())) {
            Toast.makeText(mActivity, "请添加商品数量", Toast.LENGTH_LONG).show();
            return false;
        }
        int amount;
        try {
            amount = Integer.valueOf(temp);
        } catch (Exception e) {
            Toast.makeText(mActivity, "数量参数必须是int(大于2147483647越界)", Toast.LENGTH_LONG).show();
            return false;
        }
        if (amount < 1 || amount > 999999999) {
            Toast.makeText(mActivity, "数量参数必须大于0小于999999999", Toast.LENGTH_LONG).show();
            return false;
        }
        mModel.setpay_amount(temp);
        if (TextUtils.isEmpty(temp = mView.getcpOrderIdText())) {
            Toast.makeText(mActivity, "请添加订单号", Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp.length() > 64) {
            Toast.makeText(mActivity, "订单号不能大于64个字符", Toast.LENGTH_LONG).show();
            return false;
        }
        mModel.setcp_order_id(temp);
        if (TextUtils.isEmpty(temp = mView.getUserId())) {
            Toast.makeText(mActivity, "请添加用户ID", Toast.LENGTH_LONG).show();
            return false;
        }
        if (temp.length() > 32) {
            Toast.makeText(mActivity, "用户ID不能大于32个字符", Toast.LENGTH_LONG).show();
            return false;
        }
        mModel.setapp_user_id(temp);
        return true;
    }
}
