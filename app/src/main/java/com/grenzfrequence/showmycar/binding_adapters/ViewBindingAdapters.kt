package com.grenzfrequence.showmycar.binding_adapters

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by grenzfrequence on 12/03/17.
 */

@BindingAdapter("android:visibility")
fun bindingVisibility(view: View, visible: Boolean?) {
    val visibility = if (visible == null || !visible) View.GONE else View.VISIBLE
    view.visibility = visibility
}


