package org.sopt.dosopttemplate.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.local.HomeState
import org.sopt.dosopttemplate.data.remote.api.ServicePool
import org.sopt.dosopttemplate.data.remote.model.dto.response.follower.ResponseFollowerDto
import org.sopt.dosopttemplate.repository.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val _homeState = MutableLiveData<HomeState>()
    val homeState = _homeState

    private val _followerList = MutableLiveData<List<ResponseFollowerDto.FollowerData>>()
    val followerList = _followerList

    fun getFollowerList() {
        _homeState.value = HomeState.Loading
        viewModelScope.launch {
            homeRepository.getFollowerList(pageNumber)
                .onSuccess {
                    _followerList.value = it
                    _homeState.value = HomeState.Success
                }.onFailure {
                    _homeState.value = HomeState.Error
                }
        }
    }

    companion object {
        const val pageNumber = 2
    }
}