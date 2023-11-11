package org.sopt.dosopttemplate.ui.auth

import android.os.Bundle
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.model.data.UserInfo
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast

class SignUpActivity :
    BindingActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) }) {
    lateinit var userInfo: UserInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.root.setOnClickListener {
            hideKeyBoard()
        }

        signUpBtnListener()
    }

    private fun signUpBtnListener() {
        binding.run {
            btnSignUpToSignUp.setOnClickListener {
                if (etSignUpId.text.isEmpty()
                    || etSignUpPw.text.isEmpty()
                    || etSignNickName.text.isEmpty()
                    || etSignAddress.text.isEmpty()
                ) {
                    shortSnackBar(binding.root, "정보를 입력해주세요.")
                } else if (etSignUpId.length() in 6..10
                    && etSignUpPw.length() in 8..12
                    && etSignNickName.text.isNotEmpty()
                    && etSignAddress.text.isNotEmpty()
                ) {
                    userInfo = UserInfo(
                        etSignUpId.text.toString(), etSignUpPw.text.toString(),
                        etSignNickName.text.toString(), etSignAddress.text.toString()
                    )
                    intent.putExtra("UserInfo", userInfo)

                    setResult(RESULT_OK, intent)

                    finish()

                    shortToast("회원가입이 완료되었습니다.")
                } else {
                    shortSnackBar(binding.root, "입력 조건을 확인해주세요.")
                }
            }
        }
    }
}