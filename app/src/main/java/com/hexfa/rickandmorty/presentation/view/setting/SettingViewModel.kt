package com.hexfa.rickandmorty.presentation.view.setting

import androidx.lifecycle.viewModelScope
import com.hexfa.rickandmorty.domain.repository.SettingRepository
import com.hexfa.rickandmorty.presentation.base.ViewModelBase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository
) : ViewModelBase() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme = _isDarkTheme.asStateFlow()

    private val _isGridView = MutableStateFlow(false)
    val isGridView = _isGridView.asStateFlow()

    init {
        settingRepository.isDarkTheme.onEach { isDark ->
            _isDarkTheme.value = isDark
        }.launchIn(viewModelScope)

        settingRepository.isGridView.onEach { isGrid ->
            _isGridView.value = isGrid
        }.launchIn(viewModelScope)
    }

    fun toggleTheme() {
        launchWithState {
            val newTheme = !_isDarkTheme.value
            _isDarkTheme.value = newTheme
            settingRepository.saveTheme(newTheme)
        }
    }

    fun toggleGridView() {
        launchWithState {
            val newState = !_isGridView.value
            _isGridView.value = newState
            settingRepository.saveGrid(newState)
        }
    }
}