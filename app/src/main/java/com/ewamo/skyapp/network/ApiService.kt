package com.ewamo.skyapp.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

//TODO, complete
private const val BASE_URL =
    "https://api.nasa.gov/planetary/apod?api_key=wCK0i0fKZI6dYsXaGqN6azUrpcilhSy6H0J9hY8a"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("apod")
    fun getProperties():
            Call<String>
}

object NasaApi {
    val nasaService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}