package com.example.firabesewallpapers.model

import com.google.firebase.Timestamp


data class WallpapersModel (
    val name: String = "",
    val image: String = "",
    val data: Timestamp? = null
        )