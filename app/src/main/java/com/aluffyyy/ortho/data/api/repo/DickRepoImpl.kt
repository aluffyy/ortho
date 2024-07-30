package com.aluffyyy.ortho.data.api.repo

import android.app.Application
import com.aluffyyy.ortho.R
import com.aluffyyy.ortho.data.api.api.DickApi
import com.aluffyyy.ortho.data.api.mapper.toWordItem
import com.aluffyyy.ortho.domain.DickRepo
import com.aluffyyy.ortho.domain.model.WordItem
import com.aluffyyy.ortho.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DickRepoImpl @Inject constructor(
    private val dickApi: DickApi,
    private val application: Application
) : DickRepo {
    override suspend fun getWordResult(word: String): Flow<Result<WordItem>> {
        return flow {
            emit(Result.Loading(true))

            val remoteWordResultDto = try {
                dickApi.getWordResult(word)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
                null
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
                null
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_get_result)))
                null
            }
            remoteWordResultDto?.let { wordResultDto ->
                wordResultDto[0]?.let { WordItemDto ->
                    emit(Result.Success(WordItemDto.toWordItem()))
                    emit(Result.Loading(false))
                    return@flow
                }
            }
            emit(Result.Error(application.getString(R.string.can_t_get_result)))
            emit(Result.Loading(false))
        }
    }
}