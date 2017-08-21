package com.grenzfrequence.showmycar.car_types.data.repos

import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by grenzfrequence on 15.08.17.
 */
abstract class Repository<MODEL> {

    var currentPageNr: Int = -1

    abstract fun nextPage(): Observable<Response<MODEL>>

    fun firstPage(): Observable<Response<MODEL>> {
        currentPageNr = -1
        return nextPage()
    }

}