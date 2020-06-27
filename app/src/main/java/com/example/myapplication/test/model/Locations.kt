package com.example.myapplication.test.model

import com.google.gson.annotations.SerializedName

data class Locations(

	@SerializedName("features")
	val features: List<FeaturesItem?>? = null,

	@SerializedName("type")
	val type: String? = null
)

data class FeaturesItem(

	@SerializedName("geometry")
	val geometry: Geometry? = null,

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("properties")
	val properties: Properties? = null
)

data class Geometry(

	@SerializedName("coordinates")
	val coordinates: List<Double?>? = null,

	@SerializedName("type")
	val type: String? = null
)

data class Properties(

	@SerializedName("occurred_at")
	val occurredAt: Int? = null,

	@SerializedName("id")
	val id: Int? = null,

	@SerializedName("type")
	val type: String? = null
)