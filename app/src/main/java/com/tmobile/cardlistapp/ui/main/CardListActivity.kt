package com.tmobile.cardlistapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmobile.cardlistapp.R
import com.tmobile.cardlistapp.ui.main.adapter.CardsAdapter
import com.tmobile.cardlistapp.model.CardType
import com.tmobile.cardlistapp.viewmodels.CardListViewModel
import kotlinx.android.synthetic.main.activity_card_list.*

class CardListActivity : AppCompatActivity() {
    private lateinit var mCardListViewModel : CardListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_list)
        mCardListViewModel = ViewModelProvider(this)[CardListViewModel::class.java]
        recyclerViewCards.layoutManager = LinearLayoutManager(this)
        recyclerViewCards.addItemDecoration(
            DividerItemDecoration(
                recyclerViewCards.context,
                DividerItemDecoration.VERTICAL
            )
        )
        subscribeObservers()
        fetchCards()
    }

    private fun subscribeObservers(){
        mCardListViewModel.cards.observe(this, Observer<List<CardType>>(){ cardTypeList ->
            recyclerViewCards.adapter = CardsAdapter(cardTypeList)
        })
    }

    private fun fetchCards(){
        mCardListViewModel.fetchCards()
    }
}
