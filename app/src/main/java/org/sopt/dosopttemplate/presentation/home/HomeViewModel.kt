package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.remote.response.follower.ResponseFollowerDto
import org.sopt.dosopttemplate.data.repository.HomeRepositoryImpl
import javax.inject.Inject

class HomeViewModel(private val homeRepository: HomeRepositoryImpl) : ViewModel() {
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