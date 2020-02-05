package com.roxkax.mvvmcore.application

import android.app.Application
import com.roxkax.mvvmcore.injection.BaseComponent

/**
 * Base class to use as the application entry point.
 */
abstract class BaseApplication<T : BaseComponent> : Application(),
    DaggerApplication {

    abstract val component: T
}