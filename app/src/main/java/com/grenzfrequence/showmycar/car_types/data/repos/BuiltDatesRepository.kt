package com.grenzfrequence.showmycar.car_types.data.repos

import com.grenzfrequence.githubviewerkotlin.webservice.WebServiceConfig
import com.grenzfrequence.showmycar.car_types.data.model.BuiltDatesModel
import com.grenzfrequence.showmycar.webservice.ShowMyCarApi
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject
import kotlin.collections.Map.Entry

/**
 * Created by grenzfrequence on 18.08.17.
 */

class BuiltDatesRepository
@Inject
constructor(private val showMyCarApi: ShowMyCarApi): Repository<BuiltDatesModel>() {

    lateinit var manufacturer: Entry<String, String>
    lateinit var mainType: Entry<String, String>

    override fun nextPage(): Observable<Response<BuiltDatesModel>> {
        return showMyCarApi.getBuiltDates(
                manufacturer.key, mainType.key, currentPageNr, WebServiceConfig.CARTYPES_PAGE_SIZE)
    }
}