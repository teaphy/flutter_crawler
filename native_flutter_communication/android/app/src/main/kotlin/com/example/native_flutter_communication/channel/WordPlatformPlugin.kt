package com.example.native_flutter_communication.channel

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StringCodec

class WordPlatformPlugin {
    companion object {
        var wordMessageChannel: BasicMessageChannel<String>? = null

        fun registerChannel(flutterEngine: FlutterEngine) {
            wordMessageChannel = BasicMessageChannel<String>(flutterEngine.dartExecutor.binaryMessenger,
                    "com.example.native_flutter_communication/word",
                    StringCodec.INSTANCE)
        }
    }
}