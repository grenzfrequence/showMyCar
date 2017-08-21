package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.showmycar.car_types.data.model.BuiltDatesModel
import com.grenzfrequence.showmycar.car_types.data.repos.BuiltDatesRepository
import com.grenzfrequence.showmycar.car_types.events.LoadBuiltDatesEvent
import com.grenzfrequence.showmycar.car_types.ui.base.CarTypesFragment
import com.grenzfrequence.showmycar.car_types.viewmodel.base.CarTypesViewModelItem
import com.grenzfrequence.showmycar.car_types.viewmodel.view_model_items.BuiltDatesViewModelItem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by grenzfrequence on 14.08.17.
 */

class BuiltDatesFragment: CarTypesFragment<BuiltDatesModel, BuiltDatesRepository>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun createViewModelItem(): CarTypesViewModelItem {
        val repo = viewModel.repository
        return BuiltDatesViewModelItem(repo.manufacturer, repo.mainType)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onLoadBuiltDates(event: LoadBuiltDatesEvent) {
        val repo : BuiltDatesRepository = viewModel.repository
        repo.manufacturer = event.manufacturer
        repo.mainType = event.mainType
        viewModel.loadData(true)
        EventBus.getDefault().removeStickyEvent(this)
    }

}