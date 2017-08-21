package com.grenzfrequence.githubviewerkotlin.base

import android.support.v7.app.AppCompatActivity
import com.grenzfrequence.showmycar.di.components.ActivityComponent
import com.grenzfrequence.showmycar.utils.ComponentManager
import org.greenrobot.eventbus.EventBus


/**
 * Created by grenzfrequence on 09/03/17.
 */

abstract class BaseActivity : AppCompatActivity() {

    var configureEventBus = true

    val activityComponent: ActivityComponent by lazy {
        ComponentManager.getActivityComponent(this)
    }

    override fun onStart() {
        super.onStart()
        if (configureEventBus) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (configureEventBus) {
            EventBus.getDefault().unregister(this)
        }
    }
}
