package com.roxkax.mvvmcore.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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
    private val viewModelVariable: Int,
    private val viewModelClass: Class<V>,
    val selfContainedViewModel: Boolean = true
) : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewDataBinding: B

    protected lateinit var viewModel: V

    /**
     * Sets the associated ViewModel
     * Call the injection method
     */
    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectSelf()
        if (selfContainedViewModel) {
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        } else {
            viewModel = ViewModelProviders.of(this.requireActivity(), viewModelFactory)
                .get((viewModelClass))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        this.viewDataBinding.setVariable(viewModelVariable, this.viewModel)
        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
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