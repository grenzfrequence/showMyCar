package com.grenzfrequence.showmycar.webservice

import com.grenzfrequence.showmycar.car_types.data.models.BuiltDates
import com.grenzfrequence.showmycar.car_types.data.models.MainTypes
import com.grenzfrequence.showmycar.car_types.data.models.Manufacturers
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by grenzfrequence on 10.08.17.
 */

interface ShowMyCarApi {


    // http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/manufacturer?page=0&pageSize=10&wa_key=coding-puzzle-client-449cc9d
    @GET("v1/car-types/manufacturer")
    fun getManufacturer(
            @Query("page") pageNr: Int,
            @Query("pageSize") itemsPerPage: Int): Observable<Response<Manufacturers>>

    // http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/main-types?manufacturer=107&page=0&pageSize=10&wa_key=coding-puzzle-client-449cc9d
    @GET("v1/car-types/main-types")
    fun getMainTypes(
            @Query("manufacturer") manufacturer: String,
            @Query("page") pageNr: Int,
            @Query("pageSize") itemsPerPage: Int): Observable<Response<MainTypes>>

    // http://api-aws-eu-qa-1.auto1-test.com/v1/car-types/built-dates?manufacturer=107&main-type=Arnage&page=0&pageSize=10&wa_key=coding-puzzle-client-449cc9d
    @GET("v1/car-types/built-dates")
    fun getBuiltDates(
            @Query("manufacturer") manufacturer: String,
            @Query("main-type") mainType: String,
            @Query("page") pageNr: Int,
            @Query("pageSize") itemsPerPage: Int): Observable<Response<BuiltDates>>

}