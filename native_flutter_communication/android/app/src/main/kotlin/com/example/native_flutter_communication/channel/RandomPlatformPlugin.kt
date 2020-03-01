package com.example.native_flutter_communication.channel

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.common.StringCodec

class RandomPlatformPlugin {
    companion object {
        var randomMessageChannel: BasicMessageChannel<Any>? = null

        fun registerChannel(flutterEngine: FlutterEngine) {
            randomMessageChannel = BasicMessageChannel(flutterEngine.dartExecutor.binaryMessenger,
                    "com.example.native_flutter_communication/random",
                    StandardMessageCodec.INSTANCE)
        }
    }
}