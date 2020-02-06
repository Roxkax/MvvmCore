package com.roxkax.mvvmcore.services

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.roxkax.mvvmcore.activities.BaseActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivityTrackerService @Inject constructor(context: Context) :
    Application.ActivityLifecycleCallbacks {

    var currentActivity: BaseActivity? = null

    init {
        (context as Application).registerActivityLifecycleCallbacks(this)
    }

    private fun clearCurrentActivity(activity: Activity?) {
        if (activity == currentActivity)
            currentActivity = null
    }

    private fun setCurrentActivity(activity: Activity?) {
        if (activity is BaseActivity)
            currentActivity = activity
    }

    override fun onActivityStarted(activity: Activity?) {
        setCurrentActivity(activity)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        setCurrentActivity(activity)
    }

    override fun onActivityResumed(activity: Activity?) {
        setCurrentActivity(activity)
    }

    override fun onActivityDestroyed(activity: Activity?) {
        clearCurrentActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        clearCurrentActivity(activity)
    }

    override fun onActivityStopped(activity: Activity?) {
        clearCurrentActivity(activity)
    }

    override fun onActivityPaused(activity: Activity?) {
        clearCurrentActivity(activity)
    }
}