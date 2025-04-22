package com.hexfa.rickandmorty.data.repository

import com.hexfa.rickandmorty.data.datasource.local.SettingDataSource
import com.hexfa.rickandmorty.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow

class SettingRepositoryImp(private val settingDataSource: SettingDataSource) : SettingRepository {

    override val isDarkTheme: Flow<Boolean> = settingDataSource.isDarkTheme
    override val isGridView: Flow<Boolean> = settingDataSource.isGridView

    override suspend fun saveTheme(isDark: Boolean) {
        settingDataSource.saveTheme(isDark)
    }

    override suspend fun saveGrid(isGridView: Boolean) {
        settingDataSource.saveGridView(isGridView)
    }
}