package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.repository.HomeRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl,
    ): HomeRepository
}