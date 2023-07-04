package ru.netology.musicapp.activity.media

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.OnConflictStrategy
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.netology.musicapp.R

class MediaLifecycleObserver(
    private val context: Context
) : LifecycleObserver {


    var player: MediaPlayer? = MediaPlayer()
    var fabPlayTrack: MaterialButton? = null
    var fabPlayAlbum: MaterialButton? = null

    fun play() {
        player?.setOnPreparedListener {
            it.start()
        }
        player?.prepareAsync()

        fabPlayTrack?.icon = AppCompatResources.getDrawable(
            context,
            R.drawable.ic_baseline_pause_circle_filled_32
        )
        fabPlayAlbum?.icon = AppCompatResources.getDrawable(
            context,
            R.drawable.ic_baseline_pause_circle_filled_64
        )

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        player?.pause()
        fabPlayTrack?.icon = AppCompatResources.getDrawable(
            context,
            R.drawable.ic_baseline_play_circle_filled_32
        )
        fabPlayAlbum?.icon = AppCompatResources.getDrawable(
            context,
            R.drawable.ic_baseline_play_circle_filled_64
        )

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        player?.stop()
        player?.release()
        player = null
        player = MediaPlayer()
        fabPlayTrack?.icon = AppCompatResources.getDrawable(
            context,
            R.drawable.ic_baseline_play_circle_filled_32
        )
        fabPlayAlbum?.icon = AppCompatResources.getDrawable(
            context,
            R.drawable.ic_baseline_play_circle_filled_64
        )
    }


}


//class MediaLifecycleObserver: LifecycleObserver {
//
//    companion object {
//
//        var player: MediaPlayer? = MediaPlayer()
//
//        fun play() {
//            player?.setOnPreparedListener {
//                it.start()
//            }
//            player?.prepareAsync()
//        }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//        fun onPause() {
//            player?.pause()
//        }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//        fun onStop() {
//            player?.release()
//            player = null
//        }
//    }
//}