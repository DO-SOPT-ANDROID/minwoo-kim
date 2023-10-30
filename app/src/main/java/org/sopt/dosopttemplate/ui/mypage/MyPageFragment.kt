package org.sopt.dosopttemplate.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.model.data.User
import org.sopt.dosopttemplate.util.base.BindingFragment

class MyPageFragment : BindingFragment<FragmentMyPageBinding>() {
    private lateinit var user: User

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding = FragmentMyPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserData()
        initText()
    }

    private fun getUserData() {
        user = arguments?.getParcelable("UserInfo")!!
    }

    private fun initText() {
        binding.run {
            tvMainId.text = user.id
            tvMainNickName.text = user.nickName
            tvMainAddress.text = user.address
        }
    }
}