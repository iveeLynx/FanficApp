package com.ivan.yaskovskyi.fanficapp.activities.ReadText

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.ivan.yaskovskyi.fanficapp.R

class ReadTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_text)
        val name: TextView = findViewById(R.id.name)
        val text: TextView = findViewById(R.id.text)

        text.movementMethod = ScrollingMovementMethod()
        val intent: Intent = intent
        name.text = intent.getStringExtra("name")
        text.text = intent.getStringExtra("text")
    }
}