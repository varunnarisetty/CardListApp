package com.tmobile.cardlistapp.api

import com.tmobile.cardlistapp.db.CardsDatabase

class CardsApiClient(private val cardsDatabase: CardsDatabase) {
    suspend fun getCardsFromApi(){
        val cardApiResponse = CardsApi().getCardsAsync().await()
        //writing the newly fetched data to DB as its the single source of truth.
        //It will be populated to the UI from LiveData
        cardsDatabase.cardDao.insertAll(cardApiResponse.asDatabaseModel())
    }
}