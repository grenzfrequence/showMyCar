package com.grenzfrequence.showmycar.di.components

import com.grenzfrequence.githubviewerkotlin.di.components.AppComponent
import com.grenzfrequence.githubviewerkotlin.di.scopes.ActivityScope
import com.grenzfrequence.showmycar.di.modules.ActivityModule
import com.squareup.moshi.Moshi

import dagger.Component
import retrofit2.Retrofit

/**
 * Created by grenzfrequence on 09/03/17.
 */

@Component(modules = arrayOf(ActivityModule::class), dependencies = arrayOf(AppComponent::class))
@ActivityScope
interface ActivityComponent {


    fun moshi(): Moshi
    fun retrofit(): Retrofit

}
