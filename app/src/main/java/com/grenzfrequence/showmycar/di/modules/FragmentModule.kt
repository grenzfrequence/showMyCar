package com.grenzfrequence.showmycar.di.modules

import com.grenzfrequence.showmycar.car_types.data.Repos.ManufacturersRepository
import com.grenzfrequence.showmycar.car_types.viewmodel.ManufacturersViewModel
import com.grenzfrequence.showmycar.common.ErrMessages
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
    fun provideManufacturersViewModel(
            repository: ManufacturersRepository,
            @ErrorMessages errorMessages: ErrMessages): ManufacturersViewModel {
        return ManufacturersViewModel(repository, errorMessages)
    }

}
