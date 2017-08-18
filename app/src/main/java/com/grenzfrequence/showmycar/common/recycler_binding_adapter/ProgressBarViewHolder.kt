package com.grenzfrequence.showmycar.common.recycler_binding_adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar

import com.grenzfrequence.showmycar.R

/**
 * Created by grenzfrequence on 26/03/17.
 */

class ProgressBarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val progressBar: ProgressBar

    init {
        this.progressBar = view.findViewById(R.id.recycler_binding_progress_bar) as ProgressBar
    }
}
