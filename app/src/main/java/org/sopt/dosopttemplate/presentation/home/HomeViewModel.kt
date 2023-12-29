package org.sopt.dosopttemplate.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.model.Follower
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<Follower>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getFollowerList(page: Int) {
        viewModelScope.launch {
            homeRepository.getHomeFollower(page)
                .onSuccess { followerData ->
                    _uiState.value = UiState.Success(followerData)
                }.onFailure { throwable ->
                    _uiState.value = UiState.Failure(throwable.message.toString())
                }
        }
    }
}