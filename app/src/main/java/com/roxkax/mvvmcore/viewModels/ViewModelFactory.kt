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
@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: Map<Class<out BaseViewModel>, @JvmSuppressWildcards Provider<BaseViewModel>>) :
    ViewModelProvider.Factory {

    /**
     * Returns the correct [ViewModel] for the requested [Class]..
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        viewModels.keys.firstOrNull { k -> k == modelClass }?.let {
            val provider = viewModels[it]
            try {
                @Suppress("UNCHECKED_CAST")
                return (provider?.get() as? T)
                    ?: throw Exception("Could not provide viewModel for requested class")
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }?: throw Exception("Could not provide viewModel for requested class")
    }
}

