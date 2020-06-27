package com.example.myapplication.test

import android.app.Application
import com.example.myapplication.test.dagger.AppComponent
import com.example.myapplication.test.dagger.DaggerAppComponent

/**
 * Created by Kumuthini.N on 28-06-2020
 */
class App : Application() {
  companion object {
    lateinit var component: AppComponent
  }

  override fun onCreate() {
    super.onCreate()

    component = DaggerAppComponent.builder().build()
  }

}
