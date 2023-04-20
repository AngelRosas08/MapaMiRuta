package com.arioval.proyecto_api_key

import com.google.android.gms.common.Feature
import com.google.gson.annotations.SerializedName

data class RouteResponse(@SerializedName("features") val features:List<Feature>)
data class Feature(@SerializedName("geometry")val geometry:Geometry)
data class Geometry(@SerializedName("coordinates")val coordinates:List<List<Double>>)