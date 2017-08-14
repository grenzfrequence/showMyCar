package com.grenzfrequence.githubviewerkotlin.di.components

import android.app.Application
import android.content.res.Resources
import com.grenzfrequence.githubviewerkotlin.di.modules.NetModule
import com.grenzfrequence.githubviewerkotlin.di.scopes.AppScope
import com.grenzfrequence.githubviewerkotlin.utils.UrlReference
import com.grenzfrequence.showmycar.di.modules.AppModule
import com.squareup.moshi.Moshi
import dagger.Component
import retrofit2.Retrofit


/**
 * Created by grenzfrequence on 25/01/17.
 */


//@Component(modules = arrayOf(AppModule::class, NetModule::class))

@Component(modules = arrayOf(AppModule::class, NetModule::class))
@AppScope
interface AppComponent {

    fun application(): Application

    fun resources(): Resources

    fun retrofit(): Retrofit

    fun moshi(): Moshi

    fun baseUrlReference(): UrlReference

}
