package ru.netology.musicapp.activity.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.room.PrimaryKey
import retrofit2.Response
import ru.netology.musicapp.activity.api.MusicApi
import ru.netology.musicapp.activity.dao.TrackDao
import ru.netology.musicapp.activity.dto.Track
import ru.netology.musicapp.activity.dto.TrackX
import ru.netology.musicapp.activity.entity.AlbumEntity
import ru.netology.musicapp.activity.entity.TrackEntity
import ru.netology.musicapp.activity.entity.toDto
import ru.netology.musicapp.activity.entity.toEntity
import ru.netology.musicapp.activity.media.MediaLifecycleObserver

class TrackRepositoryImpl(
    private val trackDao: TrackDao,
) : TrackRepository {

    override val allTracks: LiveData<List<TrackX>> = trackDao.getAllTracks().map(List<TrackEntity>::toDto)

    override val album: LiveData<Track> = trackDao.getAlbum().map(AlbumEntity::toDto)
//    override var album: LiveData<Track> = MutableLiveData()
//    .map(AlbumEntity::toDto)

//        override val allTracks: LiveData<List<TrackX>>
//        get() = trackDao.getAllTracks()


    override suspend fun getAllTracks() {
        val response = MusicApi.service.getAllAlbum()

        val bodyTracks = response.body()!!.tracks
        val bodyAlbum = response.body()!!

//        album.value!!.apply {
//            artist = bodyAlbum.artist
//            genre = bodyAlbum.genre
//            id = bodyAlbum.id
//            published = bodyAlbum.published
//            subtitle = bodyAlbum.subtitle
//            title = bodyAlbum.title
//            tracks = emptyList()
//        }





        trackDao.insertTracks(bodyTracks.toEntity())
        trackDao.insertAlbum(AlbumEntity.fromDto(bodyAlbum))
    }

    override suspend fun playTrackUpDao(id: Int) {
        trackDao.playTrackUpDao(id)
    }

    override suspend fun stopTrackUpDao(id: Int) {
        trackDao.stopTrackUpDao(id)
    }
}