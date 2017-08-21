package com.grenzfrequence.showmycar.car_types.events

import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem

/**
 * Created by grenzfrequence on 19.08.17.
 */
class ShowSummarizeEvent(
        val manufacturer: CarTypesItem,
        val mainType: CarTypesItem,
        val builtDate: CarTypesItem)