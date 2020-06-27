package com.example.myapplication.test.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.test.model.Incidents
import com.example.myapplication.test.model.Locations
import com.example.myapplication.test.repo.MainRepository


/**
 * Created by Kumuthini.N on 28-06-2020
 */
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val mLocations = MutableLiveData<Locations>()
    private val mIncidents = MutableLiveData<Incidents>()

    val locations: LiveData<Locations>
        get() = mLocations
    val incidents: LiveData<Incidents>
        get() = mIncidents

    fun getLocations() {
        mainRepository.getLocations(
            { locations1 -> mLocations.value = locations1 },
            { t -> Log.e("MainActivity", "onFailure: ", t) }
        )
    }

    fun getIncidents() {
        mainRepository.getIncidents(
            { incidents -> mIncidents.value = incidents },
            { t -> Log.e("MainActivity", "onFailure: ", t) }
        )
    }
}


