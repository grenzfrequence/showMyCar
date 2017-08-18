package com.grenzfrequence.showmycar.car_types.data.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by grenzfrequence on 13.08.17.
 */
typealias Wkda = MutableMap<String, String>

@PaperParcel
data class ManufacturersModel(
        val page: Int,
        val pageSize: Int,
        val totalPageCount: Int,
        val wkda: Wkda) : PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelManufacturersModel.CREATOR
    }

    fun isLastPage() : Boolean = page >= totalPageCount - 1
}