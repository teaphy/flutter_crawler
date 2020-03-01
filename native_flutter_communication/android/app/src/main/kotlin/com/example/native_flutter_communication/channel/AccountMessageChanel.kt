package com.example.native_flutter_communication.channel

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

class AccountMessageChanel : MethodChannel.MethodCallHandler {

    companion object {

        private const val CHANNEL = "com.example.native_flutter_communication/account"

        fun registerChannel(flutterEngine: FlutterEngine) {
            MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                    .setMethodCallHandler(AccountMessageChanel())
        }
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {

    }
}