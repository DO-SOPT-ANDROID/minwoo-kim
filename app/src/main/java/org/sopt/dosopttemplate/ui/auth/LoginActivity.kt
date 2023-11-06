package org.sopt.dosopttemplate.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.model.data.UserInfo
import org.sopt.dosopttemplate.ui.home.HomeActivity
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast
import org.sopt.dosopttemplate.util.inent.getParcelable

class LoginActivity : BindingActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>

    lateinit var UserInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyBoard()
        }

        setSignUpActivityLauncher()
        initLoginBtnListener()
        initSignUpBtnListener()
    }

    private fun setSignUpActivityLauncher() {
        signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    UserInfo = result.data?.getParcelable("UserInfo", UserInfo::class.java)!!
                }
            }
    }

    private fun initLoginBtnListener() {
        binding.btnLoginSubmit.setOnClickListener {
            if (::UserInfo.isInitialized) {
                if (binding.etLoginId.text.toString() == UserInfo.id && binding.etLoginPw.text.toString() == UserInfo.pw) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)

                    intent.putExtra("UserInfo", UserInfo)

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