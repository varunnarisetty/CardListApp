package com.tmobile.cardlistapp.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tmobile.cardlistapp.api.response.CardApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://private-8ce77c-tmobiletest.apiary-mock.com/test/"
interface CardsApi {
    @GET("home")
    fun getCardsAsync() : Deferred<CardApiResponse>

    companion object {
        operator fun invoke() : CardsApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(CardsApi::class.java)
        }
    }
}