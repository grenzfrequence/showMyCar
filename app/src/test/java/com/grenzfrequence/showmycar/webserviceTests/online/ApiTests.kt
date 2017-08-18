package com.grenzfrequence.showmycar.webserviceTests.online

import com.grenzfrequence.showmycar.BuildConfig
import com.grenzfrequence.showmycar.base.BaseUnitTest
import com.grenzfrequence.showmycar.car_types.data.model.BuiltDatesModel
import com.grenzfrequence.showmycar.car_types.data.model.MainTypesModel
import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
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
        val response = apiGetManufacturers()
        assertThat(response.raw().code()).describedAs("%s", response.errorBody()).isEqualTo(200)
        assertThat(response.body().wkda.isEmpty()).isFalse()
    }

    @Test
    fun getMainTypesTest() {
        val manufacturer = apiGetManufacturers().body().wkda.keys.first()
        val response = apiGetMainTypes(manufacturer)
        assertThat(response.raw().code()).describedAs("%s", response.errorBody()).isEqualTo(200)
        assertThat(response.body().wkda.isEmpty()).isFalse()
    }

    @Test
    fun getBuiltDatesTest() {
        val manufacturer = apiGetManufacturers().body().wkda.keys.first()
        val mainType = apiGetMainTypes(manufacturer).body().wkda.keys.first()
        val response: Response<BuiltDatesModel> = apiGetBuiltDates(manufacturer, mainType)
        assertThat(response.raw().code()).describedAs("%s", response.errorBody()).isEqualTo(200)
        assertThat(response.body().wkda.isEmpty()).isFalse()
    }

    private fun apiGetManufacturers(): Response<ManufacturersModel> {
        val response: Response<ManufacturersModel> = showMyCarApi.getManufacturer(0, 10)
                .blockingSingle()
        return response
    }

    private fun apiGetMainTypes(manufacturer: String): Response<MainTypesModel> {
        val response: Response<MainTypesModel> = showMyCarApi.getMainTypes(manufacturer, 0, 10)
                .blockingSingle()
        return response
    }

    private fun apiGetBuiltDates(manufacturer: String, mainType: String): Response<BuiltDatesModel> {
        val response: Response<BuiltDatesModel> = showMyCarApi.getBuiltDates(manufacturer, mainType, 0, 10)
                .blockingSingle()
        return response
    }
}