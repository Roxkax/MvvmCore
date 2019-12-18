package com.roxkax.mvvmcore.injection

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.roxkax.mvvmcore.viewModels.ViewModelFactory

/**
 * A Dagger [Module] which provides the [ViewModelKey] to be used to construct the [Map] of ViewModel's
 * and the [ViewModelProvider.Factory] to initialize those ViewModel's
 */
@Module
abstract class BaseViewModelModule {

    /**
     * Binds the [ViewModelProvider.Factory] type to the [ViewModelFactory].
     * This ensures that whenever the [ViewModelProvider.Factory] is required for injection,
     * the [ViewModelFactory] will be provided.
     */
    @Binds
    abstract fun providesViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}