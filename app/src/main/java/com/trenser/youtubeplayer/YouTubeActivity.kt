package com.trenser.youtubeplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "34Na4j8AVgA"
const val YOUTUBE_PLAYLIST = "PLK2zhq9oy0K4OgF8a_ZR5lCWd1N6qKJ5t"

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val TAG = "YoutubeActivity"
    val REQUEST_CODE = 0
    val playerView by lazy { YouTubePlayerView(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_you_tube)
//        val layout=findViewById<ConstraintLayout>(R.id.activity_you_tube)
        val layout = layoutInflater.inflate(R.layout.activity_you_tube,null,) as ConstraintLayout
        setContentView(layout)

//        val button = Button(this)
//        button.layoutParams = ConstraintLayout.LayoutParams(600,400)
//        button.text = "Button added"
//        layout.addView(button)

        playerView.layoutParams = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY),this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
       Log.d(TAG,"onInitializationsuccess : provider${provider?.javaClass}")
        Log.d(TAG,"onInitializationsuccess : youtubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this,"Initialised youtube player successfully",Toast.LENGTH_LONG).show()
            youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
            youTubePlayer?.setPlaybackEventListener(playbackListener)

        if (!wasRestored) {
            youTubePlayer?.cuePlaylist(YOUTUBE_PLAYLIST)
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        YouTubeInitializationResult: YouTubeInitializationResult?
    ) {


        if (YouTubeInitializationResult?.isUserRecoverableError == true) {
            YouTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show()
        }else {
            val errorMesage="There was an error in initializing youtube video ($YouTubeInitializationResult)"
            Toast.makeText(this,errorMesage,Toast.LENGTH_LONG).show()

        }
    }

    private val playbackListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@YouTubeActivity,"The video is playing good",Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YouTubeActivity," Video has Paused",Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YouTubeActivity," Video has Stopped",Toast.LENGTH_SHORT).show()
        }

        override fun onBuffering(p0: Boolean) {
//            TODO("Not yet implemented")
        }

        override fun onSeekTo(p0: Int) {
//            TODO("Not yet implemented")
        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {
//            TODO("Not yet implemented")
        }

        override fun onLoaded(p0: String?) {
//            TODO("Not yet implemented")
        }

        override fun onAdStarted() {
            Toast.makeText(this@YouTubeActivity,"watching Ad",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YouTubeActivity," Video has started",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YouTubeActivity," Completed the video ",Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
//            TODO("Not yet implemented")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG,"onActivity called")
        if(requestCode == REQUEST_CODE){
            Log.d(TAG, intent!!.toString())
            Log.d(TAG, intent!!.extras.toString())
            playerView.initialize(getString(R.string.GOOGLE_API_KEY),this)
        }
    }
}