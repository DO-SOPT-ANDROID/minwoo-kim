package org.sopt.dosopttemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var signUpLauncher: ActivityResultLauncher<Intent>

    lateinit var id: String
    lateinit var pw: String
    lateinit var nickName: String
    lateinit var address: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 화면에서 데이터 가져오기
        signUpLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("Id") ?: ""
                pw = result.data?.getStringExtra("Pw") ?: ""
                nickName = result.data?.getStringExtra("NickName") ?: ""
                address = result.data?.getStringExtra("Address") ?: ""
            }
        }

        binding.run {
            btnLoginSubmit.setOnClickListener {
                if(::id.isInitialized && ::pw.isInitialized) {
                    if(etLoginId.text.toString() == id && etLoginPw.text.toString() == pw) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)

                        intent.putExtra("Id", id)
                        intent.putExtra("Pw", pw)
                        intent.putExtra("NickName", nickName)
                        intent.putExtra("Address", address)

                        startActivity(intent)

                        Snackbar.make(binding.root, "로그인 되었습니다.", Snackbar.LENGTH_SHORT).show()
                    } else Snackbar.make(binding.root, "회원정보가 일치하지 않습니다.", Snackbar.LENGTH_SHORT).show()
                } else Snackbar.make(binding.root, "회원가입을 진행해주세요.", Snackbar.LENGTH_SHORT).show()
            }

            btnLoginToSignUp.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                signUpLauncher.launch(intent)
            }
        }
    }
}