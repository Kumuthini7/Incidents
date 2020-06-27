package com.example.myapplication.test.api

import com.example.myapplication.test.model.Incidents
import com.example.myapplication.test.model.Locations
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Kumuthini.N on 28-06-2020
 */
interface Api {

  @GET("api/v2/locations")
  fun getLocations(@Query("limit") limit: Int): Call<Locations>

  @GET("api/v2/locations/markers")
  fun getIncidents( @Query("limit") limit: Int): Call<Incidents>

}
