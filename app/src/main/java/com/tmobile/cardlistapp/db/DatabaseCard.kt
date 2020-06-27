package com.tmobile.cardlistapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.tmobile.cardlistapp.model.CardType

/**
 * DatabaseCard represents a Card entity in the database.
 */
@Entity
data class DatabaseCard constructor(@PrimaryKey  val cardJson: String)

/**
 * Map DatabaseCards to CardType entities
 */
fun List<DatabaseCard>.asCardType(): List<CardType> {
    return map {
        Gson().fromJson(it.cardJson,CardType::class.java)
    }
}