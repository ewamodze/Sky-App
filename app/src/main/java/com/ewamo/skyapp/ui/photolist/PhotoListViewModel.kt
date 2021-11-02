package com.ewamo.skyapp.ui.photolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ewamo.skyapp.network.NasaApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO, refactor with ViewModel
class PhotoListViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getNasaProperties()
    }

    private fun getNasaProperties() {
        NasaApi.nasaService.getProperties().enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })
    }

}