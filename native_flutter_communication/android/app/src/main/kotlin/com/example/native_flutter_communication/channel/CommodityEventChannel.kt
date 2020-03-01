package com.example.native_flutter_communication.channel

import android.util.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel

class CommodityEventChannel {
    companion object{
        private const val CHANNEL = "com.example.native_flutter_communication/cmmodity"

        var commodityEventChannel: EventChannel? = null

        var _eventSink: EventChannel.EventSink? = null

        fun registerChannel(flutterEngine: FlutterEngine) {
            commodityEventChannel = EventChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)

            commodityEventChannel?.setStreamHandler(object : EventChannel.StreamHandler{
                /**
                 * 处理建立事件Stream的请求。
                 * arguments：Stream订阅时，传递的参数
                 * events: EventSink用于向Flutter分发数据
                 */
                override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                    Log.e("teaphy", "setStreamHandler onListen - arguments: $arguments")
                    _eventSink = events;
                }

                /**
                 * 处理一个销毁最近创建的事件Stream的请求，。
                 */
                override fun onCancel(arguments: Any?) {
                    Log.e("teaphy", "setStreamHandler onCancel - arguments: $arguments")
                }

            })
        }

        fun sendEvent(any: Any?) {
            _eventSink?.success(any)
        }
    }
}