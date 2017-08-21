package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.showmycar.car_types.data.model.ManufacturersModel
import com.grenzfrequence.showmycar.car_types.data.repos.ManufacturersRepository
import com.grenzfrequence.showmycar.car_types.events.LoadManufacturersEvent
import com.grenzfrequence.showmycar.car_types.ui.base.CarTypesFragment
import com.grenzfrequence.showmycar.car_types.viewmodel.base.CarTypesViewModelItem
import com.grenzfrequence.showmycar.car_types.viewmodel.view_model_items.ManufacturerViewModelItem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by grenzfrequence on 18.08.17.
 */

class ManufacturersFragment: CarTypesFragment<ManufacturersModel, ManufacturersRepository>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun createViewModelItem(): CarTypesViewModelItem {
        return ManufacturerViewModelItem()
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onLoadManufacturersEvent(event: LoadManufacturersEvent) {
        viewModel.loadData(true)
        EventBus.getDefault().removeStickyEvent(event)
    }

}