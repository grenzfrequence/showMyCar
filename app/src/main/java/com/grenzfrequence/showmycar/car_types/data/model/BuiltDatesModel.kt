package com.grenzfrequence.showmycar.car_types.data.model

import com.grenzfrequence.showmycar.car_types.data.model.base.CarTypesModel
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by grenzfrequence on 10.08.17.
 */

@PaperParcel
data class BuiltDatesModel(override val wkda: MutableMap<String, String>) : CarTypesModel, PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelBuiltDatesModel.CREATOR
    }

    override fun isLastPage(): Boolean {
        return !wkda.isEmpty()
    }
}