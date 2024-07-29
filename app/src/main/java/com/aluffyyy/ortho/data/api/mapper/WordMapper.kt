package com.aluffyyy.ortho.data.api.mapper

import com.aluffyyy.ortho.data.api.dto.DefinitionDto
import com.aluffyyy.ortho.data.api.dto.MeaningDto
import com.aluffyyy.ortho.data.api.dto.SourceUrlDto
import com.aluffyyy.ortho.data.api.dto.WordItemDto
import com.aluffyyy.ortho.domain.model.Definition
import com.aluffyyy.ortho.domain.model.Meaning
import com.aluffyyy.ortho.domain.model.SourceUrls
import com.aluffyyy.ortho.domain.model.WordItem

fun WordItemDto.toWordItem() = WordItem (
    word = word ?: "",
    meaning = meaning?.map {
        it.toMeaning()
    } ?: emptyList(),
    phonetic = phonetic ?: "",
    sourceUrls = SourceUrlDto().toSourceUrl(),
)

fun MeaningDto.toMeaning() = Meaning(
    definition = definitionDtoToDefinition(definitions?.get(0)),
    partOfSpeech = partOfSpeech ?: ""
)


fun definitionDtoToDefinition(
    definitionDto: DefinitionDto?
) = Definition(
    definition = definitionDto?.definition?: "",
    example = definitionDto?.example ?: ""
)
fun SourceUrlDto.toSourceUrl(): SourceUrls {
    return SourceUrls(
        sourceUrls = this.sourceUrls ?: emptyList() // Handle potential nulls
    )
}