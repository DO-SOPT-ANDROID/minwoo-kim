package org.sopt.dosopttemplate.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.context.shortSnackBar
import org.sopt.dosopttemplate.util.context.shortToast

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpBtnListener()
    }

    private fun signUpBtnListener() {
        binding.run {
            btnSignUpToSignUp.setOnClickListener {
                if (etSignUpId.text.toString() == ""
                    || etSignUpPw.text.toString() == ""
                    || etSignNickName.text.toString() == ""
                    || etSignAddress.text.toString() == ""
                ) {
                    shortSnackBar(binding.root, "정보를 입력해주세요.")
                } else if (etSignUpId.length() in 6..10
                    && etSignUpPw.length() in 8..12
                    && etSignNickName.text.toString() != ""
                    && etSignAddress.text.toString() != ""
                ) {
                    intent.putExtra("Id", etSignUpId.text.toString())
                    intent.putExtra("Pw", etSignUpPw.text.toString())
                    intent.putExtra("NickName", etSignNickName.text.toString())
                    intent.putExtra("Address", etSignAddress.text.toString())

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