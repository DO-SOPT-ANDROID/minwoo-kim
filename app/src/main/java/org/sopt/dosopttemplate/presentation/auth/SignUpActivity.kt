package org.sopt.dosopttemplate.presentation.auth

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast

class SignupActivity :
    BindingActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) }) {
    private val signupViewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signupViewModel = signupViewModel

        hideKeyBoard()
        onClickSignupBtn()
        observeSignupValid()
        observeSignupState()
    }

    private fun onClickSignupBtn() {
        binding.btnSignUpToSignUp.setOnClickListener {
            signupViewModel.postSignup()
        }
    }

    private fun observeSignupValid() {
        signupViewModel.isIdValid.observe(this) { isIdValid ->
            if (!isIdValid && !signupViewModel.id.value.isNullOrBlank()) {
                binding.tilSignupId.isErrorEnabled = true
                binding.tilSignupId.error = "영문, 숫자 포함 6~10자"
            } else {
                binding.tilSignupId.isErrorEnabled = false
            }
            signupViewModel.signupBtnValidate()
        }
        signupViewModel.isPwValid.observe(this) { isPwValid ->
            if (!isPwValid && !signupViewModel.pw.value.isNullOrBlank()) {
                binding.tilSignupPw.isErrorEnabled = true
                binding.tilSignupPw.error = "영문, 숫자, 특수문자 포함 6~12자"
            } else {
                binding.tilSignupPw.isErrorEnabled = false
            }
            signupViewModel.signupBtnValidate()
        }
    }

    private fun observeSignupState() {
        lifecycleScope.launch {
            signupViewModel.signupState.flowWithLifecycle(lifecycle).onEach { state ->
                when (state) {
                    is UiState.Success -> {
                        shortToast("회원가입 성공")

                        intent.putExtra("UserInfo", state.data)
                        setResult(RESULT_OK, intent)
                        finish()
                    }

                    is UiState.Failure -> {
                        shortSnackBar(binding.root, "회원가입 실패, ${state.msg}")
                    }

                    is UiState.Loading -> {
                        shortSnackBar(binding.root, "회원가입 중")
                    }

                    is UiState.Empty -> return@onEach
                }
            }.launchIn(lifecycleScope)
        }
    }
}