package org.sopt.dosopttemplate.ui.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.data.local.UserInfo
import org.sopt.dosopttemplate.ui.home.HomeActivity
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast
import org.sopt.dosopttemplate.util.inent.getParcelable

class LoginActivity : BindingActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>

    private var backPressedTime: Long = 0
    private lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        hideKeyBoard()
        autoLogin()
        setSignUpActivityLauncher()
        initLoginBtnListener()
        initSignUpBtnListener()
        initOnBackPressed()
    }

    private fun autoLogin() {
        if (UserSharedPreferences.getUserInfo(this).id != null) {
            startHomeActivity()
        }
    }

    private fun setSignUpActivityLauncher() {
        signUpLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    userInfo = result.data?.getParcelable("UserInfo", UserInfo::class.java)!!
                }
            }
    }

    private fun initLoginBtnListener() {
        binding.btnLoginSubmit.setOnClickListener {
            if (::userInfo.isInitialized) {
                if (binding.tilLoginId.editText?.text.toString() == userInfo.id && binding.tilLoginPw.editText?.text.toString() == userInfo.pw) {
                    setUserSharedPreferences(userInfo)
                    startHomeActivity()

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

    private fun setUserSharedPreferences(userInfo: UserInfo) {
        UserSharedPreferences.setUserInfo(this@LoginActivity, userInfo)
    }

    private fun startHomeActivity() {
        Intent(this, HomeActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
        finish()
    }

    private fun initOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - backPressedTime >= 2000) {
                    backPressedTime = System.currentTimeMillis()

                    shortSnackBar(binding.root, "한번 더 누르면 앱이 종료 됩니다.")
                } else {
                    finish()
                }
            }
        }
        this.onBackPressedDispatcher.addCallback(this, callback)
    }
}