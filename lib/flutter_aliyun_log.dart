import 'dart:async';
import 'package:flutter/services.dart';

const MethodChannel _channel = const MethodChannel('flutter_aliyun_log');

class AliyunLogConfig {
  String projectName;
  String accessKeyID;
  String accessKeySecret;
  String endPoint;
  String token;
  bool enableNativeDebug = false;
  AliyunLogConfig({
    this.endPoint,
    this.accessKeyID,
    this.accessKeySecret,
    this.projectName,
    this.token,
  });
  getConfig() {
    return {
      "projectName": this.projectName,
      "accessKeyID": this.accessKeyID,
      "accessKeySecret": this.accessKeySecret,
      "endPoint": this.endPoint,
      "token": this.token
    };
  }
}

class AliyunLogItem {
  AliyunLogItem({this.store, this.topic, this.source, log: List});
  String topic;
  String source;
  String store;
  List keyContents = [];
  append({key: String, content: String}) =>
      this.keyContents.add({"key": key, "content": content});
  getLogItem() => {
        "store": store,
        "source": source,
        "topic": topic,
        "keyContents": keyContents
      };
}

class FlutterAliyunLog {
  static bool _hasClient = false;
  static Future create({config: AliyunLogConfig}) async {
    try {
      await _channel.invokeMethod("create", config.getConfig());
      FlutterAliyunLog._hasClient = true;
    } catch (error) {
      throw "[FlutterAliyunLog] failed to create client";
    }
  }

  static Future post({logItem: AliyunLogItem}) async {
    if (FlutterAliyunLog._hasClient == true) {
      try {
        await _channel.invokeMethod("post", logItem.getLogItem());
      } catch (e) {
        throw e;
      }
    } else {
      throw "[FlutterAliyunLog] failed to post log, no client found";
    }
  }
}
