package com.example.broadcastbestpractice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val login : Button = findViewById(R.id.login)
        val accountEdit : EditText = findViewById(R.id.accountEdit)
        val rememberPass : CheckBox = findViewById(R.id.rememberPass)
        val passwordEdit : EditText = findViewById(R.id.passwordEdit)
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean("remember_password",false)
        if(isRemember){
            //将账号和密码都设置到文本框中
            val account = prefs.getString("account","")
            val password = prefs.getString("password","")
            accountEdit.setText(account)
            passwordEdit.setText(password)
            rememberPass.isChecked = true
        }

        login.setOnClickListener{
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()

            if(account == "admin" && password == "123456"){
                val editor = prefs.edit()
                if(rememberPass.isChecked){
                    editor.putBoolean("remember_password",true)
                    editor.putString("account",account)
                    editor.putString("password",password)
                }else{
                    editor.clear()
                }
                editor.apply()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"account or password is invaild",Toast.LENGTH_SHORT).show()
            }
        }
    }
}