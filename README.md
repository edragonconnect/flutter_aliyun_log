# Flutter 阿里云日志服务


本文档主要介绍阿里云日志服务 Flutter 的安装和使用。本文档假设您已经开通了[阿里云日志服务](https://www.aliyun.com/product/sls/)。如果您还没有开通或者还不了解日志服务，请登录[日志服务产品主页](https://www.aliyun.com/product/sls/)获取更多的帮助。

## Flutter 阿里云日志服务使用了如下 SDKs

Android SDK
> https://github.com/aliyun/aliyun-log-android-sdk/


iOS SDK
> https://github.com/aliyun/aliyun-log-ios-sdk/

## Android 
### 环境要求
1. Android系统版本：2.3 及以上。
2. 必须注册有Aliyun.com用户账户，并开通日志服务。

### 权限设置
以下是日志服务 Android SDK所需要的Android权限，请确保您的AndroidManifest.xml文件中已经配置了这些权限，否则，SDK将无法正常工作。

```
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>

```


## iOS
目前提供以下功能：

1. 写入日志(默认为HTTPS)
2. 断点续传(默认为HTTPS)


## 使用方式

在 `pubspec.yaml `中添加 `flutter_aliyun_log` 到 `dependencies`

请使用 `Git依赖` 方式


```
import 'package:flutter_aliyun_log/flutter_aliyun_log.dart';

// 创建 Aliyun LogClient 配置
AliyunLogConfig config = AliyunLogConfig(
		endPoint:"", // no https://||http://
		accessKeyID:"accessKeyID",
		accessKeySecret:"accessKeySecret",
		projectName:"projectName",
		token:"token"
);


// 启动 Aliyun LogClient
FlutterAliyunLog.create(config: config);

// 创建日志配置项
AliyunLogItem logItem = AliyunLogItem(
      store: "storeName",
      topic: "topic",
      source: "sourceName",
    );

// 添加日志内容(keyContent)
logItem.append(key:"key",content:"");

//keyContent 保存在 `logItem.keyContents ` 列表里

// 发送日志
FlutterAliyunLog.post(logItem: logItem);

```

