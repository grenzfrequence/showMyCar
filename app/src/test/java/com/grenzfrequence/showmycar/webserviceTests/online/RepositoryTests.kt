package com.grenzfrequence.showmycar.webserviceTests.online

import com.grenzfrequence.showmycar.BuildConfig
import com.grenzfrequence.showmycar.base.BaseUnitTest
import com.grenzfrequence.showmycar.car_types.data.model.BuiltDatesModel
import com.grenzfrequence.showmycar.car_types.data.model.MainTypesModel
import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
import com.grenzfrequence.showmycar.car_types.data.repos.BuiltDatesRepository
import com.grenzfrequence.showmycar.car_types.data.repos.MainTypesRepository
import com.grenzfrequence.showmycar.car_types.data.repos.ManufacturersRepository
import com.grenzfrequence.showmycar.webservice.ShowMyCarApi
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import retrofit2.Response

/**
 * Created by grenzfrequence on 17.08.17.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(23))
class RepositoryTests : BaseUnitTest() {

    val showMyCarApi: ShowMyCarApi by lazy { fragmentComponent.showMyCarApi() }

    @Test
    fun manufacturerRepositoryTest() {
        val manufacturerRepo = ManufacturersRepository(showMyCarApi)
        val response: Response<ManufacturersModel> = manufacturerRepo
                .firstPage()
                .blockingSingle()
        assertRepo(response)
    }

    @Test
    fun MainTypesRepositoryTest() {
        val manufacturer = ManufacturersRepository(showMyCarApi)
                .firstPage()
                .blockingSingle()
                .body()
                .wkda.entries.first()

        val mainTypesRepo = MainTypesRepository(showMyCarApi)
        mainTypesRepo.manufacturer = manufacturer
        val response: Response<MainTypesModel> = mainTypesRepo
                .firstPage()
                .blockingSingle()
        assertRepo(response)
    }

    @Test
    fun BuiltDatesRepositoryTest() {
        val manufacturer = ManufacturersRepository(showMyCarApi)
                .firstPage()
                .blockingSingle()
                .body()
                .wkda.entries.first()

        val mainTypesRepository = MainTypesRepository(showMyCarApi)
        mainTypesRepository.manufacturer = manufacturer
        val mainType = mainTypesRepository
                .firstPage()
                .blockingSingle()
                .body()
                .wkda.entries.first()

        val builtDatesRepo = BuiltDatesRepository(showMyCarApi)
        builtDatesRepo.manufacturer = manufacturer
        builtDatesRepo.mainType = mainType
        val response: Response<BuiltDatesModel> = builtDatesRepo
                .firstPage()
                .blockingSingle()
        assertRepo(response)
    }

    fun <MODEL> assertRepo(response: Response<MODEL>) {
        assertThat(response).isNotNull()
        assertThat(response.errorBody()).isNull()
        assertThat(response.body()).isNotNull()
    }

}