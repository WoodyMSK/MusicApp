package ru.netology.musicapp.activity.entity

import androidx.room.*
import com.google.gson.Gson
import ru.netology.musicapp.activity.activity.AppActivity
import ru.netology.musicapp.activity.dto.Track
import ru.netology.musicapp.activity.dto.TrackX

@Entity(tableName = "track_table")
data class TrackEntity(
    val `file`: String,
    @PrimaryKey
    val id: Int,
    var isPlay: Boolean = false
) {
    fun toDto() = TrackX(`file`, id, isPlay)

    companion object {
        fun fromDto(dto: TrackX) =
            TrackEntity(dto.file, dto.id, dto.isPlay)
    }
}

fun List<TrackEntity>.toDto(): List<TrackX> = map(TrackEntity::toDto)
fun List<TrackX>.toEntity(): List<TrackEntity> = map(TrackEntity::fromDto)


