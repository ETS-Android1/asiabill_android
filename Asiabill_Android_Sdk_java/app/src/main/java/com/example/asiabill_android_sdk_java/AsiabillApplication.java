package com.example.asiabill_android_sdk_java;


import com.module.common.base.BaseApplication;


/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 */
public class AsiabillApplication extends BaseApplication {
    private static AsiabillApplication sInstance;


    public static AsiabillApplication getInstance() {
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
