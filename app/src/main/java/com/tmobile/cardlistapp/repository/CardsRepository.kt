package com.tmobile.cardlistapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tmobile.cardlistapp.api.CardsApiClient
import com.tmobile.cardlistapp.db.CardsDatabase
import com.tmobile.cardlistapp.db.asCardType
import com.tmobile.cardlistapp.model.CardType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardsRepository(private val database: CardsDatabase){

    fun getCards() : LiveData<List<CardType>> {
        return Transformations.map(database.cardDao.getCards()) {
            it.asCardType()
        }
    }

    suspend fun fetchCards(){
        withContext(Dispatchers.IO) {
            CardsApiClient(database).getCardsFromApi()
        }
    }
}