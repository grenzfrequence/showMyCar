package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grenzfrequence.showmycar.BR
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.events.ShowSummarizeEvent
import com.grenzfrequence.showmycar.car_types.ui.base.BaseFragment
import com.grenzfrequence.showmycar.car_types.viewmodel.SummarizeViewModel
import com.grenzfrequence.showmycar.databinding.FragmentSummarizeBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by grenzfrequence on 21.08.17.
 */

class SummarizeFragment : BaseFragment<FragmentSummarizeBinding, SummarizeViewModel>(BR.summarizeViewModel) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = bindViewModel(inflater!!, container, R.layout.fragment_summarize)
        return view
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    fun onShowSummarizeEvent(event: ShowSummarizeEvent) {
        viewModel.manufacturer.set(event.manufacturer.value)
        viewModel.mainType.set(event.mainType.value)
        viewModel.builtDate.set(event.builtDate.value)
        EventBus.getDefault().removeStickyEvent(event)
    }
}
