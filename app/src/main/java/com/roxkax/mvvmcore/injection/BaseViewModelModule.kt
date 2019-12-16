package com.roxkax.mvvmcore.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

    @Binds
    internal abstract fun providesViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    annotation class ViewModelKey(val value: KClass<out ViewModel>)

}