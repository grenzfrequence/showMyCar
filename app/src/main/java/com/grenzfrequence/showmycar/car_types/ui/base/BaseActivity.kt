package com.grenzfrequence.githubviewerkotlin.base

import android.support.v7.app.AppCompatActivity
import com.grenzfrequence.showmycar.di.components.ActivityComponent
import com.grenzfrequence.showmycar.utils.ComponentManager


/**
 * Created by grenzfrequence on 09/03/17.
 */

abstract class BaseActivity : AppCompatActivity() {

    val activityComponent: ActivityComponent by lazy {
        ComponentManager.getActivityComponent(this)
    }

}
