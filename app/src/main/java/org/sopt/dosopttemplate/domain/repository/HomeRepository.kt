package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.model.Follower

interface HomeRepository {
    suspend fun getHomeFollower(page: Int): Result<List<Follower>>
}