package org.sopt.dosopttemplate.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import org.sopt.dosopttemplate.data.local.UserInfo
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortToast

class SignupActivity :
    BindingActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) }) {
    private val viewModel by viewModels<SignupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signupViewModel = viewModel

        hideKeyBoard()
        onClickSignupBtn()
        observeSignupValid()
        observeSignupState()
    }

    private fun onClickSignupBtn() {
        binding.btnSignUpToSignUp.setOnClickListener {
            viewModel.postSignup()
        }
    }

    private fun observeSignupValid() {
        viewModel.isIdValid.observe(this) { isIdValid ->
            if (!isIdValid && !viewModel.id.value.isNullOrBlank()) {
                binding.tilSignupId.isErrorEnabled = true
                binding.tilSignupId.error = "영문, 숫자 포함 6~10자"
            } else {
                binding.tilSignupId.isErrorEnabled = false
            }
            viewModel.signupBtnEnabled()
        }
        viewModel.isPwValid.observe(this) { isPwValid ->
            if (!isPwValid && !viewModel.pw.value.isNullOrBlank()) {
                binding.tilSignupPw.isErrorEnabled = true
                binding.tilSignupPw.error = "영문, 숫자, 특수문자 포함 6~12자"
            } else {
                binding.tilSignupPw.isErrorEnabled = false
            }
            viewModel.signupBtnEnabled()
        }
    }

    private fun observeSignupState() {
        viewModel.signupState.observe(this) { signupState ->
            when (signupState) {
                is SignupState.Success -> {
                    shortToast("회원가입 성공")
                    intent.putExtra("UserInfo", signupState.data)
                    setResult(RESULT_OK, intent)
                    finish()
                }

                is SignupState.Error -> {
                    shortToast("회원가입 실패 : ${signupState.message}")
                }

                is SignupState.Loading -> {
                    shortToast("회원가입 중")
                }
            }
        }
    }
}