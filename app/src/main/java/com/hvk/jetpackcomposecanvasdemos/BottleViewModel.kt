package com.hvk.jetpackcomposecanvasdemos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BottleViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun back() {
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }
}