package com.grenzfrequence.showmycar.car_types.data.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by grenzfrequence on 21.08.17.
 */
@PaperParcel
data class SummarizeModel(
        var manufacturer: String,
        var mainType: String,
        var builtDate: String) : PaperParcelable {
            companion object {
                @JvmField val CREATOR = PaperParcelSummarizeModel.CREATOR
            }
        }