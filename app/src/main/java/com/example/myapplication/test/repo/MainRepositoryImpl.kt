package com.example.myapplication.test.repo

import com.example.myapplication.test.api.Api
import com.example.myapplication.test.model.Incidents
import com.example.myapplication.test.model.Locations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Kumuthini.N on 28-06-2020
 */
class MainRepositoryImpl(private val api: Api) :
    MainRepository {
    override fun getIncidents(
        onSuccess: (incidents: Incidents) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        api.getIncidents(15).enqueue(object : Callback<Incidents> {
            override fun onResponse(call: Call<Incidents>, response: Response<Incidents>) {
                response.body()?.let { incidents ->
                    onSuccess.invoke(incidents)
                }
            }

            override fun onFailure(call: Call<Incidents>, t: Throwable) {
                onFailure.invoke(t)
            }
        })
    }

    override fun getLocations(
        onSuccess: (locations: Locations) -> Unit,
        onFailure: (t: Throwable) -> Unit
    ) {
        api.getLocations(15).enqueue(object : Callback<Locations> {
            override fun onResponse(call: Call<Locations>, response: Response<Locations>) {
                response.body()?.let { incidents ->
                    onSuccess.invoke(incidents)
                }
            }

            override fun onFailure(call: Call<Locations>, t: Throwable) {
                onFailure.invoke(t)
            }
        })
    }
}
