package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.remote.HomeDataSource
import org.sopt.dosopttemplate.domain.model.Follower
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource,
) : HomeRepository {
    override suspend fun getHomeFollower(page: Int): Result<List<Follower>> = runCatching {
        homeDataSource.getHomeFollower(page).toFollowerData()
    }
}