package com.example.native_flutter_communication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.annotation.NonNull
import com.example.native_flutter_communication.channel.CommodityEventChannel
import com.example.native_flutter_communication.channel.EditPlatformPlugin
import com.example.native_flutter_communication.channel.RandomPlatformPlugin
import com.example.native_flutter_communication.channel.WordPlatformPlugin
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.JSONUtil
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import org.json.JSONObject
import java.util.*

class MainActivity : FlutterActivity() {

    private val CHANNEL = "com.example.native_flutter_communication/account"

    var loginResult: MethodChannel.Result? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0x01 && null != data) {
            handleLogin(data)
        }
    }


    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {

        GeneratedPluginRegistrant.registerWith(flutterEngine);

        registerMessageChannel(flutterEngine)

        EditPlatformPlugin.registerChannel(flutterEngine)

        WordPlatformPlugin.registerChannel(flutterEngine)

        RandomPlatformPlugin.registerChannel(flutterEngine)

        CommodityEventChannel.registerChannel(flutterEngine)
    }

    // 在onCreate里创建一个MethodChannel并在configureFlutterEngine()方法内设置MethodCallHandler。
    // 确保使用与在Flutter客户端使用的channel名称相同。
    private fun registerMessageChannel(flutterEngine: FlutterEngine) {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call, result ->
                    // // this method is invoked on the main thread.
                    when (call.method) {
                        "gotoLogin" -> {
                            loginResult = result
                            gotoLoginActivity(call.arguments)
                        }
                        "gotoEditAccount" -> startActivity(Intent(this, AccountEditActivity::class.java))
                        "gotoRandomWord" -> startActivity(Intent(this, RandomWordActivity::class.java))
                        "gotoAddCommodity" -> startActivity(Intent(this, AddCommodityActivity::class.java))
                        "notifySendRandom" -> startSendRandom()
                        else -> {
                            result.notImplemented()
                        }
                    }
                }
    }

    private fun gotoLoginActivity(arguments: Any?) {

        Log.e("teaphy", "From Flutter；$arguments")

        val intent = Intent(this, LoginActivityActivity::class.java)

        if (null != arguments && !TextUtils.isEmpty(arguments.toString())) {
            arguments?.apply {
                val jsonObject = JSONObject(this.toString())
                val bundle = Bundle()

                with(bundle) {
                    putString("name", jsonObject.getString("name"))
                    putString("mobile", jsonObject.getString("mobile"))
                }

                intent.putExtras(bundle)
            }
        }


        startActivityForResult(intent, 0x01)

    }


    private fun handleLogin(data: Intent) {
        val bundle = data.extras

        bundle?.apply {
            val name = getString("name", "")
            val mobile = getString("mobile", "")

            val jsonObject = JSONObject()

            with(jsonObject) {
                put("name", name)
                put("mobile", mobile)
            }

            loginResult?.success(jsonObject.toString())
        }
    }

    /**
     * 开始发射Random
     */
    private fun startSendRandom() {
        Log.e("teaphy", "startSendRandom")
        Thread(Runnable {
            var count = 0
            while (true) {
                runOnUiThread {
                    // send方法必须在主线程调用。如果在子线程调用，会报错
                    RandomPlatformPlugin.randomMessageChannel?.send(Random().nextInt(1000)) {
                        Log.e("teaphy", "sendRandom reply: $it")
                    }
                }
                count++;
                if (count > 100) {
                    break
                }

                Thread.sleep(1000)
            }
        }).start()
    }

}
