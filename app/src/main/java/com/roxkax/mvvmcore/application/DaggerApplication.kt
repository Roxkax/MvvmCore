package com.roxkax.mvvmcore.application

import androidx.databinding.ViewDataBinding
import com.roxkax.mvvmcore.activities.BaseActivity
import com.roxkax.mvvmcore.fragments.BaseFragment
import com.roxkax.mvvmcore.viewModels.BaseViewModel

interface DaggerApplication {

    fun <V : BaseViewModel, T : BaseActivity<V>> injectActivity(activity: T)

    fun <B : ViewDataBinding, V : BaseViewModel, T : BaseFragment<B, V>> injectFragment(fragment: T)
}