package ru.netology.musicapp.activity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.musicapp.R

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
    }
}