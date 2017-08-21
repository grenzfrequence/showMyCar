package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.githubviewerkotlin.base.BaseActivity
import com.grenzfrequence.showmycar.Navigator
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.events.ClickedMainTypeViewModelItemEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by grenzfrequence on 14.08.17.
 */

class MainTypesActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_types)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onClickedMainTypeViewModelItemEvent(event: ClickedMainTypeViewModelItemEvent) {
        Navigator.startBuiltDatesActivity(this, event.manufacturer, event.mainType)
    }
}