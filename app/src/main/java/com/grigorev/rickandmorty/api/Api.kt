package com.grigorev.rickandmorty.api

import com.grigorev.rickandmorty.BuildConfig
import com.grigorev.rickandmorty.dto.Character
import com.grigorev.rickandmorty.dto.CharactersApiResponse
import com.grigorev.rickandmorty.dto.Episode
import com.grigorev.rickandmorty.dto.EpisodesApiResponse
import com.grigorev.rickandmorty.dto.Location
import com.grigorev.rickandmorty.dto.LocationsApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharactersApiResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") page: Int): Response<Character>

    @GET("location")
    suspend fun getAllLocations(@Query("page") page: Int): Response<LocationsApiResponse>

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): Response<Location>

    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int): Response<EpisodesApiResponse>

    @GET("episode/{id}")
    suspend fun getEpisode(@Path("id") id: Int): Response<Episode>

    companion object {
        val apiClient: Api = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}