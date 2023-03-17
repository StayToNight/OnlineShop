package com.staytonight.onlineshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.staytonight.domain.model.Category
import com.staytonight.domain.model.CategorySection
import com.staytonight.domain.model.FlashSaleSection
import com.staytonight.domain.model.LatestDealSection
import com.staytonight.domain.usecase.home.GetFlashSaleUseCase
import com.staytonight.domain.usecase.home.GetLatestDealUseCase
import com.staytonight.domain.usecase.profile.GetUserDataUseCase
import com.staytonight.onlineshop.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kz.arcana.qrmenu.domain.model.SectionMarker
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestDealUseCase: GetLatestDealUseCase,
    private val getFlashSaleUseCase: GetFlashSaleUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun getUserAvatar() {
        viewModelScope.launch {
            val response = getUserDataUseCase.getUserData()
            _state.value = State.Avatar(response?.avatar)
        }
    }

    fun getPage() {
        viewModelScope.launch(exceptionHandler()) {
            _state.value = State.ShowLoading
            val latest = getLatestDealUseCase.getLatestDeal()
            val flashSales = getFlashSaleUseCase.getFlashSale()

            _state.value = State.Result(
                listOf(
                    CategorySection(
                        listOf(
                            Category(
                                R.drawable.ic_phone.toString(),
                                "Phones"
                            ),
                            Category(
                                R.drawable.ic_headphones.toString(),
                                "Headphones"
                            ),
                            Category(
                                R.drawable.ic_gamepad.toString(),
                                "Games"
                            ),
                            Category(
                                R.drawable.ic_car.toString(),
                                "Cars"
                            ),
                            Category(
                                R.drawable.ic_bed.toString(),
                                "Furniture"
                            ),
                            Category(
                                R.drawable.ic_robot.toString(),
                                "Kids"
                            )
                        )
                    ),
                    LatestDealSection(
                        R.string.latest_deal,
                        latest
                    ),
                    FlashSaleSection(
                        R.string.flash_sale,
                        flashSales
                    )
                )
            )
            _state.value = State.HideLoading

        }
    }

    private fun exceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            _state.value = State.Error(throwable.message.toString())
            throwable.printStackTrace()
        }

    sealed class State {
        object ShowLoading : State()
        object HideLoading : State()
        data class Result(val page: List<SectionMarker>) : State()
        data class Avatar(val avatar: String?) : State()
        data class Error(val error: String?) : State()
    }
}