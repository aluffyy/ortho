package com.aluffyyy.ortho.data.api.dto

data class WordItemDto(
    val license: LicenseDto? = null,
    val meaning: List<MeaningDto>? = null,
    val phonetic: String? = null,
//    val phonetic: List<PhoneticDto>,
    val sourceUrls: List<String>? = null,
    val word: String? = null
)