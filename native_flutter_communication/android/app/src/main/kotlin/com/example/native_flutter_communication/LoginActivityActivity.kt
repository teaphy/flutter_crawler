package com.example.native_flutter_communication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login_activity.*

class LoginActivityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)

        initWidget()
    }

    private fun initWidget() {

        val bundle = intent.extras

        bundle?.apply {
            name_edit.setText(getString("name"))
            mobile_edit.setText(getString("mobile"))
        }

        login_button.setOnClickListener {
            val name = name_edit.text.toString();
            val mobile = mobile_edit.text.toString();

            val intent = Intent()
            val bundle = Bundle()

            with(bundle) {
                putString("name", name)
                putString("mobile", mobile)
            }

            intent.putExtras(bundle)

            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }
}
