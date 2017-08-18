package com.grenzfrequence.showmycar.common

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes


/**
 * Created by grenzfrequence on 14.08.17.
 */

data class ErrMsg (

    @field:StringRes
    val errorMessageId: Int,

    @field:DrawableRes
    val errorIconId: Int
)