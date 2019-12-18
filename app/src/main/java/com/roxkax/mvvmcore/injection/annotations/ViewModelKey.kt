package com.roxkax.mvvmcore.injection.annotations

import androidx.lifecycle.ViewModel
import com.roxkax.mvvmcore.viewModels.BaseViewModel
import com.roxkax.mvvmcore.viewModels.ViewModelFactory
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Annotation used as [MapKey] so that the [ViewModel] classes can be provided into a map to
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