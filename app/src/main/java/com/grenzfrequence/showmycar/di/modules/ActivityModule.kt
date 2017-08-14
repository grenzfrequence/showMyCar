package com.grenzfrequence.showmycar.di.modules

import android.content.Context
import android.support.v7.app.AppCompatActivity

import com.grenzfrequence.githubviewerkotlin.di.qualifiers.ActivityContext
import com.grenzfrequence.githubviewerkotlin.di.scopes.ActivityScope

import dagger.Module
import dagger.Provides

/**
 * Created by grenzfrequence on 09/03/17.
 */

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    @ActivityContext
    internal fun provideActivityContext(): Context {
        return activity
    }

}
