package com.grenzfrequence.showmycar.car_types.data.model.base

import android.os.Parcelable

/**
 * Created by grenzfrequence on 18.08.17.
 */
interface CarTypesModel : Parcelable {
    val wkda: MutableMap<String, String>

    fun isLastPage(): Boolean
}