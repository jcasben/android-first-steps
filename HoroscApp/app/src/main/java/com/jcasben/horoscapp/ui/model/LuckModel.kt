package com.jcasben.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LuckModel(
    @DrawableRes val image: Int,
    @StringRes val text: Int
)