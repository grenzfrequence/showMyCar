package com.grenzfrequence.showmycar.car_types.viewmodel

import android.databinding.ObservableField
import android.os.Bundle
import com.grenzfrequence.showmycar.car_types.data.model.SummarizeModel
import com.grenzfrequence.showmycar.car_types.ui.base.MvvmView
import com.grenzfrequence.showmycar.car_types.viewmodel.base.BaseViewModel

/**
 * Created by grenzfrequence on 21.08.17.
 */

class SummarizeViewModel : BaseViewModel<MvvmView, SummarizeModel>() {

    companion object {
        val TAG_SAVE_INSTANCE: String = SummarizeViewModel::class.java.simpleName
    }

    val manufacturer: ObservableField<String> = ObservableField("")
    val mainType: ObservableField<String> = ObservableField("")
    val builtDate: ObservableField<String> = ObservableField("")

    override fun saveInstanceState(outState: Bundle) {
        super.saveInstanceState(outState)
        outState.putString(TAG_SAVE_INSTANCE, manufacturer.get())
        outState.putString(TAG_SAVE_INSTANCE+1, mainType.get())
        outState.putString(TAG_SAVE_INSTANCE+2, builtDate.get())
    }

    override fun restoreInstanceState(savedInstanceState: Bundle) {
        super.restoreInstanceState(savedInstanceState)
        manufacturer.set(savedInstanceState.getString(TAG_SAVE_INSTANCE))
        mainType.set(savedInstanceState.getString(TAG_SAVE_INSTANCE+1))
        builtDate.set(savedInstanceState.getString(TAG_SAVE_INSTANCE+2))
    }

    override var modelData: SummarizeModel? = null
    override fun loadData(reset: Boolean) {}
    override fun isLastPage(): Boolean = false
}