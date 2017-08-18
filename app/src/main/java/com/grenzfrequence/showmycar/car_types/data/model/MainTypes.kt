package com.grenzfrequence.showmycar.car_types.data.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by grenzfrequence on 10.08.17.
 */

@PaperParcel
data class MainTypes(
        val page: Int,
        val pageSize: Int,
        val totalPageCount: Int,
        val wkda: Wkda) : PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelMainTypes.CREATOR
    }
}