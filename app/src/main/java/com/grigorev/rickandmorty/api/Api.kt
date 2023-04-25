package com.grigorev.rickandmorty.api

import com.grigorev.rickandmorty.BuildConfig
import com.grigorev.rickandmorty.dto.CharactersApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("character")
    suspend fun getAll(@Query("page") page: Int): Response<CharactersApiResponse>

    companion object {
        val apiClient: Api = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}