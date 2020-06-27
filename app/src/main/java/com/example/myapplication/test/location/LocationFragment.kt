package com.example.myapplication.test.location

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
import kotlinx.android.synthetic.main.fragment_location_list.view.*
import javax.inject.Inject


/**
 * Created by Kumuthini.N on 28-06-2020
 */
/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [LocationFragment.OnListFragmentInteractionListener] interface.
 */
class LocationFragment : Fragment() {

    @Inject
    lateinit var factory: MainViewModelFactory

    private lateinit var viewModel: MainViewModel

    val adapter = LocationAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.locations.observe(this, Observer { incident ->
            incident.features?.let {
                adapter.update(this.requireContext(), it)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_location_list, container, false)

        // Set the adapter
        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = RecyclerView.VERTICAL
        view.list.layoutManager = mLayoutManager
        view.list.adapter = adapter

        val dividerItemDecoration =
            androidx.recyclerview.widget.DividerItemDecoration(activity, mLayoutManager.orientation)
        view.list.addItemDecoration(dividerItemDecoration)
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLocations()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction()
    }

    companion object {
        fun newInstance() =
            LocationFragment().apply {
            }
    }
}
