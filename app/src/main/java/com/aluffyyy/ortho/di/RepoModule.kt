package com.aluffyyy.ortho.di

import com.aluffyyy.ortho.data.api.repo.DickRepoImpl
import com.aluffyyy.ortho.domain.DickRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun bindDickRepo(
        dickRepoImpl: DickRepoImpl
    ): DickRepo
}




