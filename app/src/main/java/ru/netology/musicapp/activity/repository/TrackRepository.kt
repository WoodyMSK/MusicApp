package ru.netology.musicapp.activity.repository

import androidx.lifecycle.LiveData
import retrofit2.Response
import ru.netology.musicapp.activity.api.MusicApi
import ru.netology.musicapp.activity.dto.Track
import ru.netology.musicapp.activity.dto.TrackX
import ru.netology.musicapp.activity.entity.AlbumEntity
import ru.netology.musicapp.activity.entity.TrackEntity

interface TrackRepository {

    val allTracks: LiveData<List<TrackX>>
    val album: LiveData<Track>
    suspend fun getAllTracks()
    suspend fun playTrackUpDao(id: Int)
    suspend fun stopTrackUpDao(id: Int)
//    suspend fun getTrackById(id: Int): TrackX
}