package com.task_baham.viewmodel.home

import androidx.paging.PagingData
import androidx.test.platform.app.InstrumentationRegistry
import com.task_baham.MyApplication
import com.task_baham.repository.MediaRepository
import com.task_baham.viewModel.home.HomeViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    private lateinit var viewModel: HomeViewModel
    @Mock
    private lateinit var mediaRepository: MediaRepository

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as MyApplication,
            mock(MediaRepository::class.java)
        )
    }

    @Test
    fun `getMedia should return PagingData of files`() = runBlockingTest {
        // Given
        val pagingData = mock(PagingData::class.java) as PagingData<File>
        Mockito.`when`(mediaRepository.getMedia()).thenReturn(flowOf(pagingData))

        // When
        val result = viewModel.getMedia().first()

        // Then
        assertEquals(pagingData, result)
    }
}