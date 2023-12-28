package org.sopt.dosopttemplate.data.model.local

sealed class HomeState {
    object Success : HomeState()
    object Error : HomeState()
    object Loading : HomeState()
}
