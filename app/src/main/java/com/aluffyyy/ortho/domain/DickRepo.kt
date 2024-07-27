package com.aluffyyy.ortho.domain

import com.aluffyyy.ortho.util.Result
import kotlinx.coroutines.flow.Flow

interface DickRepo {
    suspend fun getWordResult (
        word: String

    ): Flow<Result>
}