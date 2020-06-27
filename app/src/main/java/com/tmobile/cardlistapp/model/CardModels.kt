package com.tmobile.cardlistapp.model

data class Attributes(val text_color: String, val font: Font)

data class Card (
    val value: String,
    val attributes: Attributes,
    val image: Image,
    val title: Title,
    val description: Description
)

data class CardType(val card_type: String, val card:Card)

data class Description(val value :String,val attributes: Attributes)

data class Font(val size: Int)

data class Image(val url: String, val size: Size)

data class Page(val cards : List<CardType>)

data class Size(val width: Int, val height: Int)

data class Title(val value: String,val attributes : Attributes)