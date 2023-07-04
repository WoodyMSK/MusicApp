package ru.netology.musicapp.activity.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.musicapp.R
import ru.netology.musicapp.activity.BASE_URL
import ru.netology.musicapp.activity.dto.TrackX
import ru.netology.musicapp.activity.media.MediaLifecycleObserver
import ru.netology.musicapp.databinding.CardTrackBinding

class TrackAdapter(
    private val mediaObserver: MediaLifecycleObserver,
    private val trackAdapterListener: TrackAdapterListener,
): ListAdapter<TrackX, TrackViewHolder>(TrackDiffCallBack()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = CardTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrackViewHolder(binding, trackAdapterListener, mediaObserver)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}

class TrackViewHolder(
    private val binding: CardTrackBinding,
    private val trackAdapterListener: TrackAdapterListener,
    private val mediaObserver: MediaLifecycleObserver
): RecyclerView.ViewHolder(binding.root) {


    private var playPause: Int = 0

    fun bind(trackX: TrackX) {
        binding.apply {
            trackName.text = trackX.file
            albumName.text = trackX.id.toString()
//            if (trackX.isPlay) fabPlayTrack.setImageResource(R.drawable.ic_pause_24)
//                else fabPlayTrack.setImageResource(R.drawable.ic_play_arrow_24)

            fabPlayTrackXML.setOnClickListener {
                trackAdapterListener.onPlay(trackX)


                if (mediaObserver.player!!.isPlaying) {

                    if (playPause == trackX.id) {
                        mediaObserver.onPause()
//                        stopTrackUpDao(playPause)
                        return@setOnClickListener
                    }

//                    stopTrackUpDao(playPause)
                    mediaObserver.onStop()
                    mediaObserver.fabPlayTrack = fabPlayTrackXML
                    playPause = 0

                    mediaObserver.apply {
//                        Application.resources.openRawResourceFd(R.raw.track1).use { afd ->
//                            stopTrackUpDao(playPause)

                            playPause = trackX.id
                            mediaObserver.player?.setDataSource("$BASE_URL$playPause.mp3")
                            mediaObserver.player?.setOnCompletionListener {
//                                stopTrackUpDao(playPause)
                                mediaObserver.onStop()
                                fabPlayTrack = fabPlayTrackXML
                                mediaObserver.apply {
//                                    getApplication<Application>().resources.openRawResourceFd(R.raw.track1).use { afd ->
                                        playPause = trackX.id
                                        mediaObserver.player?.setDataSource(BASE_URL + "${playPause.inc()}" + ".mp3")
//                                    }

                                }.play()
                                fabPlayTrack = fabPlayTrackXML
//                                playTrackUpDao(trackX.id)

                            }
//                        }
                    }.play()
                    mediaObserver.fabPlayTrack = fabPlayTrackXML
//                    playTrackUpDao(trackX.id)

                } else {

                    if (playPause == trackX.id) {
                        mediaObserver.player!!.start()
//                        playTrackUpDao(trackX.id)
                        return@setOnClickListener
                    }

                    mediaObserver.onStop()
                    mediaObserver.fabPlayTrack = fabPlayTrackXML
                    playPause = 0

                    mediaObserver.apply {
//                        getApplication<Application>().resources.openRawResourceFd(R.raw.track1).use { afd ->
//                            stopTrackUpDao(playPause)

                            playPause = trackX.id
                            mediaObserver.player?.setDataSource("$BASE_URL$playPause.mp3")
                            mediaObserver.player?.setOnCompletionListener {
//                                stopTrackUpDao(playPause)
                                mediaObserver.onStop()
                                fabPlayTrack = fabPlayTrackXML
                                mediaObserver.apply {
//                                    getApplication<Application>().resources.openRawResourceFd(R.raw.track1).use { afd ->
                                        playPause = trackX.id
                                        mediaObserver.player?.setDataSource(BASE_URL + "${playPause.inc()}" + ".mp3")
//                                    }
                                }.play()
                                fabPlayTrack = fabPlayTrackXML
//                                playTrackUpDao(trackX.id)

                            }
//                        }
                    }.play()
                    mediaObserver.fabPlayTrack = fabPlayTrackXML
//                    playTrackUpDao(trackX.id)

                }

            }

        }


    }



}

class TrackDiffCallBack: DiffUtil.ItemCallback<TrackX>() {
    override fun areItemsTheSame(oldItem: TrackX, newItem: TrackX): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TrackX, newItem: TrackX): Boolean {
        return oldItem == newItem
    }
}