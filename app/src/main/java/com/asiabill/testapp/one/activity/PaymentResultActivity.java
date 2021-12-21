package com.asiabill.testapp.one.activity;


import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.asiabill.testapp.one.R;

public class PaymentResultActivity extends Activity {
    public static final String ALIPAY_PAYMENT_RESULT = "alipay_payment_result";
    public static final String ALIPAY_PAYMENT_ORDERID = "alipay_payment_orderid";
    public static final String ALIPAY_PAYMENT_AMOUNT = "alipay_payment_amount";
    public static final String ALIPAY_PAYMENT_METHOD = "alipay_payment_method";
    public static final String ALIPAY_PAYMENT_CURRENCY = "alipay_payment_currency";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_result);
        if(getIntent() != null) {
            int result = getIntent().getIntExtra(ALIPAY_PAYMENT_RESULT, 0);
            String orderId = getIntent().getStringExtra(ALIPAY_PAYMENT_ORDERID);
            if(orderId != null && !TextUtils.isEmpty(orderId)){
                ((TextView)findViewById(R.id.alipay_result_orderid_tv)).setText(orderId);
            }
             String orderAmount = getIntent().getStringExtra(ALIPAY_PAYMENT_AMOUNT);
            if(orderAmount != null && !TextUtils.isEmpty(orderAmount)){
                ((TextView)findViewById(R.id.order_amount)).setText(orderAmount);
            }
            String orderCurrency = getIntent().getStringExtra(ALIPAY_PAYMENT_CURRENCY);
            if(orderAmount != null && !TextUtils.isEmpty(orderAmount)){
                ((TextView)findViewById(R.id.order_currency)).setText(orderCurrency);
            }
            String orderMethod = getIntent().getStringExtra(ALIPAY_PAYMENT_METHOD);
            if(orderAmount != null && !TextUtils.isEmpty(orderAmount)){
                ((TextView)findViewById(R.id.order_method)).setText(orderMethod);
            }

            if(result == 1){
                ((ImageView)findViewById(R.id.alipay_result_iv)).setBackground(this.getResources().getDrawable(R.drawable.payment_result_icon_successful));
                ((TextView)findViewById(R.id.alipay_result_tv)).setText("支付成功");
            }else if(result == 2){
                ((ImageView)findViewById(R.id.alipay_result_iv)).setBackground(this.getResources().getDrawable(R.drawable.payment_result_icon_error));
                ((TextView)findViewById(R.id.alipay_result_tv)).setText("支付失败");
                ((TextView)findViewById(R.id.alipay_result_tv)).setTextColor(this.getResources().getColor(R.color.common_title_bg));
            }else {
                ((ImageView)findViewById(R.id.alipay_result_iv)).setBackground(this.getResources().getDrawable(R.drawable.payment_result_icon_successful));
                ((TextView)findViewById(R.id.alipay_result_tv)).setText("待处理...");
            }
        }else {
            ((ImageView)findViewById(R.id.alipay_result_iv)).setBackground(this.getResources().getDrawable(R.drawable.payment_result_icon_successful));
            ((TextView)findViewById(R.id.alipay_result_tv)).setText("待处理...");
        }
    }
}