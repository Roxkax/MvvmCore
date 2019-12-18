package com.roxkax.mvvmcore.injection

import androidx.lifecycle.ViewModelProvider
import com.roxkax.mvvmcore.viewModels.BaseViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import com.roxkax.mvvmcore.viewModels.ViewModelFactory
import kotlin.reflect.KClass

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

    /**
     * Annotation used as [MapKey] so that the [BaseViewModel] classes can be provided into a map to
     * be used inside the [ViewModelFactory]
     */
    @Target(
        AnnotationTarget.FUNCTION,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER
    )
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out BaseViewModel>)

}