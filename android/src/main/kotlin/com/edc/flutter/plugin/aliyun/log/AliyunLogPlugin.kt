package com.edc.flutter.plugin.aliyun.log

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterAliyunLogPlugin : MethodCallHandler {

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_aliyun_log")
            channel.setMethodCallHandler(FlutterAliyunLogPlugin())
        }
    }

    private val aliyunLog: AliyunLog = AliyunLog()

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "create") {
            val argument: InitParam = InitParam.fromMap(call.arguments as Map<String, String>)
            aliyunLog.create(argument)
            result.success(argument.toMap())
        } else if (call.method == "post") {
            val argument: PostParam = PostParam.fromMap(call.arguments as Map<String, Object>)
            aliyunLog.post(argument)
            result.success(argument.toMap())
        } else {
            result.notImplemented()
        }
    }
}
