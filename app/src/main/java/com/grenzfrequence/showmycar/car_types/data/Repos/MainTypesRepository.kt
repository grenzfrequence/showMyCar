package com.grenzfrequence.showmycar.car_types.data.Repos

import com.grenzfrequence.githubviewerkotlin.webservice.WebServiceConfig
import com.grenzfrequence.showmycar.car_types.data.model.MainTypesModel
import com.grenzfrequence.showmycar.webservice.ShowMyCarApi
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.Map.Entry

/**
 * Created by grenzfrequence on 18.08.17.
 */

class MainTypesRepository
@Inject
constructor(val showMyCarApi: ShowMyCarApi) : Repository<MainTypesModel>() {

    lateinit var manufacturer: Entry<String, String>

    override fun nextPage(): Observable<Response<MainTypesModel>> {
        return showMyCarApi.getMainTypes(manufacturer.key, ++currentPageNr, WebServiceConfig.CARTYPES_PAGE_SIZE)
    }
}
