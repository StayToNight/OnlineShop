package com.staytonight.onlineshop.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staytonight.domain.model.Details
import com.staytonight.domain.usecase.details.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private var quantity = 0
    private var currentItem: Details? = null


    fun getDetails() {
        viewModelScope.launch {
            _state.value = State.ShowLoading
            val response = getDetailsUseCase.getDetails()
            currentItem = response
            _state.value = State.Detail(response)
            _state.value = State.HideLoading
        }
    }

    fun addToCart(): Int? {
        quantity++
        return currentItem?.price?.times(quantity)
    }


    fun removeFromCart(): Int? {
        quantity--
        return currentItem?.price?.times(quantity)
    }

    sealed class State {
        object ShowLoading : State()
        object HideLoading : State()
        data class Detail(val details: Details) : State()
    }
}
