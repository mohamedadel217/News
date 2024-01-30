package com.example.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.example.common.PagingModel
import com.example.domain.entity.NewEntity
import com.example.domain.usecase.GetNewsUseCase
import com.example.feature.mapper.NewDomainUiMapper
import com.example.feature.ui.contract.HomeContract
import com.example.feature.ui.vm.HomeViewModel
import com.example.feature.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()

    @MockK
    private lateinit var getNewsUseCase: GetNewsUseCase

    private val newMapper = NewDomainUiMapper()

    private lateinit var homeViewModel: HomeViewModel


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        Dispatchers.setMain(dispatcher)
        // Create HomeViewModel before every test
        homeViewModel = HomeViewModel(
            getNewsUseCase = getNewsUseCase,
            newMapper = newMapper
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test fetch data success`() = runTest {

        val newsItems = TestDataGenerator.generateNews()
        val newsFlow = flowOf(newsItems)

        // Given
        coEvery { getNewsUseCase.execute(1) } returns newsFlow

        // When && Assertions
        homeViewModel.uiState.test {
            // Expect Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Idle
                )
            )
            homeViewModel.setEvent(HomeContract.Event.FetchData)
            // Expect Loading
            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Loading
                )
            )
            // Expect Success
            val expected = expectItem()
            val uiModels = newMapper.fromList(newsItems.data)
            Truth.assertThat(expected).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Success(
                        news = PagingModel(uiModels, newsItems.total, newsItems.currentPage),
                        title = uiModels.firstOrNull()?.sourceUiModel?.name ?: ""
                    )
                )
            )

            val expectedData =
                (expected.homeState as HomeContract.HomeState.Success).news
            Truth.assertThat(expectedData)
                .isEqualTo(PagingModel(uiModels, newsItems.total, newsItems.currentPage))

            val models = (expected.homeState as HomeContract.HomeState.Success).news.data
            Truth.assertThat(
                models
            ).containsExactlyElementsIn(uiModels)


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getNewsUseCase.execute(1) }
    }

    @Test
    fun `test fetch data fail`() = runTest {
        val newsFlow = flow<PagingModel<List<NewEntity>>> {
            throw Exception("error string")
        }

        // Given
        coEvery { getNewsUseCase.execute(1) } returns newsFlow

        // When && Assertions
        homeViewModel.uiState.test {
            // Expect Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Idle
                )
            )
            homeViewModel.setEvent(HomeContract.Event.FetchData)
            // Expect Loading
            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Loading
                )
            )
            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        // When && Assertions (UiEffect)
        homeViewModel.effect.test {
            // Expect ShowError Effect
            val expected = expectItem()
            val expectedData = (expected as HomeContract.Effect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                HomeContract.Effect.ShowError("error string")
            )
            Truth.assertThat(expectedData).isEqualTo("error string")
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getNewsUseCase.execute(1) }
    }

    @Test
    fun `test fetch data success using pull to refresh`() = runTest {

        val newsItems = TestDataGenerator.generateNews()
        val newsFlow = flowOf(newsItems)

        // Given
        coEvery { getNewsUseCase.execute(1) } returns newsFlow

        // When && Assertions
        homeViewModel.uiState.test {
            // Expect Idle from initial state
            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Idle
                )
            )
            homeViewModel.setEvent(HomeContract.Event.OnRefresh)
            // Expect Loading
            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Loading
                )
            )
            // Expect Success
            val expected = expectItem()
            val uiModels = newMapper.fromList(newsItems.data)
            Truth.assertThat(expected).isEqualTo(
                HomeContract.State(
                    homeState = HomeContract.HomeState.Success(
                        news = PagingModel(uiModels, newsItems.total, newsItems.currentPage),
                        title = uiModels.firstOrNull()?.sourceUiModel?.name ?: ""
                    )
                )
            )

            val expectedData =
                (expected.homeState as HomeContract.HomeState.Success).news
            Truth.assertThat(expectedData)
                .isEqualTo(PagingModel(uiModels, newsItems.total, newsItems.currentPage))

            val models = (expected.homeState as HomeContract.HomeState.Success).news.data
            Truth.assertThat(
                models
            ).containsExactlyElementsIn(uiModels)


            //Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }


        // Then
        coVerify { getNewsUseCase.execute(1) }
    }

    @Test
    fun `test fetch data success using load more`() = runTest {
        withContext(Dispatchers.Default) {
            val newsItems = TestDataGenerator.generateNews()
            val newsFlow = flowOf(newsItems)
            val newsItemsPage2 = TestDataGenerator.generateNews(2)

            // Given
            coEvery { getNewsUseCase.execute(1) } returns newsFlow
            coEvery { getNewsUseCase.execute(2) } returns flowOf(newsItemsPage2)

            // When && Assertions
            homeViewModel.uiState.test {
                // Expect Idle from initial state
                Truth.assertThat(expectItem()).isEqualTo(
                    HomeContract.State(
                        homeState = HomeContract.HomeState.Idle
                    )
                )
                homeViewModel.setEvent(HomeContract.Event.FetchData)
                // Expect Loading
                Truth.assertThat(expectItem()).isEqualTo(
                    HomeContract.State(
                        homeState = HomeContract.HomeState.Loading
                    )
                )
                // Expect Success
                val expected = expectItem()
                val uiModels = newMapper.fromList(newsItems.data)
                Truth.assertThat(expected).isEqualTo(
                    HomeContract.State(
                        homeState = HomeContract.HomeState.Success(
                            news = PagingModel(uiModels, newsItems.total, newsItems.currentPage),
                            title = uiModels.firstOrNull()?.sourceUiModel?.name ?: ""
                        )
                    )
                )

                val expectedData =
                    (expected.homeState as HomeContract.HomeState.Success).news
                Truth.assertThat(expectedData)
                    .isEqualTo(PagingModel(uiModels, newsItems.total, newsItems.currentPage))

                val models = (expected.homeState as HomeContract.HomeState.Success).news.data
                Truth.assertThat(
                    models
                ).containsExactlyElementsIn(uiModels)

                homeViewModel.setEvent(HomeContract.Event.LoadMoreData)
                // Expect Success
                val expectedPageTwo = expectItem()
                Truth.assertThat(expectedPageTwo).isEqualTo(
                    HomeContract.State(
                        homeState = HomeContract.HomeState.Success(
                            news = PagingModel(
                                uiModels + uiModels,
                                newsItemsPage2.total,
                                newsItemsPage2.currentPage
                            ),
                            title = uiModels.firstOrNull()?.sourceUiModel?.name ?: ""
                        )
                    )
                )

                val modelsPageTwo = (expected.homeState as HomeContract.HomeState.Success).news
                Truth.assertThat(modelsPageTwo)
                    .isEqualTo(
                        PagingModel(
                            uiModels + uiModels,
                            newsItems.total,
                            newsItems.currentPage
                        )
                    )

                Truth.assertThat(
                    modelsPageTwo.data
                ).containsExactlyElementsIn(uiModels + uiModels)


                //Cancel and ignore remaining
                cancelAndIgnoreRemainingEvents()
            }


            // Then
            coVerify { getNewsUseCase.execute(1) }
            coVerify { getNewsUseCase.execute(2) }
        }
    }

    @Test
    fun test_select_new_item() = runTest {

        val news = TestDataGenerator.generateNews()
        val selectedNewUiModel = newMapper.from(news.data.firstOrNull())

        // Given (no-op)

        // When && Assertions
        homeViewModel.event.test {
            homeViewModel.setEvent(HomeContract.Event.NewSelected(newUiModel = selectedNewUiModel))

            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.Event.NewSelected(selectedNewUiModel)
            )
            // Cancel and ignore remaining
            cancelAndIgnoreRemainingEvents()
        }

        homeViewModel.effect.test {
            Truth.assertThat(expectItem()).isEqualTo(
                HomeContract.Effect.NavigateToNewDetails(selectedNewUiModel)
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

}