package ru.netology.musicapp.activity.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.musicapp.activity.dto.Track
import ru.netology.musicapp.activity.dto.TrackX
import ru.netology.musicapp.activity.entity.AlbumEntity
import ru.netology.musicapp.activity.entity.TrackEntity

import java.net.IDN

@Dao
interface TrackDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTrack(trackX: TrackEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(trackX: List<TrackEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(track: AlbumEntity)

    @Query("UPDATE track_table SET isPlay = true WHERE id = :id")
    suspend fun playTrackUpDao(id: Int)

    @Query("UPDATE track_table SET isPlay = false WHERE id = :id")
    suspend fun stopTrackUpDao(id: Int)

//    @Delete
//    suspend fun deleteTrack(trackX: TrackEntity)

    @Query("SELECT * FROM track_table")
    fun getAllTracks(): LiveData<List<TrackEntity>>

    @Query("SELECT * FROM album_table")
    fun getAlbum(): LiveData<AlbumEntity>

//    @Query("SELECT * FROM track_table WHERE id = :id")
//    fun getTrackById(id: Int): TrackEntity
}

class Converters {
    @TypeConverter
    fun listToJson(value: List<TrackX>?) = Gson().toJson(value)
    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<TrackX>::class.java).toList()
//    @TypeConverter
//    fun mapListToString(value: TrackXEmbeddableList): String {
//        val gson = Gson()
//        val type = object : TypeToken<TrackXEmbeddableList>() {}.type
//        return gson.toJson(value, type)
//    }
//
//    @TypeConverter
//    fun mapStringToList(value: String): TrackXEmbeddableList {
//        val gson = Gson()
//        val type = object : TypeToken<TrackXEmbeddableList>() {}.type
//        return gson.fromJson(value, type)
//    }

//    @TypeConverter
//    fun fromTrackXEmbeddableToJSON(trackXEmbeddableList: TrackXEmbeddableList): String {
//        return Gson().toJson(trackXEmbeddableList)
//    }
//    @TypeConverter
//    fun fromJSONToTrackXEmbeddable(json: String): TrackXEmbeddableList {
//        return Gson().fromJson(json,TrackXEmbeddableList::class.java)
//    }

}
//    @TypeConverter
//    fun listToJson1(value: List<TrackXEmbeddable>?) = Gson().toJson(value)
//    @TypeConverter
//    fun jsonToList1(value: String) = Gson().fromJson(value, Array<TrackXEmbeddable>::class.java).toList()
