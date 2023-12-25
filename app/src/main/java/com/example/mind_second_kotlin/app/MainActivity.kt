package com.example.mind_second_kotlin.app

import RepositoryScore
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mind_second_kotlin.app.router.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RepositoryScore.initialize(this)
        setContent { Routes() }
    }
}