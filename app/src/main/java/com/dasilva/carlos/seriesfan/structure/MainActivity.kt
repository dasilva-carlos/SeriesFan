package com.dasilva.carlos.seriesfan.structure

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dasilva.carlos.seriesfan.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_SeriesFan)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
