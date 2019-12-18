package com.roxkax.mvvmcore.application

import android.app.Activity

interface DaggerApplication {

    fun inject(activity: Activity)
}