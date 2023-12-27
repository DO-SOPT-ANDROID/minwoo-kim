package org.sopt.dosopttemplate.repository

import org.sopt.dosopttemplate.data.remote.api.ServicePool
import org.sopt.dosopttemplate.data.remote.model.dto.response.follower.ResponseFollowerDto

class HomeRepository {
    suspend fun getFollowerList(pageNumber: Int): Result<List<ResponseFollowerDto.FollowerData>> =
        runCatching {
            ServicePool.followerService.getFollowerList(pageNumber).followerData
        }
}