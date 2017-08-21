package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.showmycar.car_types.data.model.MainTypesModel
import com.grenzfrequence.showmycar.car_types.data.repos.MainTypesRepository
import com.grenzfrequence.showmycar.car_types.events.LoadMainTypesEvent
import com.grenzfrequence.showmycar.car_types.ui.base.CarTypesFragment
import com.grenzfrequence.showmycar.car_types.viewmodel.base.CarTypesViewModelItem
import com.grenzfrequence.showmycar.car_types.viewmodel.view_model_items.MainTypesViewModelItem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by grenzfrequence on 14.08.17.
 */

class MainTypesFragment: CarTypesFragment<MainTypesModel, MainTypesRepository>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onLoadMainTypes(event: LoadMainTypesEvent) {
        val repo : MainTypesRepository = viewModel.repository
        repo.manufacturer = event.manufacturer
        viewModel.loadData(true)
        EventBus.getDefault().removeStickyEvent(this)
    }

    override fun createViewModelItem(): CarTypesViewModelItem {
        return MainTypesViewModelItem(viewModel.repository.manufacturer)
    }
}