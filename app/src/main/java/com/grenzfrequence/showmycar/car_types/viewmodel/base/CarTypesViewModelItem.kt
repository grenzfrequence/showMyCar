package com.grenzfrequence.showmycar.car_types.viewmodel.base

import android.databinding.BaseObservable
import android.databinding.ObservableField
import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem
import com.grenzfrequence.showmycar.car_types.ui.base.MvvmViewModelItem


/**
 * Created by grenzfrequence on 16.08.17.
 */

abstract class CarTypesViewModelItem : BaseObservable(), MvvmViewModelItem<CarTypesItem> {

    var listItem: ObservableField<String> = ObservableField("")

    override var modelData: CarTypesItem? = null
        set(value) {
            field = value
            listItem.set(value?.value ?: "")
        }

    abstract fun onClick()

}