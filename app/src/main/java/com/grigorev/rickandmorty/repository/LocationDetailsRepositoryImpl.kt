package com.grigorev.rickandmorty.repository

import com.grigorev.rickandmorty.api.Api
import com.grigorev.rickandmorty.dao.LocationsDao
import com.grigorev.rickandmorty.dto.Location
import com.grigorev.rickandmorty.entity.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class LocationDetailsRepositoryImpl(private val dao: LocationsDao, val id: Int) : LocationDetailsRepository {

    override val flow = dao.getLocationById(id)
        .map { it.toDto() }
        .flowOn(Dispatchers.Default)

    override suspend fun getLocationById(id: Int) {
        val location: Location
        try {
            val response = Api.apiClient.getLocation(id)
            if (!response.isSuccessful) {
                throw Exception("Response was not successful")
            }
            location = response.body()!!
            dao.insert(location.toEntity())
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

}