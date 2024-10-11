package com.hvk.jetpackcomposecanvasdemos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val navigator: Navigator
) : ViewModel() {
    fun navigateToBottle() {
        viewModelScope.launch {
            navigator.navigate(
                destination = Destination.BottleScreen
            )
        }
    }
}