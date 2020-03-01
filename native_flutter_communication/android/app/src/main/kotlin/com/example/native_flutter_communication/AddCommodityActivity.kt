package com.example.native_flutter_communication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.native_flutter_communication.channel.CommodityEventChannel
import com.google.gson.JsonObject
import io.flutter.plugin.common.EventChannel
import kotlinx.android.synthetic.main.activity_add_commodity.*

class AddCommodityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_commodity)

        initListener()
    }

    private fun initListener() {
        save_button.setOnClickListener {
            doSave()
            
            finish()
        }
    }

    private fun doSave() {
        val name = name_edit.text.toString()
        val price = price_edit.text.toString()

        when{
            TextUtils.isEmpty(name) -> Toast.makeText(this, "please input name", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(price) -> Toast.makeText(this, "please input price", Toast.LENGTH_SHORT).show()
            else -> doSaveExe(name, price)
        }
    }

    private fun doSaveExe(name: String, price: String) {
        val jsonObject = JsonObject()
        with(jsonObject) {
            addProperty("name", name)
            addProperty("price", price)
        }

        CommodityEventChannel.sendEvent(jsonObject.toString())
    }
}
