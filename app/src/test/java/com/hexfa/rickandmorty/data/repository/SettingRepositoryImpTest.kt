package com.hexfa.rickandmorty.data.repository

import com.hexfa.rickandmorty.data.datasource.local.SettingDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class SettingRepositoryImpTest {

    private lateinit var settingDataSource: SettingDataSource
    private lateinit var repository: SettingRepositoryImp

    @Before
    fun setUp() {
        settingDataSource = mock(SettingDataSource::class.java)
        repository = SettingRepositoryImp(settingDataSource)
    }

    @Test
    fun `isDarkTheme returns correct value from dataSource`() = runTest {
        // Given
        val expectedFlow = flowOf(true)
        `when`(settingDataSource.isDarkTheme).thenReturn(expectedFlow)

        // When
        val result = repository.isDarkTheme

        // Then
        assertEquals(expectedFlow, result)
    }

    @Test
    fun `isGridView returns correct value from dataSource`() = runTest {
        val expectedFlow = flowOf(false)
        `when`(settingDataSource.isGridView).thenReturn(expectedFlow)

        val result = repository.isGridView

        assertEquals(expectedFlow, result)
    }

    @Test
    fun `saveTheme calls dataSource with correct value`() = runTest {
        val isDark = true

        repository.saveTheme(isDark)

        verify(settingDataSource).saveTheme(isDark)
    }

    @Test
    fun `saveGrid calls dataSource with correct value`() = runTest {
        val isGrid = false

        repository.saveGrid(isGrid)

        verify(settingDataSource).saveGridView(isGrid)
    }
}