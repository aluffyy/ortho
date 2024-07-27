package com.aluffyyy.ortho.data.api.dto

data class MeaningDto(
    val antonyms: List<Any>? = null,
    val definitionDtos: List<DefinitionDto>? = null,
    val partOfSpeech: String? = null,
    val synonyms: List<String>? = null
)