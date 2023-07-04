package ru.netology.musicapp.activity.entity

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import ru.netology.musicapp.activity.dto.Track
import ru.netology.musicapp.activity.dto.TrackX

@Entity(tableName = "album_table")
data class AlbumEntity(
    val artist: String,
    val genre: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val published: String,
    val subtitle: String,
    val title: String,
//    @Embedded
//    @Ignore
    var tracks: List<TrackX>,
) {


    fun toDto() = Track(artist, genre, id, published, subtitle, title, tracks)

//    tracksEmb.tooDto()
//    tracks!!.map { it.toDto() }

    companion object {
        fun fromDto(dto: Track) =
            AlbumEntity(
                dto.artist,
                dto.genre,
                dto.id,
                dto.published,
                dto.subtitle,
                dto.title,
                dto.tracks,
//                emptyList()
            )
    }
}

data class TrackXEmbeddable(
    val trackXEmbeddable: TrackX
)

class TrackConverter {
    @TypeConverter
    fun TrackToJson(value: List<TrackXEmbeddable>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToTrack(value: String) = Gson().fromJson(value, Array<TrackXEmbeddable>::class.java).toList()
}


//fun List<TrackXEmbeddable>.tooDto(): List<TrackX> = map(TrackXEmbeddable::toDto)
//fun List<TrackX>.tooEntity(): List<TrackXEmbeddable> = map(TrackXEmbeddable::fromDto)

//fun toEntityAlbum(track: Track): AlbumEntity = Track(track.artist)


