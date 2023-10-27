package org.sopt.dosopttemplate.model.data

import androidx.annotation.DrawableRes

sealed class Friend {
    data class FriendMy(
        @DrawableRes val profileImage: Int,
        val name: String,
        val description: String?
    ) : Friend()

    data class FriendInfo(
        @DrawableRes val profileImage: Int,
        val name: String,
        val description: String?,
        val music: String?
    ) : Friend()

    data class FriendBirthday(
        @DrawableRes val profileImage: Int,
        val name: String,
        val description: String?,
        val birthday: String?
    ) : Friend()
}
