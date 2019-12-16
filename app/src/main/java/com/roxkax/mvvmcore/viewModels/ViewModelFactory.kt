package com.roxkax.mvvmcore.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


/**
 * [ViewModelProvider.Factory] to generate ViewModel's.
 *
 * @param viewModels A [Map] of ViewModel classes and their respective [Provider]. The ViewModel class must
 * extend [BaseViewModel].
 *
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }
}

