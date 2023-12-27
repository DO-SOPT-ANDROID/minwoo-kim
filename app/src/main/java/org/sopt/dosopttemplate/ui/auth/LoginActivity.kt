package org.sopt.dosopttemplate.ui.auth

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.local.UserInfo
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.ui.home.HomeActivity
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast
import org.sopt.dosopttemplate.util.inent.getParcelable

class LoginActivity : BindingActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private lateinit var signupLauncher: ActivityResultLauncher<Intent>
    private val loginViewModel by viewModels<LoginViewModel>()

    private var backPressedTime: Long = 0
    private lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        hideKeyBoard()
        autoLogin()
        setSignUpActivityLauncher()
        observeLoginState()
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

    private fun observeLoginState() {
        binding.btnLoginSubmit.setOnClickListener {
            loginViewModel.clickLoginBtn()

            lifecycleScope.launch {
                loginViewModel.loginState.flowWithLifecycle(lifecycle).onEach { state ->
                    when (state) {
                        is UiState.Success -> {
                            shortToast("로그인 성공! 유저 ID는 ${state.data.id} 입니다.")

                            setUserSharedPreferences(state.data)
                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            startActivity(intent)
                        }

                        is UiState.Failure -> {
                            shortSnackBar(binding.root, "로그인 실패, ${state.msg}")
                        }

                        is UiState.Loading -> {
                            shortSnackBar(binding.root, "로그인 중")
                        }

                        is UiState.Empty -> return@onEach
                    }
                }.launchIn(lifecycleScope)
            }
        }
    }

    private fun initSignUpBtnListener() {
        binding.btnLoginToSignup.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            signupLauncher.launch(intent)
        }
    }

    private fun setUserSharedPreferences(userInfo: UserInfo) {
        if (userInfo.id?.isNotEmpty() ?: return) {
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