package com.grenzfrequence.showmycar.car_types.ui.base

import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem

/**
 * Created by grenzfrequence on 15.08.17.
 */

interface CarTypesView : MvvmView {

    fun setListItems(listItems: List<CarTypesItem>, reset: Boolean)
    fun onRefresh(show: Boolean)

}