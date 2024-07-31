package com.aluffyyy.ortho.presentation

import com.aluffyyy.ortho.domain.model.WordItem
import com.aluffyyy.ortho.util.Result

data class MainState(
    val isLoading: Boolean=false,
    val searchWord: String="",
    val wordItem: WordItem? = null
)
