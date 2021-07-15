package com.example.asiabill_android_sdk_java.activity;/*
 * Copyright 2020 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.asiabill.testapp.manager.PayTask;
import com.asiabill.testapp.model.GoodsDetail;
import com.asiabill.testapp.model.PayInfoBean;
import com.asiabill.testapp.model.PaymentUiData;
import com.example.asiabill_android_sdk_java.R;
import com.example.asiabill_android_sdk_java.databinding.ActivityMainBinding;
import com.example.asiabill_android_sdk_java.model.PayResult;
import com.module.common.base.BaseActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


/**
 * Checkout implementation for the app
 */
public class MainActivity extends BaseActivity {

    public static final String CREDITCARDSTR= "Credit Card";
    private ActivityMainBinding layoutBinding;

    private long clickTime = 0;

    /**
     * 支付结果处理回调
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandle = new Handler() {
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((String) msg.obj);
            String code = payResult.getCode();
            if(TextUtils.equals(code, "9900")){
                Toast.makeText(MainActivity.this, R.string.asiabill_buy_item_successs,
                        Toast.LENGTH_SHORT).show();
            }else {
                if (TextUtils.equals(code, "7700")) {
                    Toast.makeText(MainActivity.this, R.string.asiabill_buy_item_fail,
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.equals(code, "6600")){
                    Toast.makeText(MainActivity.this, R.string.asiabill_buy_item_pending,
                            Toast.LENGTH_SHORT).show();
                }
            }

        };
    };


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeUi();
    }


    private void initializeUi() {
        // Use view binding to access the UI elements
        layoutBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(layoutBinding.getRoot());
        layoutBinding.currencyEt.setText(ORDER_CURRENCY);
        layoutBinding.detailIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLayoutVisible();
            }
        });

        layoutBinding.goodDetailOne.placeDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(System.currentTimeMillis() - clickTime >800) {
                    clickTime = System.currentTimeMillis();
                    if (CREDITCARDSTR.equals(paymentMethod)) {
                        buildVersionTwoPaymentInfo(layoutBinding.goodDetailOne.price.getText().toString().trim());
                    }else {
                        buildVersionOnePaymentInfo(layoutBinding.goodDetailOne.price.getText().toString().trim());
                    }
                }
            }
        });

        layoutBinding.goodDetailTwo.placeDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(System.currentTimeMillis() - clickTime >800) {
                    clickTime = System.currentTimeMillis();
                    if (CREDITCARDSTR.equals(paymentMethod)) {
                        buildVersionTwoPaymentInfo(layoutBinding.goodDetailTwo.price.getText().toString().trim());
                    }else {
                        buildVersionOnePaymentInfo(layoutBinding.goodDetailTwo.price.getText().toString().trim());
                    }
                }
            }
        });
        layoutBinding.goodDetailThree.placeDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(System.currentTimeMillis() - clickTime >800) {
                    clickTime = System.currentTimeMillis();
                    if (CREDITCARDSTR.equals(paymentMethod)) {
                        buildVersionTwoPaymentInfo(layoutBinding.goodDetailThree.price.getText().toString().trim());
                    }else {
                        buildVersionOnePaymentInfo(layoutBinding.goodDetailThree.price.getText().toString().trim());
                    }
                }
            }
        });

        layoutBinding.placeHolderBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View view) {
                String mainEnvironment=layoutBinding.mainEnvironmentEt.getText().toString().trim();
                if(TextUtils.isEmpty(mainEnvironment)){
                    mainEnvironment = "0";
                }
                if(mainEnvironment.equals("2")){
                    layoutBinding.mainEnvironmentLayout.setHint(R.string.checkout_item_environmentproduct);
                    paymentsEnvironment =2;
                }else {
                    layoutBinding.mainEnvironmentLayout.setHint(R.string.checkout_item_environmenttest);
                    paymentsEnvironment =0;
                }
                layoutBinding.placeHolderLayout.setVisibility(View.GONE);
            }
        });
    }




    //---------------------------------------------------------------------------
    /***
     * 构建sdk支付数据
     */

