package com.trenser.youtubeplayer

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

//        btnPlayVideo.setOnClickListener(this)
//        btnPlayList.setOnClickListener(this)
        val playVideoButton =findViewById<Button>(R.id.btnPlayVideo)
        val playPlayListButton = findViewById<Button>(R.id.btnPlayList)
        playVideoButton?.setOnClickListener(this)
        playPlayListButton.setOnClickListener(this)

//        playVideoButton?.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(p0: View?) {
//                TODO("Not yet implemented")
//            }
//
//        })

//        playVideoButton?.setOnClickListener(View.OnClickListener {v->
//            TODO("Not yet implemented")
//        })

//        val listener = View.OnClickListener { v->
//
//        }
//        playVideoButton?.setOnClickListener(listener)
//        playPlayListButton?.setOnClickListener(listener)
    }

    override fun onClick(v: View?) {
       val intent = when (v?.id){
           R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(this,getString(R.string.GOOGLE_API_KEY),
               YOUTUBE_VIDEO_ID,0,true,false)
           R.id.btnPlayList -> YouTubeStandalonePlayer.createPlaylistIntent(this,getString(R.string.GOOGLE_API_KEY),
               YOUTUBE_PLAYLIST,0,0,true,true)
           else -> throw IllegalAccessException("Undefined button clicked")

       }
        startActivity(intent)
    }
}