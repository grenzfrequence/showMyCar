package com.grenzfrequence.showmycar.car_types.ui

import com.grenzfrequence.showmycar.car_types.ui.base.MvvmView
import kotlin.collections.Map.Entry

/**
 * Created by grenzfrequence on 15.08.17.
 */
typealias ManufacturerItem = Entry<String, String>

interface ManufacturersView : MvvmView {

    fun setListItems(listItems: List<ManufacturerItem>, reset: Boolean)
    fun onRefresh(show: Boolean)

}