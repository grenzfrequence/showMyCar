package com.grenzfrequence.showmycar.car_types.ui

import android.os.Bundle
import com.grenzfrequence.githubviewerkotlin.base.BaseActivity
import com.grenzfrequence.showmycar.R

/**
 * Created by grenzfrequence on 14.08.17.
 */

class ManufacturersActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_manufacturers)
    }
}