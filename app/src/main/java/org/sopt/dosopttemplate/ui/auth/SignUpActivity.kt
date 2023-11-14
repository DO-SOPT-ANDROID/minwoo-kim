package org.sopt.dosopttemplate.ui.auth

import android.os.Bundle
import org.sopt.dosopttemplate.data.local.UserInfo
import org.sopt.dosopttemplate.data.remote.api.ServicePool
import org.sopt.dosopttemplate.data.remote.model.dto.request.auth.SignupReq
import org.sopt.dosopttemplate.databinding.ActivitySignUpBinding
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortToast
import retrofit2.Call
import retrofit2.Response

class SignUpActivity :
    BindingActivity<ActivitySignUpBinding>({ ActivitySignUpBinding.inflate(it) }) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        hideKeyBoard()
        initSignupBtn()
    }


    private fun initSignupBtn() {
        binding.run {
            btnSignUpToSignUp.setOnClickListener {
                val id = etSignUpId.text.toString()
                val password = etSignUpPw.text.toString()
                val nickname = etSignNickName.text.toString()
                val address = etSignAddress.text.toString()
                val userInfo = UserInfo(id, password, nickname, address)

                ServicePool.authService.signup(SignupReq(id, password, nickname))
                    .enqueue(object : retrofit2.Callback<Unit> {
                        override fun onResponse(
                            call: Call<Unit>,
                            response: Response<Unit>,
                        ) {
                            if (response.isSuccessful) {
                                shortToast("회원가입 성공")
                                intent.putExtra("UserInfo", userInfo)
                                setResult(RESULT_OK, intent)
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            shortToast("서버 에러 발생")
                        }
                    })
            }
        }
    }
}