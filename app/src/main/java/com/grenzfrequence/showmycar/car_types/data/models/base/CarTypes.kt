package com.grenzfrequence.showmycar.car_types.data.models.base

import com.grenzfrequence.showmycar.car_types.data.models.Wkda

/**
 * Created by grenzfrequence on 10.08.17.
 */

abstract class CarTypes(
        val page: Int,
        val pageSize: Int,
        val totalPageCount: Int,
        val wkda: Wkda
)
