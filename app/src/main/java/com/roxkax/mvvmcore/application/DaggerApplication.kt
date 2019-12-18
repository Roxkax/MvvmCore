package com.roxkax.mvvmcore.application

import androidx.databinding.ViewDataBinding
import com.roxkax.mvvmcore.activities.BaseActivity
import com.roxkax.mvvmcore.viewModels.BaseViewModel

interface DaggerApplication {

    fun <B : ViewDataBinding, V : BaseViewModel, T : BaseActivity<B, V>> injectActivity(activity: T)
}