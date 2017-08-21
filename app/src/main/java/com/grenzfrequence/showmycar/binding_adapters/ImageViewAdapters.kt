package com.grenzfrequence.showmycar.binding_adapters

import android.databinding.BindingAdapter
import android.widget.ImageView

/**
 * Created by grenzfrequence on 12/03/17.
 */

@BindingAdapter("src")
fun setImageResource(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}

