package com.grenzfrequence.showmycar.car_types.data.model.base

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by grenzfrequence on 18.08.17.
 */
@PaperParcel
data class EmptyCarTypesModel(override val wkda: MutableMap<String, String>) : CarTypesModel, PaperParcelable {

    override fun isLastPage(): Boolean = false

    companion object {
        @JvmField val CREATOR = PaperParcelEmptyCarTypesModel.CREATOR
    }
}