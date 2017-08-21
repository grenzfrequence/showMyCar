package com.grenzfrequence.showmycar.di.modules

import com.grenzfrequence.showmycar.car_types.data.model.BuiltDatesModel
import com.grenzfrequence.showmycar.car_types.data.model.MainTypesModel
import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
import com.grenzfrequence.showmycar.car_types.data.repos.BuiltDatesRepository
import com.grenzfrequence.showmycar.car_types.data.repos.MainTypesRepository
import com.grenzfrequence.showmycar.car_types.data.repos.ManufacturersRepository
import com.grenzfrequence.showmycar.car_types.viewmodel.CarTypesViewModel
import com.grenzfrequence.showmycar.car_types.viewmodel.SummarizeViewModel
import com.grenzfrequence.showmycar.common.ErrMsg
import com.grenzfrequence.showmycar.di.qualifiers.ErrorMessages
import com.grenzfrequence.showmycar.webservice.ShowMyCarApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by grenzfrequence on 09/03/17.
 */

@Module
class FragmentModule {

    @Provides
    fun provideRepoApi(retrofit: Retrofit): ShowMyCarApi {
        return retrofit.create(ShowMyCarApi::class.java)
    }

    @Provides
    fun provideManufacturersRepository(showMyCarApi: ShowMyCarApi): ManufacturersRepository {
        return ManufacturersRepository(showMyCarApi)
    }

    @Provides
    fun provideMainTypeRepository(showMyCarApi: ShowMyCarApi): MainTypesRepository {
        return MainTypesRepository(showMyCarApi)
    }

    @Provides
    fun provideBuiltDatesRepository(showMyCarApi: ShowMyCarApi): BuiltDatesRepository {
        return BuiltDatesRepository(showMyCarApi)
    }

    @Provides
    fun provideManufacturersViewModel(
            manufacturersRepository: ManufacturersRepository,
            @ErrorMessages errorMessages: Map<Int, ErrMsg>)

            : CarTypesViewModel<ManufacturersModel, ManufacturersRepository> {

        return CarTypesViewModel(manufacturersRepository, errorMessages)
    }

    @Provides
    fun provideMainTypesViewModel(
            mainTypesRepository: MainTypesRepository,
            @ErrorMessages errorMessages: Map<Int, ErrMsg>)

            : CarTypesViewModel<MainTypesModel, MainTypesRepository> {

        return CarTypesViewModel(mainTypesRepository, errorMessages)
    }

    @Provides
    fun provideBuiltDatesViewModel(
            builtDatesRepository: BuiltDatesRepository,
            @ErrorMessages errorMessages: Map<Int, ErrMsg>)

            : CarTypesViewModel<BuiltDatesModel, BuiltDatesRepository> {

        return CarTypesViewModel(builtDatesRepository, errorMessages)
    }

    @Provides
    fun provideSummarizeViewModel(): SummarizeViewModel = SummarizeViewModel()

}
