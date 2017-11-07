package com.konka.testdemo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.konka.kkuserpaydemo.R;

import java.util.Map;

public class LayoutHolder {

    private EditText goodsid;
    private EditText goodsName;
    private EditText cpOrderId;
    private EditText price;
    private EditText payAmount;
    private TextView order;
    private Button makeOrder;
    private Button buttonPay;
    private Button randomMakeOrder;
    private TextView payReturn;
    private TextView useridLabel;
    private EditText userid;
    private Button useKonka;

    public LayoutHolder(View view, View.OnClickListener onClickListener) {
        goodsid = (EditText) view.findViewById(R.id.goodsid);
        goodsName = (EditText) view.findViewById(R.id.goodsName);
        cpOrderId = (EditText) view.findViewById(R.id.cpOrderId);
        price = (EditText) view.findViewById(R.id.price);
        payAmount = (EditText) view.findViewById(R.id.payAmount);
        order = (TextView) view.findViewById(R.id.order);
        makeOrder = (Button) view.findViewById(R.id.make_order);
        buttonPay = (Button) view.findViewById(R.id.button_pay);
        randomMakeOrder = (Button) view.findViewById(R.id.random_make_order);
        payReturn = (TextView) view.findViewById(R.id.pay_return);
        useridLabel = (TextView) view.findViewById(R.id.userid_label);
        userid = (EditText) view.findViewById(R.id.userid);
        useKonka = (Button) view.findViewById(R.id.use_konka);
        buttonPay.setOnClickListener(onClickListener);
        randomMakeOrder.setOnClickListener(onClickListener);
        makeOrder.setOnClickListener(onClickListener);
        useKonka.setOnClickListener(onClickListener);
    }


    public void setViewData(Map<String, String> data) {
        setgoodsNameText(data.get("goods_name"));
        setGoodsidText(data.get("goods_id"));
        setcpOrderIdText(data.get("cp_order_id"));
        setpriceText(data.get("price"));
        setpayAmountText(data.get("pay_amount"));
        setUserId(data.get("app_user_id"));
    }

    public String getGoodsidText() {
        return goodsid.getText().toString();
    }

    public void setGoodsidText(String goodsidText) {
        goodsid.setText(goodsidText);
    }

    public String getgoodsNameText() {
        return goodsName.getText().toString();
    }

    public void setgoodsNameText(String goodsidText) {
        goodsName.setText(goodsidText);
    }

    public String getcpOrderIdText() {
        return cpOrderId.getText().toString();
    }

    public void setcpOrderIdText(String goodsidText) {
        cpOrderId.setText(goodsidText);
    }

    public String getpriceText() {
        return price.getText().toString();
    }

    public void setpriceText(String goodsidText) {
        price.setText(goodsidText);
    }

    public String getpayAmountText() {
        return payAmount.getText().toString();
    }

    public void setpayAmountText(String goodsidText) {
        payAmount.setText(goodsidText);
    }

    public void setUserId(String useridText) {
        userid.setText(useridText);
    }

    public String getUserId() {
        return userid.getText().toString();
    }

    public EditText getGoodsid() {
        return goodsid;
    }

    public TextView getCpOrderId() {
        return cpOrderId;
    }

    public Button getMakeOrder() {
        return makeOrder;
    }


    public EditText getPayAmount() {
        return payAmount;
    }

    public Button getButtonPay() {
        return buttonPay;
    }

    public TextView getPayReturn() {
        return payReturn;
    }

    public EditText getGoodsName() {
        return goodsName;
    }

    public EditText getPrice() {
        return price;
    }

    public TextView getOrder() {
        return order;
    }

    public Button getRandomMakeOrder() {
        return randomMakeOrder;
    }


    public void setorderText(String orderText) {
        order.setText(orderText);
    }

    public void setPayReturnText(String PayReturn) {
        payReturn.setText(PayReturn);
    }

    public TextView getUseridLabel() {
        return useridLabel;
    }

    public EditText getUserid() {
        return userid;
    }

    public Button getUseKonka() {
        return useKonka;
    }
}
