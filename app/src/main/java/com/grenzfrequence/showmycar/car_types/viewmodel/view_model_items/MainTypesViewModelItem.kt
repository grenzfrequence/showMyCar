package com.grenzfrequence.showmycar.car_types.viewmodel.view_model_items

import com.grenzfrequence.showmycar.car_types.events.ClickedMainTypeViewModelItemEvent
import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem
import com.grenzfrequence.showmycar.car_types.viewmodel.base.CarTypesViewModelItem
import org.greenrobot.eventbus.EventBus

/**
 * Created by grenzfrequence on 19.08.17.
 */

class MainTypesViewModelItem(val _manufacturer: CarTypesItem) : CarTypesViewModelItem() {

    override fun onClick() {
        modelData?.let {
            EventBus.getDefault().post(ClickedMainTypeViewModelItemEvent(_manufacturer, it))
        }
    }
}