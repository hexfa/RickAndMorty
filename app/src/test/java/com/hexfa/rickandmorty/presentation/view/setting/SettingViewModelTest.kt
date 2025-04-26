package com.hexfa.rickandmorty.presentation.view.setting

import app.cash.turbine.test
import com.hexfa.rickandmorty.domain.repository.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class SettingViewModelTest {

    private lateinit var viewModel: SettingViewModel
    private lateinit var settingRepository: SettingRepository
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var testScope: TestScope

    private val darkThemeFlow = MutableStateFlow(false)
    private val gridViewFlow = MutableStateFlow(false)

    @Before
    fun setup() {
        settingRepository = mock()
        whenever(settingRepository.isDarkTheme).thenReturn(darkThemeFlow)
        whenever(settingRepository.isGridView).thenReturn(gridViewFlow)

        testScope = TestScope(testDispatcher)
        viewModel = SettingViewModel(settingRepository)
    }

    @Test
    fun `initial theme state is loaded from repository`() = runTest {
        darkThemeFlow.value = true

        viewModel.isDarkTheme.test {
            assert(awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `initial grid view state is loaded from repository`() = runTest {
        gridViewFlow.value = true

        viewModel.isGridView.test {
            assert(awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `toggleTheme updates state and saves to repository`() = runTest {
        viewModel.toggleTheme()

        viewModel.isDarkTheme.test {
            assert(awaitItem())
            cancelAndConsumeRemainingEvents()
        }

        verify(settingRepository).saveTheme(true)
    }
}