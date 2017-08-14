package com.grenzfrequence.showmycar.di.modules

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

}
