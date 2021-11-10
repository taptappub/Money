package io.taptap.moneysample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import io.taptap.money.setMoneyTextWatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<EditText>(R.id.edittext1)
            .setMoneyTextWatcher()
        findViewById<EditText>(R.id.edittext2)
            .setMoneyTextWatcher()
    }
}