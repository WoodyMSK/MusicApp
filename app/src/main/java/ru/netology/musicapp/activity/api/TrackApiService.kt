package ru.netology.musicapp.activity.api

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import ru.netology.musicapp.activity.BASE_URL
import ru.netology.musicapp.activity.dto.Track
import ru.netology.musicapp.activity.dto.TrackX

private val logging = HttpLoggingInterceptor().apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okhttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okhttp)
        .build()


interface TrackApiService {
    @GET("album.json")
    suspend fun getAllAlbum(): Response<Track>

//    @GET("{id}.mp3")
//    suspend fun getTrackById(@Path("id") id: Int): Response<TrackX>



}

object MusicApi {
    val service: TrackApiService by lazy {
        retrofit.create(TrackApiService::class.java)
    }
}