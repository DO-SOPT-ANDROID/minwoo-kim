package org.sopt.dosopttemplate.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.model.data.User
import org.sopt.dosopttemplate.ui.main.MainActivity
import org.sopt.dosopttemplate.ui.signup.SignUpActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>

    lateinit var user: User

    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var nickName: String
    private lateinit var address: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSignUpActivityLauncher()
        initLoginBtnListener()
        initSignUpBtnListener()
    }

    private fun setSignUpActivityLauncher() {
        signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    id = result.data?.getStringExtra("Id") ?: ""
                    pw = result.data?.getStringExtra("Pw") ?: ""
                    nickName = result.data?.getStringExtra("NickName") ?: ""
                    address = result.data?.getStringExtra("Address") ?: ""
                }
            }
    }

    private fun initLoginBtnListener() {
        binding.btnLoginSubmit.setOnClickListener {
            if (::id.isInitialized && ::pw.isInitialized) {
                if (binding.etLoginId.text.toString() == id && binding.etLoginPw.text.toString() == pw) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)

                    intent.putExtra("Id", id)
                    intent.putExtra("Pw", pw)
                    intent.putExtra("NickName", nickName)
                    intent.putExtra("Address", address)

                    startActivity(intent)

                    shortToast("로그인 되었습니다.")
                } else shortSnackBar(binding.root, "회원정보가 일치하지 않습니다.")
            } else shortSnackBar(binding.root, "먼저 회원가입을 진행해주세요.")
        }
    }

    private fun initSignUpBtnListener() {
        binding.btnLoginToSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            signUpLauncher.launch(intent)
        }
    }
}