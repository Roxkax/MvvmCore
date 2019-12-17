package com.roxkax.mvvmcore

import dagger.android.support.DaggerApplication

/**
 * Base class to use as the application entry point.
 * It extends [DaggerApplication] so whichever extends this class should implement the
 * [DaggerApplication.applicationInjector] method.
 */
abstract class BaseApplication: DaggerApplication()