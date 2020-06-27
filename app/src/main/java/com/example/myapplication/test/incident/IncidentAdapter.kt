package com.example.myapplication.test.incident

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.test.model.IncidentFeaturesItem
import com.example.myapplication.test.model.IncidentProperties
import kotlinx.android.synthetic.main.fragment_incident.view.*


/**
 * Created by Kumuthini.N on 28-06-2020
 */

class IncidentAdapter : RecyclerView.Adapter<IncidentAdapter.ViewHolder>() {

    private var context: Context? = null
    private val mOnClickListener: View.OnClickListener
    private var features: List<IncidentFeaturesItem?>? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            val property = v.tag as IncidentProperties
            val intent = Intent(context, IncidentDetailActivity::class.java)
            intent.putExtra("Description", property.description as String)
            intent.putExtra("Title", property.title as String)
            context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_incident, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = features?.get(position)
        holder.type.text = item?.properties?.title
        // holder.mView.tag = item?.properties?.description

        with(holder.mView) {
            tag = item?.properties
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = features?.size ?: 0

    fun update(
        context: Context,
        it1: List<IncidentFeaturesItem?>
    ) {
        this.context = context
        this.features = it1
        notifyDataSetChanged()

    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val type: TextView = mView.type

        override fun toString(): String {
            return super.toString() + " '" + type.text + "'"
        }
    }
}
