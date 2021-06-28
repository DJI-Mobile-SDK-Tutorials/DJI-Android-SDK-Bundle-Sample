# Android-AppBundleDemo

## 简介

在本教程中，你将学会如何将MSDK应用到google的App-Bundle中

## 前提

 - Android Studio 3.2+
 - Android System 4.1+
 - DJI Android SDK 4.15

## 使用

1. 在进入dynamic-module的第一个Activity中调用方法```Helper.install(application)```。同时该Activity必须是Java代码，不能是kotlin（会带来预期外的错误）。
2. 在进入dynamic-module的第一个Activity中调用```AppBundleHelper.init()```。

   **调用方式:**
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
3. 你需要在base-module中创建一个```WebView```, 因为第一次创建```WebView```会更新```assetManager```，因此需要实现更新assetManager，以免SDK中创建的WebView覆盖```assetManager```
   
   **调用方式: activity_main.xml**
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