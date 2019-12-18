package com.roxkax.mvvmcore.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass


/**
 * [ViewModelProvider.Factory] to generate ViewModel's.
 *
 * @param viewModels A [Map] of [ViewModel] classes and their respective [Provider].
 *
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: Map<KClass<out BaseViewModel>, @JvmSuppressWildcards Provider<BaseViewModel>>) :
    ViewModelProvider.Factory {

    /**
     * Returns the correct [ViewModel] for the requested [KClass]..
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModelClass = viewModels.keys.find { k -> k == modelClass }
        val provider = viewModels[viewModelClass]
        provider?.let {
            return it.get() as T
        }
        throw Exception("Could not provide viewModel for requested class")
    }
}

