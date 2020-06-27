package com.tmobile.cardlistapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tmobile.cardlistapp.db.getDatabase
import com.tmobile.cardlistapp.model.CardType
import com.tmobile.cardlistapp.repository.CardsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class CardListViewModel(application: Application) : AndroidViewModel(application) {
    private val cardsRepo = CardsRepository(getDatabase(application))

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by CardListViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    val cards: LiveData<List<CardType>> = cardsRepo.getCards()
    fun fetchCards(){
        viewModelScope.launch {
            try {
                cardsRepo.fetchCards()
            } catch (networkError: IOException) {
                Log.e("CardListViewModel", networkError.message)
            }
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}