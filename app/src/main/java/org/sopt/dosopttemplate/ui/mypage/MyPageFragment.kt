package org.sopt.dosopttemplate.ui.mypage

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.model.UserSharedPreferences
import org.sopt.dosopttemplate.model.data.UserInfo
import org.sopt.dosopttemplate.ui.auth.LoginActivity
import org.sopt.dosopttemplate.util.base.BindingFragment

class MyPageFragment : BindingFragment<FragmentMyPageBinding>() {
    private lateinit var userInfo: UserInfo

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding = FragmentMyPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLogoutBtnListener()
        getUserInfo()
        initText()
    }

    private fun getUserInfo() {
        userInfo = UserSharedPreferences.getUserInfo(requireContext())
    }

    private fun initText() {
        binding.run {
            tvMainId.text = userInfo.id
            tvMainNickName.text = userInfo.nickName
            tvMainAddress.text = userInfo.address
        }
    }

    private fun initLogoutBtnListener() {
        binding.btnMypageLogout.setOnClickListener {
            UserSharedPreferences.removeUserInfo(requireContext())

            Intent(context, LoginActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        }
    }
}