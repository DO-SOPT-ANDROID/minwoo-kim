package org.sopt.dosopttemplate.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.local.SignupState
import org.sopt.dosopttemplate.data.local.UserInfo
import org.sopt.dosopttemplate.data.remote.api.ServicePool
import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.RequestSignupDto
import java.util.regex.Pattern

class SignupViewModel : ViewModel() {
    private val _signupState = MutableLiveData<SignupState>()
    val signupState = _signupState

    private var _isSignupBtn = MutableLiveData(false)
    val isSignupBtn = _isSignupBtn

    val id: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")
    val nickname: MutableLiveData<String> = MutableLiveData("")
    val address: MutableLiveData<String> = MutableLiveData("")

    val isIdValid = id.map { ID_PATTERN.matcher(it).matches() }

    val isPwValid = pw.map { PW_PATTERN.matcher(it).matches() }

    fun signupBtnEnabled() {
        _isSignupBtn.value = (isIdValid.value == true && isPwValid.value == true)
    }

    fun postSignup() {
        _signupState.value = SignupState.Loading
        viewModelScope.launch {
            runCatching {
                ServicePool.authService.postSignup(
                    RequestSignupDto(
                        id.value.orEmpty(),
                        pw.value.orEmpty(),
                        nickname.value.orEmpty()
                    )
                )
            }.onSuccess {
                _signupState.value = SignupState.Success(
                    UserInfo(
                        id.value,
                        pw.value,
                        nickname.value,
                        address.value
                    )
                )
            }.onFailure {
                _signupState.value = SignupState.Error
            }
        }
    }

    companion object {
        private const val ID_REGEX =
            "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{6,10}\$"
        private const val PW_REGEX =
            "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[^\\w\\s])[a-zA-Z\\d\\S]{6,12}\$"
        val ID_PATTERN: Pattern = Pattern.compile(ID_REGEX)
        val PW_PATTERN: Pattern = Pattern.compile(PW_REGEX)
    }
}