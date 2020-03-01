package com.example.native_flutter_communication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.example.native_flutter_communication.channel.EditPlatformPlugin
import com.example.native_flutter_communication.model.AccountModel
import com.google.gson.Gson
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.Result
import kotlinx.android.synthetic.main.activity_account_edit.*

class AccountEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_edit)

        initData()

        initWidget()
    }

    private fun initData() {
        getData()
    }

    private fun initWidget() {

        save_button.setOnClickListener {
            finish()
        }
    }


    private fun getData() {

        EditPlatformPlugin.editMethodChannel?.invokeMethod("getAccountData", "call the method to get account data", object: MethodChannel.Result{
            override fun notImplemented() {
                Log.e("teaphy", "notImplemented")
            }

            override fun error(errorCode: String?, errorMessage: String?, errorDetails: Any?) {
                Log.e("teaphy", "error: $errorMessage")
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun success(result: Any?) {
                Log.e("teaphy", "success: $result")
                if (null != result && !TextUtils.isEmpty(result.toString())) {
                    val accountModel = Gson().fromJson<AccountModel>(result.toString(), AccountModel::class.java)
                    name_edit.setText(accountModel.name)
                    mobile_edit.setText(accountModel.mobile)
                }
            }
        })
    }

}
