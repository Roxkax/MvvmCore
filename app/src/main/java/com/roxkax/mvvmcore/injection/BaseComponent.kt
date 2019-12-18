package com.roxkax.mvvmcore.injection

import dagger.Component

/**
 * A base class to be used for the [Component] of the application
 *
 **/
@Component(
    modules = [
        BaseViewModelModule::class
    ]
)
interface BaseComponent {


}