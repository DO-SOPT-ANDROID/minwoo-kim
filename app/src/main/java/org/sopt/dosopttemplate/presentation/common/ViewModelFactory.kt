package org.sopt.dosopttemplate.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.repository.HomeRepositoryImpl
import org.sopt.dosopttemplate.presentation.home.HomeViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(HomeRepositoryImpl()) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
