package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grenzfrequence.showmycar.BR
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.ui.base.BaseFragment
import com.grenzfrequence.showmycar.car_types.viewmodel.ManufacturersItemViewModel
import com.grenzfrequence.showmycar.car_types.viewmodel.ManufacturersViewModel
import com.grenzfrequence.showmycar.common.recycler_binding_adapter.EndlessRecyclerViewScrollListener
import com.grenzfrequence.showmycar.common.recycler_binding_adapter.RecyclerBindingAdapter
import com.grenzfrequence.showmycar.databinding.FragmentManufacturersBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable


/**
 * Created by grenzfrequence on 14.08.17.
 */
class ManufacturersFragment
    : BaseFragment<FragmentManufacturersBinding, ManufacturersViewModel>(BR.manufacturersViewModel),
        ManufacturersView {

    val recyclerAdapter: RecyclerBindingAdapter<ManufacturerItem, ManufacturersItemViewModel> by lazy {
        RecyclerBindingAdapter(
                R.layout.view_manufacturer_item_even,
                R.layout.view_manufacturer_item_odd,
                R.layout.view_progress_bar,
                BR.manufacturersItemViewModel) {
            ManufacturersItemViewModel()
        }
    }
    val linearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(activity) }
    val recyclerViewScrollListener: EndlessRecyclerViewScrollListener by lazy {
        MyScrollListener(linearLayoutManager)
    }

    var onLoadMoreSubscription: Disposable? = null
    var loadDataSubscription: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = bindViewModel(inflater!!, container, R.layout.fragment_manufacturers)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val rv = binding.rvList
        rv.layoutManager = linearLayoutManager
        rv.adapter = recyclerAdapter
        rv.addOnScrollListener(recyclerViewScrollListener)
        binding.srlRefresh.setOnRefreshListener { viewModel.loadData(true) }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadData(true)
    }

    override fun onStop() {
        super.onStop()
        onLoadMoreSubscription?.dispose()
        loadDataSubscription?.dispose()
    }

    override fun setListItems(listItems: List<ManufacturerItem>, reset: Boolean) {
        recyclerAdapter.setListItems(listItems)
        if (reset) {
            recyclerViewScrollListener.resetState()
        }
    }

    override fun onRefresh(show: Boolean) {
        val srlRefresh = binding.srlRefresh
        srlRefresh.post { srlRefresh.isRefreshing = show }
    }

    inner class MyScrollListener(layoutManager: LinearLayoutManager) :
            EndlessRecyclerViewScrollListener(layoutManager) {

        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            if (!viewModel.isLastPage()) {
                onLoadMoreSubscription = Observable
                        .defer({ Observable.just(recyclerAdapter.showOnLoadMore()) })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
            }
            viewModel.loadData(false)
        }
    }
}

