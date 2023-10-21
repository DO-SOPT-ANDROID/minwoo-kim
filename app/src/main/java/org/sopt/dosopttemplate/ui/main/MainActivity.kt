package org.sopt.dosopttemplate.ui.main

import android.os.Bundle
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.model.data.User
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.inent.getParcelable

class MainActivity : BindingActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        getUserData()
        initText()
    }

    private fun getUserData() {
        user = intent.getParcelable("User", User::class.java)!!
    }

    private fun initText() {
        binding.run {
            tvMainId.text = user.id
            tvMainNickName.text = user.nickName
            tvMainAddress.text = user.address
        }
    }
}