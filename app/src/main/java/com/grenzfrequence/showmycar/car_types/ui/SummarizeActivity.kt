package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.githubviewerkotlin.base.BaseActivity
import com.grenzfrequence.showmycar.R

/**
 * Created by grenzfrequence on 18.08.17.
 */
class SummarizeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summarize)
        configureEventBus = false
    }
}