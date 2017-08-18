package com.grenzfrequence.showmycar.car_types.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import com.grenzfrequence.showmycar.car_types.ui.ManufacturerItem
import com.grenzfrequence.showmycar.car_types.ui.base.MvvmViewModelData


/**
 * Created by grenzfrequence on 16.08.17.
 */

class ManufacturersItemViewModel : BaseObservable(), MvvmViewModelData<ManufacturerItem> {

    var manufacturer: ObservableField<String> = ObservableField("")

    override var modelData: ManufacturerItem? = null
        set(value) {
            manufacturer.set(value?.value ?: "")
        }

}