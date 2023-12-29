package org.sopt.dosopttemplate.data.datasource.remote

import org.sopt.dosopttemplate.data.model.remote.response.follower.ResponseFollowerDto
import org.sopt.dosopttemplate.data.service.HomeService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val homeService: HomeService,
) {
    suspend fun getHomeFollower(page: Int): ResponseFollowerDto =
        homeService.getFollowerList(page)
}