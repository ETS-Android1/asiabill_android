**<h1>Asiabill Android SDK</h1>**

> Welcome to Asiabill's Android SDK. This library will help you accept card and alternative payments in your Android app.



**<h2>asiabill Android sdk对接步骤</h2>**

> **<h3>1. application需要实现抽象BaseApplication以便sdk初始化（参照DEMO中AsiabillApplication）<h3>**
 
     public class AsiabillApplication extends BaseApplication {}
 
> **<h3> 2. 项目build.gradle里面添加配置<h3>**
 
 repositories {
    jcenter()
    maven{
        url "https://raw.githubusercontent.com/Asiabill/asiabill_android/main"
     }
  }

> **<h3>3. app模块build.gradle里面添加asiabill sdk库<h3>**
 
 dependencies {
    implementation "com.asiabill.payment:android_payment:2.0.0"//具体版本号根据你的需求来确定
 }
 
> **<h3>4. app模块gradle.properties里面添加支持androidx库配置<h3>**
 
 android.useAndroidX=true
 android.enableJetifier=true

> **<h3>5. app模块下AndroidManifest.xml里面添加商户号、网关号和signkey(格式: 商户号##网关号##signkey)<h3>**
 
 paymentsEnvironment: 0 测试环境  2 线上生产环境（默认）
 
 payInfoBean.setPaymentsEnvironment(paymentsEnvironment);
 
商户正式网关号示例（不用设置默认为线上环境， name="asiabillsdk_key"）：
 
        <meta-data
            android:name="asiabillsdk_key"
            android:value="12117##12117001##12345678" />

 商户测试网关号（payInfoBean.setPaymentsEnvironment("0")， name="asiabillsdk_test_key"）：
 
        <meta-data
            android:name="asiabillsdk_test_key"
            android:value="12167##12167001##12345678" />
 
 
> **<h3>6. Android sdk调用方法介绍<h3>**
 
| 方法类型 | 示例| 
| ------ | ------ |
| 方法原型	             |    PayTask payTask new PayTask(activity); payTask.pay(PayInfoBean)     |
| 方法功能	             |        提供给商户订单支付功能                                   |
| 方法参数	             |        PayInfoBean(对象赋值传入参数,如payInfoBean.setFirstName("CL")等）             |
| 返回值	               |         PayResult payResult = new PayResult((String) msg.obj)       |

----------------------------------------end--------------------------------------
