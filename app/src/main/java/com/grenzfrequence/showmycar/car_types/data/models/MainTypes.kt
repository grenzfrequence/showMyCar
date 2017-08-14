package com.grenzfrequence.showmycar.car_types.data.models

import com.grenzfrequence.showmycar.car_types.data.models.base.CarTypes

/**
 * Created by grenzfrequence on 10.08.17.
 */

class MainTypes(
        page: Int,
        pageSize: Int,
        totalPageCount: Int,
        wkda: Wkda) : CarTypes(page, pageSize, totalPageCount, wkda)