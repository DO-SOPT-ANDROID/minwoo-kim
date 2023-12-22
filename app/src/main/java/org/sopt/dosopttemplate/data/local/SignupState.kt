package org.sopt.dosopttemplate.data.local

sealed class SignupState {
    data class Success(val data: UserInfo) : SignupState()
    object Error : SignupState()
    object Loading : SignupState()
}