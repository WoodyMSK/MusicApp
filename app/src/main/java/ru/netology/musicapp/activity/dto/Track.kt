package ru.netology.musicapp.activity.dto

import androidx.room.Embedded
import androidx.room.Ignore

data class Track(
    var artist: String,
    var genre: String,
    var id: Int,
    var published: String,
    var subtitle: String,
    var title: String,
    var tracks: List<TrackX>
)