package com.staytonight.onlineshop.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staytonight.domain.usecase.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = State.ShowLoading
            _state.value = State.Login(loginUseCase.login(email, password))
            _state.value = State.HideLoading
        }
    }


    sealed class State {
        object ShowLoading : State()
        object HideLoading : State()
        data class Login(val loginState: Boolean) : State()
    }

}