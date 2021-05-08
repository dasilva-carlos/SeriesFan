package com.dasilva.carlos.seriesfan.network.api

import com.dasilva.carlos.seriesfan.network.api.dto.ShowDTO
import com.dasilva.carlos.seriesfan.network.api.dto.ShowDetailDTO
import com.dasilva.carlos.seriesfan.network.api.dto.ShowRatingDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ShowsApi {
    @GET("/search/shows")
    fun search(
        @Query("q") query: String
    ): Flow<List<ShowRatingDTO>>

    @GET("/shows")
    fun getShowsList(
        @Query("page") page: Int
    ): Flow<List<ShowDTO>>

    @GET("/shows/{id}?embed=episodes")
    fun getShowDetails(
        @Path("id") id: Int
    ): Flow<ShowDetailDTO>
}