    public static  String ORDERNO = String.valueOf(System.currentTimeMillis());
    //0 测试环境 1 fz环境 2 线上生产环境（默认）
    public static  int paymentsEnvironment = 0;
    private String ORDER_CURRENCY = "USD";
    private String ORDER_AMOUNT = "0.01";
    private String COUNTRY="US";
    private String viewManagerType = "0";   // 0、默认   1、商户自定义-旧卡支付   2、商户自定义-新卡支付
    private String customerID = "test_sign_123456";
    private String cardToken = "";
    private String callbackUrl = "https://testpay.asiabill.com/services/v3/CallResult";
    private String paymentMethod = "Credit Card";
//    private String paymentMethod = "CryptoPayment";
    private String isMobile = "2";
    private String creNum="1234567891234569"; //16位身份证信息且都为数字,country为ID时必传
    private JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("Visa")
                .put("Master card")
                .put("American Express")
                .put("JCB")
                .put("Discover")
                .put("Maestro")
                .put("Dinners club");
    }

    /**
     *  发起交易
     * 海外本地支付支付的入口
     */
    private void buildVersionOnePaymentInfo(String price) {
        ORDERNO = String.valueOf(System.currentTimeMillis());
        ORDER_AMOUNT=price;
        if (TextUtils.isEmpty(ORDERNO)) {
            return;
        }
        PayInfoBean payInfoBean = new PayInfoBean();
        //设置用户firstName
        payInfoBean.setFirstName("CL");
        //设置用户lastName
        payInfoBean.setLastName("BRW1");
        //设置用户email
        payInfoBean.setEmail("532539937@qq.com");
        //设置用户phone
        payInfoBean.setPhone("+1 650-555-5555");
        //设置用户国家
        payInfoBean.setCountry(COUNTRY);
        //设置用户所在州
        payInfoBean.setState("CA");
        //设置用户账单城市
        payInfoBean.setCity("Mountain View");
        //设置用户账单地址
        payInfoBean.setAddress("1600 Amphitheatre Parkway");
        //设置用户账单邮编
        payInfoBean.setZip("94043");
        //设置支付订单号
        payInfoBean.setOrderNo(ORDERNO);
        //设置支付金额大小
        payInfoBean.setOrderAmount(ORDER_AMOUNT);
        //设置支付货币单位
        payInfoBean.setOrderCurrency(ORDER_CURRENCY);
        //设置支付方式
        payInfoBean.setPaymentMethod(paymentMethod);
        //设置用户身份证号
        //payInfoBean.setCreNum(creNum);
        //设置客户端类型（移动端传2）0：PC 端 1：移动 wap 2: 移动 sdk
        payInfoBean.setIsMobile(isMobile);
        //0 测试环境 1 fz环境 2 线上生产环境（默认）
        payInfoBean.setPaymentsEnvironment(paymentsEnvironment);
        Runnable payRunable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(MainActivity.this);
                String result = payTask.pay(payInfoBean);

                Message msg = new Message();
                msg.obj = result;
                mHandle.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunable);
        payThread.start();
    }

    /**
     * 发起交易
     * version asiabill_2.0
     * 国际信用卡支付的入口
     */
    private void buildVersionTwoPaymentInfo(String price) {
        ORDERNO = String.valueOf(System.currentTimeMillis());
        ORDER_AMOUNT=price;
        if (TextUtils.isEmpty(ORDERNO)) {
            return;
        }
        PayInfoBean payInfoBean = new PayInfoBean();
        List<GoodsDetail> list=new ArrayList<>();
        list.add(new GoodsDetail("10","product one","5"));
        list.add(new GoodsDetail("10.6","product two","5"));
        list.add(new GoodsDetail("20.2","product three","5"));
        //设置商品详情，可不传
        payInfoBean.setGoodsDetail(list);

        PaymentUiData paymentUiData=new PaymentUiData();
//        paymentUiData.setBackResource(R.drawable.ic_arrow_back);
//        paymentUiData.setTextColor(Color.parseColor("#ffffff"));
//        paymentUiData.setTextSize(18.0f);
//        paymentUiData.setToolbarBackgroundColor(Color.parseColor("#ff0099cc"));
        paymentUiData.setDialogLayoutResource(R.layout.main_dialog_loading);
        //设置支付界面头部和请求框资源文件，可不传
        payInfoBean.setPaymentUiData(paymentUiData);
        //设置用户firstName
        payInfoBean.setFirstName("CL");
        //设置用户lastName
        payInfoBean.setLastName("BRW1");
        //设置用户email
        payInfoBean.setEmail("532539937@qq.com");
        //设置用户phone
        payInfoBean.setPhone("+1 650-555-5555");
        //设置用户国家
        payInfoBean.setCountry(COUNTRY);
        //设置用户所在州
        payInfoBean.setState("CA");
        //设置用户账单城市
        payInfoBean.setCity("Mountain View");
        //设置用户账单地址
        payInfoBean.setAddress("1600 Amphitheatre Parkway");
        //设置用户账单邮编
        payInfoBean.setZip("94043");
        //设置支付订单号
        payInfoBean.setOrderNo(ORDERNO);
        //设置支付金额大小
        payInfoBean.setOrderAmount(ORDER_AMOUNT);
        //设置支付货币单位
        payInfoBean.setOrderCurrency(ORDER_CURRENCY);
        //设置支持卡种类型
        payInfoBean.setCardType(getAllowedCardNetworks().toString());
        //设置支付方式
        payInfoBean.setPaymentMethod(paymentMethod);
        // payInfoBean.setCreNum(creNum);
        //设置客户端类型（移动端传2）0：PC 端 1：移动 wap 2: 移动 sdk
        payInfoBean.setIsMobile(isMobile);
        //设置支付方式是否显示googlepay选项（默认为1不显示）；0谷歌 1其他
        payInfoBean.setTokenPayType("1");
        //是否开启显示sdk保存卡功能(不传值则不开启) 0：sdk托管显示；1：商户自定义显示ui并且用户已经选择了一张卡；2：商户自定义ui并且选择了添加新卡
        payInfoBean.setViewManagerType(viewManagerType);
        //只有viewManagerType = 1(商户自定义显示ui并且用户已经选择了一张卡)情况下传入用户选择卡的cardToken
        payInfoBean.setCardToken(cardToken);
        //设置用户的customerId
        payInfoBean.setCustomerID(customerID);
        //0 测试环境 1 fz环境 2 线上生产环境（默认）
        payInfoBean.setPaymentsEnvironment(paymentsEnvironment);
        //商户后台异步通知交易结果使用该URL通知
        payInfoBean.setCallbackUrl(callbackUrl);
        Runnable payRunable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(MainActivity.this);
                String result = payTask.pay(payInfoBean);

                Message msg = new Message();
                msg.obj = result;
                mHandle.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunable);
        payThread.start();
    }

    public void changeLayoutVisible(){
        if (layoutBinding.placeHolderLayout.getVisibility()==View.VISIBLE){
            layoutBinding.placeHolderLayout.setVisibility(View.GONE);
        }else {
            layoutBinding.placeHolderLayout.setVisibility(View.VISIBLE);
        }
    }
}
