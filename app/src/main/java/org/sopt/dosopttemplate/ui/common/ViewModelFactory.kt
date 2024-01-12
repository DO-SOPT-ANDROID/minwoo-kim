package org.sopt.dosopttemplate.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.repository.HomeRepository
import org.sopt.dosopttemplate.ui.home.HomeViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(HomeRepository()) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}
