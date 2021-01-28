package com.raag.superheroes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    var name: String,
    var alterEgo: String,
    var bio: String,
    var power: Double
) : Parcelable