package com.example.local

import androidx.test.filters.SmallTest
import com.example.common.PagingModel
import com.example.data.repository.LocalDataSource
import com.example.local.database.NewsDao
import com.example.local.mapper.NewLocalDataMapper
import com.example.local.source.LocalDataSourceImp
import com.example.local.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class LocalDataSourceImpTest {

    @MockK
    private lateinit var newsDao: NewsDao
    private val newLocalDataMapper = NewLocalDataMapper()

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        // Create RemoteDataSourceImp before every test
        localDataSource = LocalDataSourceImp(
            newsDao = newsDao,
            newMapper = newLocalDataMapper,
        )
    }

    @Test
    fun `test get news success`() = runTest {

        val news = TestDataGenerator.generateNews()
        val expected = PagingModel(
            data = newLocalDataMapper.fromList(news),
            total = 1,
            currentPage = 0
        )

        // Given
        coEvery { newsDao.getAllNews() } returns news

        // When
        val result = localDataSource.getNews()

        // Then
        coVerify { newsDao.getAllNews() }

        // Assertion
        Truth.assertThat(result).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun `test get news fail`() = runTest {

        // Given
        coEvery { newsDao.getAllNews() } throws Exception()

        // When
        localDataSource.getNews()

        // Then
        coVerify { newsDao.getAllNews() }

    }

}