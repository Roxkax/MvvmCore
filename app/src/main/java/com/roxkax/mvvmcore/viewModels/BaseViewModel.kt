package com.roxkax.mvvmcore.viewModels

import androidx.lifecycle.ViewModel
import com.roxkax.mvvmcore.activities.BaseActivity
import com.roxkax.mvvmcore.services.ActivityTrackerService
import javax.inject.Inject

/**
 * A [ViewModel] with some common logic used in Mvvm applications
 */
abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var activityTrackerService: ActivityTrackerService


    val activity: BaseActivity?
        get() {
            return activityTrackerService.currentActivity
        }
}