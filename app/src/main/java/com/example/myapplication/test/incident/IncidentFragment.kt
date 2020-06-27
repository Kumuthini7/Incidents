package com.example.myapplication.test.incident

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.test.App
import com.example.myapplication.test.viewModel.MainViewModel
import com.example.myapplication.test.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_incident_list.view.*
import javax.inject.Inject


/**
 * Created by Kumuthini.N on 28-06-2020
 */

class IncidentFragment : Fragment() {

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel
    private val adapter = IncidentAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.incidents.observe(this, Observer { incident ->
            incident.features?.let {
                adapter.update(this.requireContext(), it)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_incident_list, container, false)

        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = RecyclerView.VERTICAL
        view.rView.layoutManager = mLayoutManager
        view.rView.adapter = adapter

        val dividerItemDecoration =
            androidx.recyclerview.widget.DividerItemDecoration(activity, mLayoutManager.orientation)
        view.rView.addItemDecoration(dividerItemDecoration)
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getIncidents()
    }

    companion object {
        fun newInstance() =
            IncidentFragment().apply {
            }
    }
}
