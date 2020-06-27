package com.example.myapplication.test.dagger

import com.example.myapplication.test.incident.IncidentFragment
import com.example.myapplication.test.location.LocationFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Kumuthini.N on 28-06-2020
 */
@Singleton
@Component(
  modules = [
    AppModule::class,
    ViewModelModule::class,
    RepositoryModule::class
  ]
)
interface AppComponent {
  fun inject(incidentFragment: IncidentFragment)
  fun inject(locationFragment: LocationFragment)
}

