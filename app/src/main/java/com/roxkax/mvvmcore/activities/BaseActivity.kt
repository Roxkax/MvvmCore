package com.roxkax.mvvmcore.activities

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.roxkax.mvvmcore.application.DaggerApplication
import com.roxkax.mvvmcore.viewModels.BaseViewModel
import javax.inject.Inject

/**
 * An [AppCompatActivity] that abstracts the initialization of an Mvvm Activity
 * @param B Type of the desired binding class.
 * @param V Type of the associated ViewModel
 * @param layoutId The resource ID of the layout to be inflated, bound, and set as the
 *                 Activity's content.
 * @param viewModelVariable The BR id of the variable to be set
 * @param viewModelClass Class of the associated ViewModel
 */
abstract class BaseActivity<B : ViewDataBinding, V : BaseViewModel>(
    private val layoutId: Int,
    private val viewModelVariable: Int,
    private val viewModelClass: Class<V>
) : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: B
    protected lateinit var viewModel: V


    /**
     * Sets the Activity's content view and it's bindings.
     * Sets the associated ViewModel
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectSelf()
        this.viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        this.viewDataBinding.setVariable(viewModelVariable, this.viewModel)
        viewDataBinding.lifecycleOwner = this
    }

    private fun injectSelf() {
        val application: Application = this.application
        if (application is DaggerApplication) {
            application.injectActivity(this)
        } else {
            throw Exception("Application is not a com.roxkax.mvvmcore.applicationDaggerApplication")
        }
    }
}