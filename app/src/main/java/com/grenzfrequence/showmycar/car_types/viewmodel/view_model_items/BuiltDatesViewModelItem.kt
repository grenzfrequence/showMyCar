package com.grenzfrequence.showmycar.car_types.viewmodel.view_model_items

import com.grenzfrequence.showmycar.car_types.events.ClickedBuiltDatesViewModelItemEvent
import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem
import com.grenzfrequence.showmycar.car_types.viewmodel.base.CarTypesViewModelItem
import org.greenrobot.eventbus.EventBus

/**
 * Created by grenzfrequence on 20.08.17.
 */
class BuiltDatesViewModelItem(
        val _manufacturer: CarTypesItem, val _mainType: CarTypesItem) : CarTypesViewModelItem() {

    override fun onClick() {
        modelData?.let {
            EventBus.getDefault().post(ClickedBuiltDatesViewModelItemEvent(_manufacturer, _mainType, it))
        }
    }
}