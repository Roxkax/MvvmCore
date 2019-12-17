package com.roxkax.mvvmcore.injection

import com.roxkax.mvvmcore.BaseApplication
import dagger.Component
import dagger.android.AndroidInjector

/**
 * A base class to be used for the [Component] of the application
 *
 * It ensures that the component implements the [AndroidInjector]
 * for the a class that extends the [BaseApplication].
 *
 * This base component also already have some modules with essencial providers.
 **/
@Component(
    modules = [
        BaseViewModelModule::class
    ]
)
interface BaseComponent<T : BaseApplication> : AndroidInjector<T>