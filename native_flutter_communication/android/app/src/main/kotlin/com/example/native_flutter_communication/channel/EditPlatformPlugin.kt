package com.example.native_flutter_communication.channel

import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

class EditPlatformPlugin {
    companion object {
        private const val CHANNEL = "com.example.native_flutter_communication/edit"

        var editMethodChannel: MethodChannel?  = null

        fun registerChannel(flutterEngine: FlutterEngine) {
            editMethodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        }
    }
}