package ru.netology.musicapp.activity.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.netology.musicapp.activity.dao.Converters
import ru.netology.musicapp.activity.dao.TrackDao
import ru.netology.musicapp.activity.entity.AlbumEntity
import ru.netology.musicapp.activity.entity.TrackConverter
import ru.netology.musicapp.activity.entity.TrackEntity


@Database(entities = [TrackEntity::class, AlbumEntity::class], version = 36, exportSchema = false)
@TypeConverters(Converters::class, TrackConverter::class)
abstract class TrackDataBase: RoomDatabase() {

    abstract fun getTrackDao(): TrackDao

    companion object {
        private var dataBase: TrackDataBase ?= null

        fun getInstance(context: Context): TrackDataBase {
            return if (dataBase == null) {
                dataBase = Room
                    .databaseBuilder(context, TrackDataBase::class.java, "db")
                    .fallbackToDestructiveMigration()
                    .build()

                dataBase as TrackDataBase
            } else {
                dataBase as TrackDataBase
            }
        }


    }
}