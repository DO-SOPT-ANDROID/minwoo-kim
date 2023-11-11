package org.sopt.dosopttemplate.model

import android.content.Context
import org.sopt.dosopttemplate.model.data.UserInfo

object UserSharedPreferences {
    private const val USER_INFO: String = "UserInfo"

    fun setUserInfo(context: Context, userInfo: UserInfo) {
        val editor = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit()
        with(editor) {
            //흠.. 객체로 저장 못하나?!
            putString("id", userInfo.id)
            putString("pw", userInfo.pw)
            putString("nickname", userInfo.nickName)
            putString("address", userInfo.address)
            apply()
        }
    }

    fun getUserInfo(context: Context): UserInfo {
        val prefs = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE)

        val userId = prefs.getString("id", null)
        val userPw = prefs.getString("pw", null)
        val userNickname = prefs.getString("nickname", null)
        val userAddress = prefs.getString("address", null)

        return UserInfo(
            userId,
            userPw,
            userNickname,
            userAddress,
        )
    }

    fun removeUserInfo(context: Context) {
        val editor = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}