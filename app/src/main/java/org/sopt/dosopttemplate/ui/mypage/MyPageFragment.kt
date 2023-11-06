package org.sopt.dosopttemplate.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.model.data.UserInfo
import org.sopt.dosopttemplate.util.base.BindingFragment

class MyPageFragment : BindingFragment<FragmentMyPageBinding>() {
    private lateinit var UserInfo: UserInfo

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding = FragmentMyPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserInfo()
        initText()
    }

    private fun getUserInfo() {
        UserInfo = arguments?.getParcelable("UserInfoInfo")!!
    }

    private fun initText() {
        binding.run {
            tvMainId.text = UserInfo.id
            tvMainNickName.text = UserInfo.nickName
            tvMainAddress.text = UserInfo.address
        }
    }
}