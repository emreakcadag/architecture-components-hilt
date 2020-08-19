package com.emreakcadag.architecturecomponents_hilt.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.emreakcadag.architecturecomponents_hilt.base.navigator.Navigator
import javax.inject.Inject

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val layoutResId: Int) : AppCompatActivity() {

    protected lateinit var viewModel: VM
        private set

    protected lateinit var binding: DB
        private set

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = provideViewModel().value

        binding = DataBindingUtil.setContentView(this, layoutResId)
        bindViewModel(binding)
    }

    abstract fun provideViewModel(): Lazy<VM>

    abstract fun bindViewModel(binding: DB)
}