package com.grenzfrequence.showmycar.common.recycler_binding_adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.view.View
import com.grenzfrequence.showmycar.car_types.ui.base.MvvmViewModelData

/**
 * Created by grenzfrequence on 06/03/17.
 */

class BindingViewHolder<VIEWMODELDATA : MvvmViewModelData<*>>(
        itemView: View, viewModel: VIEWMODELDATA, @IdRes viewModelId: Int) : RecyclerView.ViewHolder(itemView) {

    lateinit var itemBinding: ViewDataBinding
        private set

    var viewModel: VIEWMODELDATA
        private set

    init {
        this.viewModel = viewModel
        bindViewModel(viewModelId)
    }

    protected fun bindViewModel(@IdRes viewModelId: Int) {
        itemBinding = DataBindingUtil.bind(itemView)
        itemBinding.setVariable(viewModelId, viewModel)
    }

    fun executePendingBindings() {
        itemBinding.executePendingBindings()
    }

}
