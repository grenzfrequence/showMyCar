package com.grenzfrequence.showmycar.di.modules

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.grenzfrequence.githubviewerkotlin.di.qualifiers.AppContext
import com.grenzfrequence.githubviewerkotlin.di.scopes.AppScope
import com.grenzfrequence.showmycar.ShowMyCarApp
import dagger.Module
import dagger.Provides

/**
 * Created by grenzfrequence on 10.08.17.
 */

@Module
class AppModule(val application: ShowMyCarApp) {

    @Provides
    @AppScope
    fun provideApp(): Application {
        return application
    }

    @Provides
    @AppScope
    @AppContext
    fun provideAppContext(): Context {
        return application
    }

    @Provides
    @AppScope
    fun provideResources(): Resources {
        return application.getResources()
    }
}