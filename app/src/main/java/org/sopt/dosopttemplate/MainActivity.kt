package org.sopt.dosopttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("Id")
        val pw = intent.getStringExtra("Pw")
        val nickName = intent.getStringExtra("NickName")
        val address = intent.getStringExtra("Address")

        binding.run {
            tvMainNickName.text = nickName
            tvMainId.text = id
            tvMainAddress.text = address
        }
    }
}