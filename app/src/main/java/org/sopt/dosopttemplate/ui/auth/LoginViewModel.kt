package org.sopt.dosopttemplate.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.local.UserInfo
import org.sopt.dosopttemplate.data.remote.api.ServicePool
import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.RequestLoginDto
import org.sopt.dosopttemplate.util.UiState

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<UiState<UserInfo>>(UiState.Empty)
    val loginState = _loginState.asStateFlow()

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun clickLoginBtn() {
        viewModelScope.launch {
            runCatching {
                ServicePool.authService.postLogin(
                    RequestLoginDto(
                        id.value.orEmpty(),
                        pw.value.orEmpty()
                    )
                )
            }.onSuccess {
                _loginState.value =
                    UiState.Success(UserInfo(it.username, pw.value, it.nickname, it.id.toString()))

            }.onFailure {
                _loginState.value = UiState.Failure(it.message.toString())
            }
        }
    }
}
