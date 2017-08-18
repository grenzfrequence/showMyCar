package com.grenzfrequence.showmycar.car_types.ui.base

import android.databinding.Observable
import android.os.Bundle
import android.os.Parcelable

/**
 * Created by grenzfrequence on 14.08.17.
 */

interface MvvmViewModel<VIEW: MvvmView, MODEL: Parcelable> : Observable, MvvmViewModelData<MODEL> {

    var view: VIEW?

    fun attachView(view: MvvmView)

    fun detachView()

    fun loadData(reset: Boolean)

    fun isLastPage(): Boolean

    fun saveInstanceState(outState: Bundle)

    fun restoreInstanceState(savedInstanceState: Bundle)

}
