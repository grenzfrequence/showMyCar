package com.grenzfrequence.showmycar

import android.content.Context
import android.content.Intent
import com.grenzfrequence.showmycar.car_types.events.LoadBuiltDatesEvent
import com.grenzfrequence.showmycar.car_types.events.LoadMainTypesEvent
import com.grenzfrequence.showmycar.car_types.events.ShowSummarizeEvent
import com.grenzfrequence.showmycar.car_types.ui.BuiltDatesActivity
import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem
import com.grenzfrequence.showmycar.car_types.ui.MainTypesActivity
import com.grenzfrequence.showmycar.car_types.ui.SummarizeActivity
import org.greenrobot.eventbus.EventBus

/**
 * Created by grenzfrequence on 18.08.17.
 */
object Navigator {

    private val eventBus by lazy { EventBus.getDefault() }

    fun startMainTypesActivity(context: Context, manufacturer: CarTypesItem) {
        eventBus.postSticky(LoadMainTypesEvent(manufacturer))
        context.startActivity(Intent(context, MainTypesActivity::class.java))
    }

    fun startBuiltDatesActivity(context: Context, manufacturer: CarTypesItem, mainType: CarTypesItem) {
        eventBus.postSticky(LoadBuiltDatesEvent(manufacturer, mainType))
        context.startActivity(Intent(context, BuiltDatesActivity::class.java))
    }

    fun startSummarizeActivity(
            context: Context,
            manufacturer: CarTypesItem,
            mainType: CarTypesItem,
            builtDate: CarTypesItem) {
        eventBus.postSticky(ShowSummarizeEvent(manufacturer, mainType, builtDate))
        context.startActivity(Intent(context, SummarizeActivity::class.java))
    }

}
