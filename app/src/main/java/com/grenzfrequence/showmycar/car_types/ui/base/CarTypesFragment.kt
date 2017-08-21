package com.grenzfrequence.showmycar.car_types.ui.base

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grenzfrequence.showmycar.BR
import com.grenzfrequence.showmycar.R
import com.grenzfrequence.showmycar.car_types.data.model.base.CarTypesModel
import com.grenzfrequence.showmycar.car_types.data.repos.Repository
import com.grenzfrequence.showmycar.car_types.ui.CarTypesItem
import com.grenzfrequence.showmycar.car_types.viewmodel.CarTypesViewModel
import com.grenzfrequence.showmycar.car_types.viewmodel.base.CarTypesViewModelItem
import com.grenzfrequence.showmycar.common.recycler_binding_adapter.EndlessRecyclerViewScrollListener
import com.grenzfrequence.showmycar.common.recycler_binding_adapter.RecyclerBindingAdapter
import com.grenzfrequence.showmycar.databinding.FragmentCarTypesBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

/**
 * Created by grenzfrequence on 18.08.17.
 */
abstract class CarTypesFragment<MODEL : CarTypesModel, REPO : Repository<MODEL>> :
        BaseFragment<FragmentCarTypesBinding, CarTypesViewModel<MODEL, REPO>>(BR.carTypesViewModel), CarTypesView {

    private val recyclerAdapter: RecyclerBindingAdapter<CarTypesItem, CarTypesViewModelItem> by lazy {
        RecyclerBindingAdapter(
                R.layout.view_car_types_item_even,
                R.layout.view_car_types_item_odd,
                R.layout.view_progress_bar,
                BR.carTypesItemViewModel) {
            createViewModelItem()
        }
    }
    private val linearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(activity) }
    private val recyclerViewScrollListener: EndlessRecyclerViewScrollListener by lazy {
        MyScrollListener(linearLayoutManager)
    }

    private var onLoadMoreSubscription: Disposable? = null
    private var loadDataSubscription: Disposable? = null

    abstract fun createViewModelItem(): CarTypesViewModelItem

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = bindViewModel(inflater!!, container, R.layout.fragment_car_types)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        val rv = binding.rvList
        rv.layoutManager = linearLayoutManager
        rv.adapter = recyclerAdapter
        rv.addOnScrollListener(recyclerViewScrollListener)
        binding.srlRefresh.setOnRefreshListener { viewModel.loadData(true) }
    }

    override fun onStop() {
        super.onStop()
        onLoadMoreSubscription?.dispose()
        loadDataSubscription?.dispose()
    }

    override fun setListItems(listItems: List<CarTypesItem>, reset: Boolean) {
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