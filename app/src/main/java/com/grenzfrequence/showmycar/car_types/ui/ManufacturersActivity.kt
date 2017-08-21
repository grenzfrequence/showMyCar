package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.githubviewerkotlin.base.BaseActivity
import com.grenzfrequence.showmycar.Navigator
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.events.ClickedManufacturerViewModelItemEvent
import com.grenzfrequence.showmycar.car_types.events.LoadManufacturersEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by grenzfrequence on 14.08.17.
 */

class ManufacturersActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manufacturers)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().postSticky(LoadManufacturersEvent())
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onClickedManufacturerViewModelItemEvent(event: ClickedManufacturerViewModelItemEvent) {
        Navigator.startMainTypesActivity(this, event.manufacturer)
    }
}