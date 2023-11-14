package org.sopt.dosopttemplate.ui.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.data.local.UserInfo
import org.sopt.dosopttemplate.data.remote.api.ServicePool
import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.LoginReq
import org.sopt.dosopttemplate.data.remote.model.dto.response.auth.LoginRes
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.ui.home.HomeActivity
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast
import org.sopt.dosopttemplate.util.inent.getParcelable
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BindingActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private lateinit var signupLauncher: ActivityResultLauncher<Intent>

    private var backPressedTime: Long = 0
    private lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        hideKeyBoard()
        autoLogin()
        setSignUpActivityLauncher()
        initLoginBtn()
        initSignUpBtnListener()
        initOnBackPressed()
    }

    private fun autoLogin() {
        if (UserSharedPreferences.getUserInfo(this).id != null) {
            startHomeActivity()
        }
    }

    private fun setSignUpActivityLauncher() {
        signupLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    userInfo = result.data?.getParcelable("UserInfo", UserInfo::class.java)!!
                }
            }
    }

    private fun initLoginBtn() {
        binding.btnLoginSubmit.setOnClickListener {
            val id = binding.tilLoginId.editText?.text.toString()
            val password = binding.tilLoginPw.editText?.text.toString()

            ServicePool.authService.login(LoginReq(id, password))
                .enqueue(object : retrofit2.Callback<LoginRes> {
                    override fun onResponse(
                        call: Call<LoginRes>,
                        response: Response<LoginRes>,
                    ) {
                        if (response.isSuccessful) {
                            val data: LoginRes = response.body()!!
                            val userId = data.id
                            shortToast("로그인 성공! 유저 ID는 $userId 입니다")

                            setUserSharedPreferences(userInfo)

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            shortToast("에러가 발생했어요~")
                        }
                    }

                    override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                        shortToast("서버 에러 발생")
                    }
                })
        }
    }

    private fun initSignUpBtnListener() {
        binding.btnLoginToSignUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            signupLauncher.launch(intent)
        }
    }

    private fun setUserSharedPreferences(userInfo: UserInfo) {
        if (userInfo.id?.isNotEmpty()!!) {
            UserSharedPreferences.setUserInfo(this@LoginActivity, userInfo)
        }
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