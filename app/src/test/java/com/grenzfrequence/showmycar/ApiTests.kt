package com.grenzfrequence.showmycar

import com.grenzfrequence.showmycar.base.BaseUnitTest
import com.grenzfrequence.showmycar.car_types.data.models.BuiltDates
import com.grenzfrequence.showmycar.car_types.data.models.MainTypes
import com.grenzfrequence.showmycar.car_types.data.models.Manufacturers
import com.grenzfrequence.showmycar.webservice.ShowMyCarApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response

/**
 * Created by grenzfrequence on 14.08.17.
 */


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(23))
class ApiTests : BaseUnitTest() {
    val showMyCarApi: ShowMyCarApi by lazy { fragmentComponent.showMyCarApi() }

    @Test
    fun getManufacturersTest() {
        val manufacturersResponse = apiGetManufacturers()
        assertThat(manufacturersResponse.raw().code()).isEqualTo(200)
        assertThat(manufacturersResponse.body().wkda.isEmpty()).isFalse()
    }

    @Test
    fun getMainTypesTest() {
        val manufacturer = apiGetManufacturers().body().wkda.keys.first()
        val mainTypesResponse = apiGetMainTypes(manufacturer)
        assertThat(mainTypesResponse.raw().code()).isEqualTo(200)
        assertThat(mainTypesResponse.body().wkda.isEmpty()).isFalse()
    }

    @Test
    fun getBuiltDatesTest() {
        val manufacturer = apiGetManufacturers().body().wkda.keys.first()
        val mainType = apiGetMainTypes(manufacturer).body().wkda.keys.first()
        val builtDatesResponse: Response<BuiltDates> = apiGetBuiltDates(manufacturer, mainType)
        assertThat(builtDatesResponse.raw().code()).isEqualTo(200)
        assertThat(builtDatesResponse.body().wkda.isEmpty()).isFalse()
    }

    private fun apiGetManufacturers(): Response<Manufacturers> {
        val response: Response<Manufacturers> = showMyCarApi.getManufacturer(0, 10)
                .blockingSingle()
        return response
    }

    private fun apiGetMainTypes(manufacturer: String): Response<MainTypes> {
        val response: Response<MainTypes> = showMyCarApi.getMainTypes(manufacturer, 0, 10)
                .blockingSingle()
        return response
    }

    private fun apiGetBuiltDates(manufacturer: String, mainType: String): Response<BuiltDates> {
        val response: Response<BuiltDates> = showMyCarApi.getBuiltDates(manufacturer, mainType, 0, 10)
                .blockingSingle()
        return response
    }
}