package ru.netology.musicapp.activity.activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable.start
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.netology.musicapp.R
import ru.netology.musicapp.activity.BASE_URL
import ru.netology.musicapp.activity.adapter.TrackAdapter
import ru.netology.musicapp.activity.adapter.TrackAdapterListener
import ru.netology.musicapp.activity.api.MusicApi
import ru.netology.musicapp.activity.dto.TrackX
import ru.netology.musicapp.activity.media.MediaLifecycleObserver
import ru.netology.musicapp.activity.viewmodel.TrackViewModel
import ru.netology.musicapp.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {
    private lateinit var mediaObserver: MediaLifecycleObserver
    private val viewModel: TrackViewModel by activityViewModels()
//    private val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


//        viewModel.initDatabase()

        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        mediaObserver = MediaLifecycleObserver(binding.root.context)
        val adapter = TrackAdapter(mediaObserver, object : TrackAdapterListener {



            override fun onPlay(trackX: TrackX) {
                viewModel.playTrack(trackX.id)
            }

            override fun playUpdateDao(id: Int) {
                viewModel.playTrackUpDao(id)
            }

            override fun stopUpdateDao(id: Int) {
                viewModel.stopTrackUpDao(id)
            }
        })

//        private val mediaObserver = MediaLifecycleObserver
        binding.listMusic.adapter = adapter

        viewModel.dataDB.observe(viewLifecycleOwner)
        { list -> adapter.submitList(list) }


//        viewModel.dataAlbumDB.observe(viewLifecycleOwner) { album ->
//
////            adapter.submitList(album.tracks)
//            binding.apply {
//                albumNameInFF.text = album!!.title
//                executorInFF.text = album.artist
//                year.text = album.published
//                style.text = album.genre
//            }
//        }





        binding.fabPlayAlbumXML.setOnClickListener {

//            if (mediaObserver.player != null) {
//                var a = mediaObserver
//                if (mediaObserver.player!!.isPlaying) {
//                    mediaObserver.onPause()
//                } else {
//                    mediaObserver.play()
//                }
//            }


            if (!mediaObserver.player!!.isPlaying) {

                mediaObserver.player!!.start()
                mediaObserver.fabPlayAlbum = binding.fabPlayAlbumXML
                return@setOnClickListener
            } else {

                mediaObserver.player!!.pause()
                mediaObserver.fabPlayAlbum = binding.fabPlayAlbumXML
                return@setOnClickListener
            }

        }




        mediaObserver.fabPlayAlbum = binding.fabPlayAlbumXML



        lifecycle.addObserver(mediaObserver)
        return binding.root


    }

    private fun requestRecordAudioPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_NETWORK_STATE
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_WIFI_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {


            ActivityCompat.requestPermissions(
                requireContext() as Activity, arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_WIFI_STATE,
                ), 0
            )
            return
        }
    }

}

//override fun onPlay(trackX: TrackX) {
//    if (mediaObserver.player!!.isPlaying) {
//
//        if (playPause == trackX.id) {
//            mediaObserver.onPause()
//            stopUpdateDao(playPause)
//            return
//        }
//
//        stopUpdateDao(playPause)
//        mediaObserver.onStop()
//        playPause = 0
//
//        mediaObserver.apply {
//            resources.openRawResourceFd(R.raw.track1).use { afd ->
//                stopUpdateDao(playPause)
//                playPause = trackX.id
//                MediaLifecycleObserver.player?.setDataSource("$BASE_URL$playPause.mp3")
//                MediaLifecycleObserver.player?.setOnCompletionListener {
//                    stopUpdateDao(playPause)
//                    mediaObserver.onStop()
//                    mediaObserver.apply {
//                        resources.openRawResourceFd(R.raw.track1).use { afd ->
//                            playPause = trackX.id
//                            MediaLifecycleObserver.player?.setDataSource(BASE_URL + "${playPause.inc()}" + ".mp3")
//                        }
//                    }.play()
//                    playUpdateDao(trackX.id)
//                }
//            }
//        }.play()
//        playUpdateDao(trackX.id)
//    } else {
//
//        if (playPause == trackX.id) {
//            mediaObserver.player!!.start()
//            playUpdateDao(trackX.id)
//            return
//        }
//
//        mediaObserver.onStop()
//        playPause = 0
//
//        mediaObserver.apply {
//            resources.openRawResourceFd(R.raw.track1).use { afd ->
//                stopUpdateDao(playPause)
//                playPause = trackX.id
//                MediaLifecycleObserver.player?.setDataSource("$BASE_URL$playPause.mp3")
//                MediaLifecycleObserver.player?.setOnCompletionListener {
//                    stopUpdateDao(playPause)
//                    mediaObserver.onStop()
//                    mediaObserver.apply {
//                        resources.openRawResourceFd(R.raw.track1).use { afd ->
//                            playPause = trackX.id
//                            MediaLifecycleObserver.player?.setDataSource(BASE_URL + "${playPause.inc()}" + ".mp3")
//                        }
//                    }.play()
//                    playUpdateDao(trackX.id)
//                }
//            }
//        }.play()
//        playUpdateDao(trackX.id)
//    }
//
//}