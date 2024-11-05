package com.susanafigueroa.exoplayerkotlin

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.susanafigueroa.exoplayerkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(viewBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    public override fun onStart() {
        super.onStart()
        if(Build.VERSION.SDK_INT > 23) {
            initializePlayer()
        }
    }

    // create an exoplayer
    private fun initializePlayer() {
        player = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                viewBinding.videoView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))
                exoPlayer.setMediaItem(mediaItem)
            }
    }
}