package com.tmobile.cardlistapp.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tmobile.cardlistapp.R
import com.tmobile.cardlistapp.model.Card
import com.tmobile.cardlistapp.model.CardType
import kotlinx.android.synthetic.main.title_des_item.view.*
import kotlinx.android.synthetic.main.title_item.view.*
import kotlinx.android.synthetic.main.image_title_desc_item.view.*

class CardsAdapter(private val cards : List<CardType>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            1 -> TextDescViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.title_des_item,parent,false))
            2 -> ImageTextDescViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.image_title_desc_item,parent,false))
            else -> TextViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.title_item,parent,false))
        }
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(cards[position].card_type){
            "text" -> (holder as TextViewHolder).bind(cards[position].card)
            "title_description" -> (holder as TextDescViewHolder).bind(cards[position].card)
            "image_title_description" -> (holder as ImageTextDescViewHolder).bind(cards[position].card)
            else -> (holder as TextViewHolder).bind(cards[position].card)
        }
    }

    override fun getItemViewType(position: Int): Int {
       return when(cards[position].card_type){
            "text" -> 0
            "title_description" -> 1
            "image_title_description" -> 2
            else -> -1
        }
    }
    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card){
            //Text
            itemView.textView.text = card.value
            itemView.textView.textSize = card.attributes.font.size.toFloat()
            itemView.textView.setTextColor(Color.parseColor(card.attributes.text_color))
        }
    }

    class TextDescViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card){
            //Title
            itemView.titleTextView.text = card.title.value
            itemView.titleTextView.textSize = card.title.attributes.font.size.toFloat()
            itemView.titleTextView.setTextColor(Color.parseColor(card.title.attributes.text_color))

            //Desc
            itemView.descTextView.text = card.description.value
            itemView.descTextView.textSize = card.description.attributes.font.size.toFloat()
            itemView.descTextView.setTextColor(Color.parseColor(card.description.attributes.text_color))
        }

    }

    class ImageTextDescViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card){
            //Title
            itemView.imgTitleTextView.text = card.title.value
            itemView.imgTitleTextView.textSize = card.title.attributes.font.size.toFloat()
            itemView.imgTitleTextView.setTextColor(Color.parseColor(card.title.attributes.text_color))

            //Desc
            itemView.imgDescTextView.text = card.description.value
            itemView.imgDescTextView.textSize = card.description.attributes.font.size.toFloat()
            itemView.imgDescTextView.setTextColor(Color.parseColor(card.description.attributes.text_color))

            //Image
            Glide.with(itemView)
                .load(card.image.url)
                .centerCrop()
                .apply(RequestOptions().override(card.image.size.width, card.image.size.height))
                .placeholder(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(itemView.imageView)
        }
    }
}