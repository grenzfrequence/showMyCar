package com.grenzfrequence.showmycar.car_types.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grenzfrequence.githubviewerkotlin.base.BaseActivity
import com.grenzfrequence.showmycar.di.components.FragmentComponent
import com.grenzfrequence.showmycar.utils.ComponentManager
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


/**
 * Created by grenzfrequence on 14.08.17.
 */

abstract class BaseFragment
<BINDING : ViewDataBinding, VIEWMODEL : MvvmViewModel<out MvvmView, *>>
(val viewModelId: Int) : Fragment(), MvvmView {

    @Inject
    lateinit var viewModel: VIEWMODEL
    lateinit var binding: BINDING

    protected val fragmentComponent: FragmentComponent by lazy {
        ComponentManager.getFragmentComponent((activity as BaseActivity).activityComponent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.attachView(this)
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        viewModel.detachView()
        EventBus.getDefault().unregister(this)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let { viewModel.restoreInstanceState(savedInstanceState) }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.let { viewModel.saveInstanceState(outState) }
    }

    protected fun bindViewModel(
            layoutInflater: LayoutInflater,
            container: ViewGroup?,
            @LayoutRes layoutResId: Int): View {

        binding = DataBindingUtil.inflate(layoutInflater, layoutResId, null, false)
        binding.setVariable(viewModelId, viewModel)

        return binding.getRoot()
    }

}