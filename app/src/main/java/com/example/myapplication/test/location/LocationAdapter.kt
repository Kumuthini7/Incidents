package com.example.myapplication.test.location


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.test.location.LocationFragment.OnListFragmentInteractionListener
import com.example.myapplication.test.model.FeaturesItem
import com.example.myapplication.test.model.Geometry
import kotlinx.android.synthetic.main.fragment_location.view.*


/**
 * Created by Kumuthini.N on 28-06-2020
 */

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    private var context: Context? = null

    private val mOnClickListener: View.OnClickListener

    private var features: List<FeaturesItem?>? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            // val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            val property = v.tag as Geometry
            val intent = Intent(context, LocationMapsActivity::class.java)
            intent.putExtra("Latitude", property.coordinates?.get(0))
            intent.putExtra("Longitude", property.coordinates?.get(1))
            context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_location, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = features?.get(position)
        holder.type.text = item?.properties?.type
        holder.latitude.text = "Latitude: ${item?.geometry?.coordinates?.get(0)?.toString()}"
        holder.longitude.text = "Longitude: ${item?.geometry?.coordinates?.get(1)?.toString()}"

        with(holder.mView) {
            tag = item?.geometry
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = features?.size ?: 0
    fun update(
        context: Context,
        it: List<FeaturesItem?>
    ) {
        this.context = context
        this.features = it
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val type: TextView = mView.type
        val latitude: TextView = mView.latitude
        val longitude: TextView = mView.longitude

        override fun toString(): String {
            return super.toString() + " '" + latitude.text + "'"
        }
    }
}
