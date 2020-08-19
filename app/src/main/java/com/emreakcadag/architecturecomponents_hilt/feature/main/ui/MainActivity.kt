package com.emreakcadag.architecturecomponents_hilt.feature.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.emreakcadag.architecturecomponents_hilt.R
import com.emreakcadag.architecturecomponents_hilt.base.BaseActivity
import com.emreakcadag.architecturecomponents_hilt.databinding.ActivityMainBinding
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.request.MainRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(R.layout.activity_main) {

    companion object {
        fun newIntent(context: Context) = Intent(context, this::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            btnSendRequest.setOnClickListener {
                viewModel?.getMainData(MainRequest(swDialogBox.isChecked))
            }
        }

        viewModel.exampleLiveData.observe(this, Observer {
            // Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun provideViewModel() = viewModels<MainViewModel>()

    override fun bindViewModel(binding: ActivityMainBinding) {
        binding.viewModel = viewModel
    }
}