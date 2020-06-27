package com.example.myapplication.test.tab

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.test.incident.IncidentDetailActivity
import com.example.myapplication.test.incident.IncidentFragment
import com.example.myapplication.test.location.LocationFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_incident_detail.*

class IncidentPagerActivity : AppCompatActivity(),
    LocationFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction() {
        val intent = Intent(this, IncidentDetailActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident_detail)
    }

    override fun onStart() {
        super.onStart()
        initUI()
    }

    private fun initUI() {
        supportActionBar?.hide()
        val incidentFragment = IncidentFragment.newInstance()
        val locationFragment = LocationFragment.newInstance()

        val fragments = ArrayList<androidx.fragment.app.Fragment>()
        val title = ArrayList<String>()

        fragments.add(incidentFragment)
        title.add("Incidents")

        fragments.add(locationFragment)
        title.add("Locations")

        viewPager.adapter = IncidentPagerAdapter(
            supportFragmentManager,
            fragments,
            title
        )

        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }
}
