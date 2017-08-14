package com.grenzfrequence.showmycar.utils

import android.support.v7.app.AppCompatActivity

import com.grenzfrequence.showmycar.ShowMyCarApp
import com.grenzfrequence.showmycar.di.components.ActivityComponent
import com.grenzfrequence.showmycar.di.components.DaggerActivityComponent
import com.grenzfrequence.showmycar.di.components.DaggerFragmentComponent
import com.grenzfrequence.showmycar.di.components.FragmentComponent
import com.grenzfrequence.showmycar.di.modules.ActivityModule
import com.grenzfrequence.showmycar.di.modules.FragmentModule

/**
 * Created by grenzfrequence on 15/03/17.
 */

object ComponentManager {

    fun getActivityComponent(activity: AppCompatActivity): ActivityComponent {
        return DaggerActivityComponent
                .builder()
                .appComponent(ShowMyCarApp.appComponent)
                .activityModule(ActivityModule(activity))
                .build()
    }

    fun getFragmentComponent(activityComponent: ActivityComponent): FragmentComponent {
        return DaggerFragmentComponent
                .builder()
                .activityComponent(activityComponent)
                .fragmentModule(FragmentModule())
                .build()
    }

}
