package com.example.feature.ui.contract

import com.example.base.UiEffect
import com.example.base.UiEvent
import com.example.base.UiState
import com.example.common.PagingModel
import com.example.feature.model.NewUiModel

class HomeContract {

    sealed class Event : UiEvent {
        object OnRefresh : Event()
        object FetchData : Event()
        object LoadMoreData: Event()
        data class NewSelected(val newUiModel: NewUiModel): Event()
    }

    data class State(
        val homeState: HomeState
    ) : UiState

    sealed class HomeState {
        object Idle : HomeState()
        object Empty: HomeState()
        object Loading : HomeState()
        data class Success(val news : PagingModel<List<NewUiModel>>, val title: String) : HomeState()
    }

    sealed class Effect : UiEffect {

        data class ShowError(val message : String?) : Effect()
        data class NavigateToNewDetails(val newUiModel: NewUiModel): Effect()

    }

}