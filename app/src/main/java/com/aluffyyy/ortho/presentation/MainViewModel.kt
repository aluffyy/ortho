package com.aluffyyy.ortho.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aluffyyy.ortho.domain.DickRepo
import com.aluffyyy.ortho.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val DickRepo: DickRepo
) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState = _mainState.asStateFlow()

    private var searchJob: Job? = null

    init {
        _mainState.update {
            it.copy(searchWord = "")
        }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            loadWorResult()
        }
    }

    fun onEven(mainUiEvents: MainUiEvents) {
        when (mainUiEvents) {
            MainUiEvents.OnSearchClick -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    loadWorResult()
                }
            }

            is MainUiEvents.OnSearchWordChange -> {
                _mainState.update {
                    it.copy(searchWord = mainUiEvents.newWord.lowercase())
                }
            }
        }
    }

    private fun loadWorResult() {
        viewModelScope.launch {
            DickRepo.getWordResult(
                mainState.value.searchWord
            ).collect { result ->
                when (result) {
                    is Result.Error -> Unit
                    is Result.Loading -> {
                        _mainState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Result.Success -> {
                        result.data?.let { wordItem ->
                            _mainState.update {
                                it.copy(wordItem = wordItem)
                            }
                        }
                    }
                }

            }
        }
    }
}