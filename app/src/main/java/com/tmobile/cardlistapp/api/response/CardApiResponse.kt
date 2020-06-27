package com.tmobile.cardlistapp.api.response

import com.google.gson.Gson
import com.tmobile.cardlistapp.db.DatabaseCard
import com.tmobile.cardlistapp.model.Page

data class CardApiResponse(val page : Page){
    fun asDatabaseModel(): List<DatabaseCard> {
        return page.cards.map {
            DatabaseCard(Gson().toJson(it))
        }
    }
}