package com.grenzfrequence.showmycar.car_types.data.models

import com.grenzfrequence.showmycar.car_types.data.models.base.CarTypes

/**
 * Created by grenzfrequence on 13.08.17.
 */

class Manufacturers(
        page: Int,
        pageSize: Int,
        totalPageCount: Int,
        wkda: Wkda) : CarTypes(page, pageSize, totalPageCount, wkda)