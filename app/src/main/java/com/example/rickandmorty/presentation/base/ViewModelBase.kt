package com.example.rickandmorty.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.ExceptionHandler
import com.example.rickandmorty.presentation.notification.NotificationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class ViewModelBase : ViewModel() {

    fun launchWithState(action: suspend () -> Unit) =
        performLaunchWithState(
            action,
            {},
            {}
        )

    private fun performLaunchWithState(
        action: suspend () -> Unit,
        failedAction: suspend () -> Unit,
        finallyAction: () -> Unit
    ) {
        viewModelScope.launch {
            if (!_isLoading.value) {
                try {
                    _isLoading.value = true
                    action()
                } catch (exc: Exception) {
                    NotificationService.showError(ExceptionHandler.getErrorMessage(exc))
                    failedAction()
                } finally {
                    _isLoading.value = false
                    finallyAction()
                }
            }
        }
    }

    companion object {
        private val _isLoading = MutableStateFlow(false)
        val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    }
}