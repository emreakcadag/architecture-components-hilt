package com.emreakcadag.architecturecomponents_hilt.feature.main.ui

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emreakcadag.architecturecomponents_hilt.base.BaseViewModel
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.MainRepository
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.request.MainRequest
import com.emreakcadag.architecturecomponents_hilt.feature.main.data.response.MainResponse
import kotlinx.coroutines.launch

/**
 * Created by Emre Akçadağ on 15.08.2020
 */
class MainViewModel @ViewModelInject constructor(
    private val repository: MainRepository // todo emreakcadag useCase içine taşınacak.
) : BaseViewModel() {

    val mainTextObservable = ObservableField("Emre")

    private val exampleValue = MutableLiveData<String?>()
    val exampleLiveData: LiveData<String?> = exampleValue

    private fun handleData(response: MainResponse?) {
        mainTextObservable.set(response?.explanation)
        exampleValue.postValue("response: $response") // background thread postValue, mainThread .value =

        // response?.dialogBox?.let { dialogBoxHandler.showDialogBox(it) }
    }

    fun getMainData(mainRequest: MainRequest) {
        scope.launch {
            // todo emreakcadag useCase içine taşınacak.
            repository.mainRemoteDataSource.getMainData(mainRequest) {
                handleData(it)
            }
        }
    }
}