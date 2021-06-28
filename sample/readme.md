# Android-AppBundleDemo

## Introduction

In this tutorial, you will learn how to use the SDK with app-bundle

## Requirements

 - Android Studio 3.2+
 - Android System 4.1+
 - DJI Android SDK 4.15

## Tutorial

1. Call the method ```Helper.install(application)``` on your dynamic-module first Activity. And the Activity need to be coded by java.
2. Call the method ```AppBundleHelper.init()``` on your dynamic-module first Activity.
   **EX:**
   ```
    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        SplitCompat.installActivity(this);
        // because SDK use applicationContext to find resource.
        // so need add the resource into application
        SplitCompat.install(context.getApplicationContext());
        Helper.install((Application) context.getApplicationContext());
        AppBundleHelper.init();
    }
   ```
3. You need to create a ```WebView``` on your base module, because the first webview created will change the assetManager
   **EX: activity_main.xml**
   ```
    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:orientation="vertical"
        android:gravity="center"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <TextView
                android:padding="20dp"
                android:id="@+id/sdkTv"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Go to SDK"/>
    
        <TextView
                android:padding="20dp"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/loadTv"
                android:text="load dynamic"/>
    
        <WebView android:layout_width="wrap_content" android:layout_height="wrap_content" />
    </LinearLayout>
   ```
## Local Test

1. Download the latest bundletool exectuable from```https://github.com/google/bundletool/releases```.
2. Compile your project and generate a aab file. Eg. app.aab.
3. Run your bundletool exectuable to uncompress the aab file. Eg. ```java -jar bundletool-all-1.4.0.jar build-apks --local-testing --bundle=app.aab --output=app.apks``` 
4. Install your apks onto your mobile device. Eg. ```java -jar bundletool-all-1.4.0.jar install-apks --apks=app.apks```
5. The apks is a set of apk includes many source files and libraries. These steps will simulate how the aab has been download from the Google Play Store and installed on an user's mobile phone.
## Feedback

We’d love to hear your feedback on this demo and tutorial.

Please use **Stack Overflow** [dji-sdk](https://stackoverflow.com/questions/tagged/dji-sdk) or **email** [dev@dji.com](dev@dji.com) when you meet any problems of using this demo. At a minimum please let us know:

* Which DJI Product you are using?
* Which Android Device and Android System version you are using?
* Which Android Studio version you are using?
* A short description of your problem includes debugging logs or screenshots.
* Any bugs or typos you come across.

## License

Android-AppBundleDemo is available under the MIT license. Please see the LICENSE file for more info.

## Join Us

DJI is looking for all kinds of Software Engineers to continue building the Future of Possible. Available positions in Shenzhen, China and around the world. If you are interested, please send your resume to <software-sz@dji.com>. For more details, and list of all our global offices, please check <https://we.dji.com/jobs_en.html>.

DJI 招软件工程师啦，based在深圳，如果你想和我们一起把DJI产品做得更好，请发送简历到 <software-sz@dji.com>.  详情请浏览 <https://we.dji.com/zh-CN/recruitment>.