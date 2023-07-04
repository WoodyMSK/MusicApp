package ru.netology.musicapp.activity.dto

data class TrackX(
    val `file`: String,
    val id: Int,
    var isPlay: Boolean = false,
)

