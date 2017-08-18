package com.grenzfrequence.showmycar.car_types.data.Repos

import io.reactivex.Observable
import retrofit2.Response

/**
 * Created by grenzfrequence on 15.08.17.
 */
interface Repository<MODEL> {

    fun nextPage(): Observable<Response<MODEL>>
    fun firstPage(): Observable<Response<MODEL>>

}