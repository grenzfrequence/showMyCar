package com.grenzfrequence.showmycar.car_types.data.Repos

import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
import com.grenzfrequence.showmycar.webservice.ShowMyCarApi
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by grenzfrequence on 15.08.17.
 */

class ManufacturersRepository
@Inject
constructor(val showMyCarApi: ShowMyCarApi) : Repository<ManufacturersModel> {

    companion object {
        const val PAGE_SIZE = 15
    }

    var currentPageNr: Int = -1

    override fun nextPage(): Observable<Response<ManufacturersModel>> {
        return showMyCarApi
                .getManufacturer(++currentPageNr, PAGE_SIZE)
    }

    override fun firstPage(): Observable<Response<ManufacturersModel>> {
        currentPageNr = -1
        return nextPage()
    }

}