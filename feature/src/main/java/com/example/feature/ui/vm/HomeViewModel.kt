package com.example.feature.ui.vm

import androidx.lifecycle.viewModelScope
import com.example.base.BaseViewModel
import com.example.common.Mapper
import com.example.common.PagingModel
import com.example.domain.entity.NewEntity
import com.example.domain.usecase.GetNewsUseCase
import com.example.feature.model.NewUiModel
import com.example.feature.ui.contract.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val newMapper: Mapper<NewEntity, NewUiModel>,
): BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    private val allNewsList: ArrayList<NewUiModel> = ArrayList()

    override fun createInitialState(): HomeContract.State {
        return HomeContract.State(
            homeState = HomeContract.HomeState.Idle
        )
    }

    override fun handleEvent(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.OnRefresh, is HomeContract.Event.FetchData -> {
                fetchData()
            }
            HomeContract.Event.LoadMoreData -> {
                loadMore()
            }
            is HomeContract.Event.NewSelected -> {
                setEffect { HomeContract.Effect.NavigateToNewDetails(event.newUiModel) }
            }
        }
    }

    private fun fetchData(page: Int = 1) {
        viewModelScope.launch {
            getNewsUseCase.execute(page)
                .onStart {
                    if (page == 1) setState { copy(homeState = HomeContract.HomeState.Loading) }
                }
                .catch {
                    // Set Effect
                    setEffect { HomeContract.Effect.ShowError(message = it.message) }
                    setState { copy(homeState = HomeContract.HomeState.Idle) }
                }
                .collect { pagingModel ->
                    if (page == 1) allNewsList.clear()
                    val currentNewsList = newMapper.fromList(pagingModel.data)
                    allNewsList.addAll(currentNewsList)
                    setState {
                        copy(
                            homeState = if (allNewsList.isNotEmpty()) HomeContract.HomeState.Success(
                                news = PagingModel(allNewsList, pagingModel.total, pagingModel.currentPage),
                                title = allNewsList.firstOrNull()?.sourceUiModel?.name ?: ""
                            ) else HomeContract.HomeState.Empty
                        )
                    }
                }
        }
    }

    private fun loadMore() {
        when(val state = currentState.homeState) {
            is HomeContract.HomeState.Success -> {
                if (state.news.total > allNewsList.size)
                    fetchData(state.news.currentPage + 1)
            }
            else -> return
        }
    }

}