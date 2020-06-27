package com.example.myapplication.test.model

import com.google.gson.annotations.SerializedName

data class Incidents(

	@field:SerializedName("features")
	val features: List<IncidentFeaturesItem?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class IncidentFeaturesItem(

	@field:SerializedName("geometry")
	val geometry: IncidentGeometry? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("properties")
	val properties: IncidentProperties? = null
)

data class IncidentGeometry(

	@field:SerializedName("coordinates")
	val coordinates: List<Double?>? = null,

	@field:SerializedName("type")
	val type: String? = null
)

data class IncidentProperties(

	@field:SerializedName("occurred_at")
	val occurredAt: String? = null,

	@field:SerializedName("marker-color")
	val markerColor: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("marker-size")
	val markerSize: String? = null
)