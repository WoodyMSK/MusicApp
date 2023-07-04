package ru.netology.musicapp.activity.viewmodel

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import ru.netology.musicapp.R
import ru.netology.musicapp.activity.BASE_URL
import ru.netology.musicapp.activity.db.TrackDataBase
import ru.netology.musicapp.activity.dto.Track
import ru.netology.musicapp.activity.dto.TrackX
import ru.netology.musicapp.activity.media.MediaLifecycleObserver
import ru.netology.musicapp.activity.repository.TrackRepository
import ru.netology.musicapp.activity.repository.TrackRepositoryImpl
import java.io.File
import java.io.FileInputStream

class TrackViewModel(application: Application) : AndroidViewModel(application) {
//    private lateinit var mediaObserver: MediaLifecycleObserver
    private val repositoryDao: TrackRepositoryImpl =
        TrackRepositoryImpl(TrackDataBase.getInstance(application).getTrackDao())

    val dataDB: LiveData<List<TrackX>> = repositoryDao.allTracks
    val dataAlbumDB: LiveData<Track> = repositoryDao.album

    var dataTrackFromDB: TrackX = TrackX("", 0)

    var playerCondition = MutableLiveData<Boolean>()


    private var playPause: Int = 0

//    val dataDB: LiveData<List<TrackX>>
//        get() = _dataDB

    init {
        loadTracks()
    }

//    fun initDatabase() {
//        val daoTrack = TrackDataBase.getInstance(context).getTrackDao()
//        repositoryDao = TrackRepositoryImpl(daoTrack)
//    }

    fun loadTracks() = viewModelScope.launch {
//        delay(2000)
        repositoryDao.getAllTracks()
    }

    fun playTrackUpDao(id: Int) = viewModelScope.launch {
        repositoryDao.playTrackUpDao(id)
    }

    fun stopTrackUpDao(id: Int) = viewModelScope.launch {
        repositoryDao.stopTrackUpDao(id)
    }

    fun getPlayerCondition() = viewModelScope.launch(Dispatchers.Default) {
//        val trueorfalse : Boolean = mediaObserver.player!!.isPlaying
    }

    fun playTrack(id: Int) {

//        if (mediaObserver.player!!.isPlaying) {
//
//            if (playPause == id) {
//                mediaObserver.onPause()
//                stopTrackUpDao(playPause)
//                return
//            }
//
//            stopTrackUpDao(playPause)
//            mediaObserver.onStop()
//            playerCondition.value = false
//            playPause = 0
//
//            mediaObserver.apply {
//                getApplication<Application>().resources.openRawResourceFd(R.raw.track1).use { afd ->
//                    stopTrackUpDao(playPause)
//
//                    playPause = id
//                    mediaObserver.player?.setDataSource("$BASE_URL$playPause.mp3")
//                    mediaObserver.player?.setOnCompletionListener {
//                        stopTrackUpDao(playPause)
//                        mediaObserver.onStop()
//
//                        mediaObserver.apply {
//                            getApplication<Application>().resources.openRawResourceFd(R.raw.track1).use { afd ->
//                                playPause = id
//                                mediaObserver.player?.setDataSource(BASE_URL + "${playPause.inc()}" + ".mp3")
//                            }
//                        }.play()
//                        playTrackUpDao(id)
//
//                    }
//                }
//            }.play()
//            playTrackUpDao(id)
//
//        } else {
//
//            if (playPause == id) {
//                mediaObserver.player!!.start()
//                playTrackUpDao(id)
//                return
//            }
//
//            mediaObserver.onStop()
//            playerCondition.value = false
//            playPause = 0
//
//            mediaObserver.apply {
//                getApplication<Application>().resources.openRawResourceFd(R.raw.track1).use { afd ->
//                    stopTrackUpDao(playPause)
//
//                    playPause = id
//                    mediaObserver.player?.setDataSource("$BASE_URL$playPause.mp3")
//                    mediaObserver.player?.setOnCompletionListener {
//                        stopTrackUpDao(playPause)
//                        mediaObserver.onStop()
//
//                        mediaObserver.apply {
//                            getApplication<Application>().resources.openRawResourceFd(R.raw.track1).use { afd ->
//                                playPause = id
//                                mediaObserver.player?.setDataSource(BASE_URL + "${playPause.inc()}" + ".mp3")
//                            }
//                        }.play()
//                        playTrackUpDao(id)
//
//                    }
//                }
//            }.play()
//            playTrackUpDao(id)
//
//        }

    }

}