package com.arioval.proyecto_api_key

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api-key") key:String,
        @Query("start", encoded = true) start:String,
        @Query("end", encoded = true) end:String
    ):Response<RouteResponse>
}