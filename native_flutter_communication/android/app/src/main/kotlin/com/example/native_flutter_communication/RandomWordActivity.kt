package com.example.native_flutter_communication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.native_flutter_communication.channel.EditPlatformPlugin
import com.example.native_flutter_communication.channel.WordPlatformPlugin
import io.flutter.plugin.common.BasicMessageChannel
import kotlinx.android.synthetic.main.activity_random_word.*

class RandomWordActivity : AppCompatActivity() {


    private val wordAdapter: WordAdapter by lazy {
        WordAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_word)

        initWidgets()

        subscribeMessage()

//        getData()
    }


    private fun initWidgets() {

        with(word_recycler_view) {
            val manager = LinearLayoutManager(this@RandomWordActivity, LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            adapter = wordAdapter
            addItemDecoration(DividerItemDecoration(this@RandomWordActivity, LinearLayoutManager.VERTICAL))
        }
    }


    private fun subscribeMessage() {
        WordPlatformPlugin.wordMessageChannel?.setMessageHandler { message, reply ->

            Log.e("teaphy", "WordPlatformPlugin - message: $message")

            if (null != message && !TextUtils.isEmpty(message)) {
                wordAdapter.addData(message!!)
                reply.reply("已经收到：$message")
            } else {
                reply.reply("有错了，没有收到数据")
            }
        }
    }

    private fun getData() {
        EditPlatformPlugin.editMethodChannel?.invokeMethod("sendWordData", null)
    }
}
