package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.remote.api.ServicePool
import org.sopt.dosopttemplate.data.model.remote.model.dto.response.follower.ResponseFollowerDto

class HomeRepositoryImpl {
    suspend fun getFollowerList(pageNumber: Int): Result<List<ResponseFollowerDto.FollowerData>> =
        runCatching {
            ServicePool.followerService.getFollowerList(pageNumber).followerData
        }
}