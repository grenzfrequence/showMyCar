package com.grenzfrequence.showmycar.di.components


import com.grenzfrequence.githubviewerkotlin.di.scopes.FragmentScope
import com.grenzfrequence.showmycar.webservice.ShowMyCarApi
import com.grenzfrequence.showmycar.di.modules.FragmentModule
import com.squareup.moshi.Moshi

import dagger.Component
import retrofit2.Retrofit

/**
 * Created by grenzfrequence on 08/03/17.
 */

@Component(modules = arrayOf(FragmentModule::class), dependencies = arrayOf(ActivityComponent::class))
@FragmentScope
interface FragmentComponent {

    fun moshi(): Moshi

    fun retrofit(): Retrofit

    fun showMyCarApi(): ShowMyCarApi

}
