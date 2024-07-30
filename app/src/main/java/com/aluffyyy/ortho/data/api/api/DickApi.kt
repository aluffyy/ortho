package com.aluffyyy.ortho.data.api.api

import com.aluffyyy.ortho.data.api.dto.WordResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DickApi {

    @GET("{word}")
    suspend fun getWordResult(
        @Path("word") word: String
    ): WordResultDto?

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }

}