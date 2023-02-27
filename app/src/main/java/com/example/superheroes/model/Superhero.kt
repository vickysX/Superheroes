package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Superhero(
    @StringRes val name : Int,
    @StringRes val powers : Int,
    @DrawableRes val image : Int
)
