**<h1>Asiabill Android SDK</h1>**

> Welcome to Asiabill's Android SDK. This library will help you accept card and alternative payments in your Android app.



**<h2>asiabill Android sdk对接步骤</h2>**

> **<h3>1. application需要实现抽象BaseApplication以便sdk初始化（参照AsiabillApplication）<h3>**
 
     public class AsiabillApplication extends BaseApplication {}
 
> **<h3> 2. 项目build.gradle里面添加配置<h3>**
 
 repositories {
   jcenter()
       url "https://raw.githubusercontent.com/Asiabill/asiabill_android/main"
  }

> **<h3>3. app模块build.gradle里面添加asiabill sdk库<h3>**
 
 dependencies {
    implementation "com.asiabill.payment:android_payment:2.0.0"//具体版本号根据你的需求来确定
 }

> **<h3>4. Android sdk调用方法介绍<h3>**
 
| 方法类型 | 示例| 
| ------ | ------ |
| 方法原型	             |    PayTask payTask new PayTask(activity); payTask.pay(PayInfoBean)     |
| 方法功能	             |        PayInfoBean(对象赋值传入参数                                     |
| 方法参数	             |        如payInfoBean.setFirstName("CL");等)                           |
| 返回值	               |         PayResult payResult = new PayResult((String) msg.obj)       |


----------------------------------------end--------------------------------------
