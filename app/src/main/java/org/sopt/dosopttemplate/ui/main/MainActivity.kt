package org.sopt.dosopttemplate.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (id, nickName, address) = getUserData()
        initText(id, nickName, address)
    }

    private fun getUserData(): Triple<String?, String?, String?> {
        val id = intent.getStringExtra("Id")
        val nickName = intent.getStringExtra("NickName")
        val address = intent.getStringExtra("Address")
        return Triple(id, nickName, address)
    }

    private fun initText(id: String?, nickName: String?, address: String?) {
        binding.run {
            tvMainId.text = id
            tvMainNickName.text = nickName
            tvMainAddress.text = address
        }
    }
}