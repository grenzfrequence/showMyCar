package com.grenzfrequence.showmycar.common.recycler_binding_adapter

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup
import com.grenzfrequence.showmycar.car_types.ui.base.MvvmViewModelData
import java.util.*

/**
 * Created by grenzfrequence on 19/03/17.
 */

class RecyclerBindingAdapter<ITEM, VIEWMODELDATA : MvvmViewModelData<ITEM>>(
        @param:LayoutRes private val itemLayoutEven: Int,
        @param:LayoutRes private val itemLayoutOdd: Int,
        @param:LayoutRes private val progressBarLayout: Int,
        private val viewModelId: Int,
        private val createViewModel: () -> VIEWMODELDATA)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private val TYPE_BINDING_VIEW_HOLDER_EVEN = 1
        private val TYPE_BINDING_VIEW_HOLDER_ODD = 2
        private val TYPE_PROGRESS_BAR_VIEW_HOLDER = 3
    }


    private var listItems: List<ITEM> = ArrayList()
    private var showProgressBar = false

    var onItemClickListener: OnItemClickListener<ITEM>? = null

    init {
        setHasStableIds(true)
    }

    fun setListItems(listItems: List<ITEM>) {
        this.listItems = listItems
        showProgressBar = false
        notifyDataSetChanged()
    }

    fun showOnLoadMore(): Int {
        showProgressBar = true
        notifyDataSetChanged()
        return 0
    }

    override fun getItemCount(): Int {
        return if (listItems.isEmpty()) 0 else listItems.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position != 0 && position == itemCount - 1))
            TYPE_PROGRESS_BAR_VIEW_HOLDER
        else if (position % 2 == 0) TYPE_BINDING_VIEW_HOLDER_EVEN
        else TYPE_BINDING_VIEW_HOLDER_ODD
    }

    override fun getItemId(position: Int): Long {
        return if (position != 0 && position == itemCount - 1)
            NO_ID.toLong()
        else
            listItems[position]?.hashCode()?.toLong() ?: NO_ID.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = when (viewType) {
            TYPE_BINDING_VIEW_HOLDER_EVEN -> itemLayoutEven
            TYPE_BINDING_VIEW_HOLDER_ODD -> itemLayoutOdd
            else -> progressBarLayout
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        val viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            TYPE_BINDING_VIEW_HOLDER_EVEN, TYPE_BINDING_VIEW_HOLDER_ODD ->
                viewHolder = BindingViewHolder(view, createViewModel(), viewModelId)
            TYPE_PROGRESS_BAR_VIEW_HOLDER ->
                viewHolder = ProgressBarViewHolder(view)
            else ->
                throw IllegalArgumentException("Invalid ViewType: " + viewType)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BindingViewHolder<*>) {

            val bindingViewHolder = holder as BindingViewHolder<VIEWMODELDATA>
            val modelData = listItems[position]

            bindingViewHolder.itemBinding.root.setOnClickListener {
                view ->
                onItemClickListener?.onItemClicked(modelData, position)
            }

            bindingViewHolder.viewModel.modelData = modelData
            bindingViewHolder.executePendingBindings()

        } else if (holder is ProgressBarViewHolder) {
            val progressBarViewHolder = holder.progressBar
            if (showProgressBar) {
                progressBarViewHolder.isIndeterminate = true
                progressBarViewHolder.visibility = VISIBLE
            } else {
                progressBarViewHolder.visibility = GONE
            }
        }
    }

}
