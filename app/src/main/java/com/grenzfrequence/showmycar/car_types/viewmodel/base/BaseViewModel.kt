package com.grenzfrequence.showmycar.car_types.viewmodel.base

import android.databinding.BaseObservable
import android.os.Bundle
import android.os.Parcelable
import com.grenzfrequence.showmycar.car_types.ui.base.MvvmView
import com.grenzfrequence.showmycar.car_types.ui.base.MvvmViewModel


/**
 * Created by grenzfrequence on 14.08.17.
 */

abstract class BaseViewModel<VIEW : MvvmView, MODEL: Parcelable> : BaseObservable(), MvvmViewModel<VIEW, MODEL> {

    companion object {
        val TAG_INSTANCE_STATE_BASE_VIEW_MODEL = BaseViewModel::class.java.simpleName
    }

    override var view: VIEW? = null
    override var modelData: MODEL? = null

    override fun attachView(view: MvvmView) {
        this.view = view as VIEW
    }

    override fun detachView() {
        view = null
    }

    override fun saveInstanceState(outState: Bundle) {
        outState.putParcelable(TAG_INSTANCE_STATE_BASE_VIEW_MODEL, modelData)
    }

    override fun restoreInstanceState(savedInstanceState: Bundle) {
        modelData = savedInstanceState.getParcelable(TAG_INSTANCE_STATE_BASE_VIEW_MODEL)
    }

}