package com.naguirrel.plataslabs8_muchos.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.naguirrel.plataslabs8_muchos.data.remote.dto.CharacterListResponse
import com.naguirrel.plataslabs8_muchos.data.remote.dto.LocationListResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface RmApi {

    // 20 por defecto
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int = 1): CharacterListResponse

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int = 1): LocationListResponse

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun create(): RmApi {
            val logging = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val json = Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            }
            val contentType = "application/json".toMediaType()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory(contentType))
                .build()

            return retrofit.create(RmApi::class.java)
        }
    }
}
