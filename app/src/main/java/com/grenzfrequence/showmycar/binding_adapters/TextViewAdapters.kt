package com.grenzfrequence.showmycar.binding_adapters

import android.databinding.BindingAdapter
import android.widget.TextView

/**
 * Created by grenzfrequence on 12/03/17.
 */

@BindingAdapter("android:text")
fun setTextResource(textView: TextView, stringResId: Int) {
    val res = textView.resources
    textView.text = res.getString(stringResId)
}
