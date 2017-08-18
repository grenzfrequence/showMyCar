package com.grenzfrequence.showmycar.car_types.data.Repos

import com.grenzfrequence.githubviewerkotlin.webservice.WebServiceConfig
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
constructor(val showMyCarApi: ShowMyCarApi) : Repository<ManufacturersModel>() {

    override fun nextPage(): Observable<Response<ManufacturersModel>> {
        return showMyCarApi
                .getManufacturer(++currentPageNr, WebServiceConfig.CARTYPES_PAGE_SIZE)
    }
}