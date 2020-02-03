package com.roxkax.mvvmcore.fragments

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.roxkax.mvvmcore.activities.BaseActivity
import com.roxkax.mvvmcore.application.DaggerApplication
import com.roxkax.mvvmcore.viewModels.BaseViewModel
import javax.inject.Inject


/**
 * An [Fragment] that abstracts the initialization of an Mvvm Fragment
 * @param B Type of the desired binding class.
 * @param V Type of the associated [AppCompatActivity]'s ViewModel
 * @param layoutId The resource ID of the layout to be inflated, bound, and set as the
 *                 Fragment's content.
 * @param viewModelVariable The BR id of the variable to be set
 */
abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel>(
    private val layoutId: Int,
    private val viewModelVariable: Int
) : Fragment() {

    protected lateinit var viewDataBinding: B

    private lateinit var viewModel: V


    /**
     * Sets the Fragment's content view and it's bindings.
     * Sets the associated ViewModel
     */
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectSelf()
        this.viewDataBinding = DataBindingUtil.setContentView(this.requireActivity(), layoutId)
        this.viewModel = (this.requireActivity() as BaseActivity<V>).viewModel
        this.viewDataBinding.setVariable(viewModelVariable, this.viewModel)
        viewDataBinding.lifecycleOwner = this
    }

    private fun injectSelf() {
        val application: Application = this.requireActivity().application
        if (application is DaggerApplication) {
            application.injectFragment(this)
        } else {
            throw Exception("Application is not a com.roxkax.mvvmcore.applicationDaggerApplication")
        }
    }
}