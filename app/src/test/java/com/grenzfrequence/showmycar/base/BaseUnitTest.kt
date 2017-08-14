package com.grenzfrequence.showmycar.base

import android.support.v7.app.AppCompatActivity
import com.grenzfrequence.githubviewerkotlin.di.components.AppComponent
import com.grenzfrequence.githubviewerkotlin.utils.UrlReference
import com.grenzfrequence.showmycar.ShowMyCarApp
import com.grenzfrequence.showmycar.di.components.ActivityComponent
import com.grenzfrequence.showmycar.di.components.FragmentComponent
import com.grenzfrequence.showmycar.utils.ComponentManager
import com.grenzfrequence.showmycar.utils.ComponentManager.getActivityComponent
import org.mockito.Mockito
import org.robolectric.RuntimeEnvironment

/**
 * Created by grenzfrequence on 15/03/17.
 */

abstract class BaseUnitTest {

//    @Mock
//    lateinit var mockedActivity: AppCompatActivity

    private val app: ShowMyCarApp
        get() = RuntimeEnvironment.application as ShowMyCarApp

    protected val appComponent: AppComponent
        get() = ShowMyCarApp.appComponent

    protected val activityComponent: ActivityComponent
        get() = getActivityComponent(Mockito.mock(AppCompatActivity::class.java))

    protected val fragmentComponent: FragmentComponent
        get() = ComponentManager.getFragmentComponent(activityComponent)

    protected val baseUrlReference: UrlReference
        get() = appComponent.baseUrlReference()

}
