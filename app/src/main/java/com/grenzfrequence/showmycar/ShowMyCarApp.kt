package com.grenzfrequence.showmycar

import android.app.Application
import com.grenzfrequence.githubviewerkotlin.di.components.AppComponent
import com.grenzfrequence.githubviewerkotlin.di.components.DaggerAppComponent
import com.grenzfrequence.githubviewerkotlin.di.modules.NetModule
import com.grenzfrequence.githubviewerkotlin.webservice.WebServiceConfig
import com.grenzfrequence.showmycar.di.modules.AppModule

/**
 * Created by grenzfrequence on 10.08.17.
 */

class ShowMyCarApp : Application() {

    companion object {

        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .netModule(NetModule(WebServiceConfig.BASE_URL, WebServiceConfig.MAX_CACHE_SIZE, WebServiceConfig.LOGGING_LEVEL))
                .build()

    }
}