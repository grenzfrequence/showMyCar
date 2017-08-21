package com.grenzfrequence.showmycar.car_types.data.model

import com.grenzfrequence.showmycar.car_types.data.model.base.CarTypesModel
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by grenzfrequence on 13.08.17.
 */
@PaperParcel
data class ManufacturersModel(
        val page: Int,
        val pageSize: Int,
        val totalPageCount: Int,
        override val wkda: MutableMap<String, String>) : CarTypesModel, PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelManufacturersModel.CREATOR
    }

    override fun isLastPage() : Boolean = page >= totalPageCount - 1
}