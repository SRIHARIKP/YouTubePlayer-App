package com.trenser.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnplaySingle.setOnClickListener(this)
        btnStandalone.setOnClickListener(this)

    }

    override fun onClick(v : View){
        val intent = when (v.id) {
            R.id.btnplaySingle -> Intent(this, YouTubeActivity::class.java)
            R.id.btnStandalone -> Intent(this,StandaloneActivity::class.java)
            else -> throw IllegalAccessException("Undefined button clicked")
        }
        startActivity(intent)
    }

}