package com.example.native_flutter_communication

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    private val CHANNEL = "com.example.native_flutter_communication/methodChannelAty"

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {

        GeneratedPluginRegistrant.registerWith(flutterEngine);

        registerMessageChannel(flutterEngine)
    }

    // 在onCreate里创建一个MethodChannel并在configureFlutterEngine()方法内设置MethodCallHandler。
    // 确保使用与在Flutter客户端使用的channel名称相同。
    private fun registerMessageChannel(flutterEngine: FlutterEngine) {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call, result ->
                    // // this method is invoked on the main thread.
                    if (call.method == "methodChannelAty") {
                        Log.e("teaphy", "registerMessageChannel")
                        result.success("I'm the message from Android, at ${System.currentTimeMillis()}")
                    } else {
                        result.notImplemented()
                    }
                }
    }
}
