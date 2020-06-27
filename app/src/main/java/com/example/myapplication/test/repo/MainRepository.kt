package com.example.myapplication.test.repo

import com.example.myapplication.test.model.Incidents
import com.example.myapplication.test.model.Locations

/**
 * Created by Kumuthini.N on 28-06-2020
 */
interface MainRepository {
  fun getLocations(onSuccess: (locations: Locations) -> Unit, onFailure: (t: Throwable) -> Unit)
  fun getIncidents(onSuccess: (incidents: Incidents) -> Unit, onFailure: (t: Throwable) -> Unit)
}
