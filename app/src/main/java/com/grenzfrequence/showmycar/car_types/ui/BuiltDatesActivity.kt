package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.githubviewerkotlin.base.BaseActivity
import com.grenzfrequence.showmycar.Navigator
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.events.ClickedBuiltDatesViewModelItemEvent
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by grenzfrequence on 14.08.17.
 */

class BuiltDatesActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_built_dates)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onClickedMainTypeViewModelItemEvent(event: ClickedBuiltDatesViewModelItemEvent) {
        Navigator.startSummarizeActivity(this, event.manufacturer, event.mainType, event.builtDate)
    }
}