package ir.vbile.app.shopinglist.data.remote

import ir.vbile.app.shopinglist.*
import ir.vbile.app.shopinglist.data.model.*
import retrofit2.*
import retrofit2.http.*

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchForImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.API_KEYS
    ): Response<ImageResponse>
}