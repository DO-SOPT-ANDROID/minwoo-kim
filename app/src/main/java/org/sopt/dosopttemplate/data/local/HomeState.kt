package org.sopt.dosopttemplate.data.local

sealed class HomeState {
    object Success : HomeState()
    object Error : HomeState()
    object Loading : HomeState()
}
