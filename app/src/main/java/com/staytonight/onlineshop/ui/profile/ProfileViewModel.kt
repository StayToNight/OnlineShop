package com.staytonight.onlineshop.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staytonight.domain.model.User
import com.staytonight.domain.usecase.profile.ChangeAvatarUseCase
import com.staytonight.domain.usecase.profile.GetUserDataUseCase
import com.staytonight.domain.usecase.profile.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val changeAvatarUseCase: ChangeAvatarUseCase
) : ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun getUserData() {
        viewModelScope.launch {
            _state.value = State.ShowLoading
            val response = getUserDataUseCase.getUserData()
            _state.value = State.UserData(response)
            _state.value = State.HideLoading
        }
    }

    fun changeAvatar(avatar: String?) {
        if (avatar.isNullOrEmpty()) return
        viewModelScope.launch {
            changeAvatarUseCase.changeAvatar(avatar)
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
        }
    }

    sealed class State {
        object ShowLoading : State()
        object HideLoading : State()
        data class UserData(val user: User?) : State()
    }
}