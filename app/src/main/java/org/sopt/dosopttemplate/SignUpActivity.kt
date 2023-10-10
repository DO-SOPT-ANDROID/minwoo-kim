package org.sopt.dosopttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnSignUpToSignUp.setOnClickListener {
                if(etSignUpId.text.toString() == ""
                    || etSignUpPw.text.toString() == ""
                    || etSignNickName.text.toString() == ""
                    || etSignAddress.text.toString() == "") {
                    Snackbar.make(binding.root, "정보를 입력해주세요.", Snackbar.LENGTH_SHORT).show()
                } else if(etSignUpId.length() in 6..10
                    && etSignUpPw.length() in 8..12
                    && etSignNickName.text.toString() != ""
                    && etSignAddress.text.toString() != "") {
                    intent.putExtra("Id", etSignUpId.text.toString())
                    intent.putExtra("Pw", etSignUpPw.text.toString())
                    intent.putExtra("NickName", etSignNickName.text.toString())
                    intent.putExtra("Address", etSignAddress.text.toString())

                    setResult(RESULT_OK, intent)

                    finish()

                    Snackbar.make(binding.root, "회원가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()
                } else {
                    Snackbar.make(binding.root, "입력 조건을 확인해주세요.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}