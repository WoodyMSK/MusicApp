package ru.netology.musicapp.activity.adapter

import ru.netology.musicapp.activity.dto.TrackX

interface TrackAdapterListener {
    fun onPlay(trackX: TrackX)
    fun playUpdateDao(id: Int)
    fun stopUpdateDao(id: Int)
}