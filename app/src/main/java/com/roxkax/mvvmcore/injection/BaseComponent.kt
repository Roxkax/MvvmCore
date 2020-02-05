package com.roxkax.mvvmcore.injection

import com.roxkax.mvvmcore.services.ActivityTrackerService
import dagger.Component

/**
 * A base class to be used for the [Component] of the application
 *
 **/
interface BaseComponent {

    fun activityTrackerService(): ActivityTrackerService
}