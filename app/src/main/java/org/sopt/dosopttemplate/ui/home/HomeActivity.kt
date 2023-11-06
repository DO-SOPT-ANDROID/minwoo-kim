package org.sopt.dosopttemplate.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.model.data.UserInfo
import org.sopt.dosopttemplate.ui.doandroid.DoAndroidFragment
import org.sopt.dosopttemplate.ui.mypage.MyPageFragment
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.inent.getParcelable

class HomeActivity : BindingActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }) {
    private lateinit var UserInfo: UserInfo

    companion object {
        const val DO_ANDROID = "DoAndroid"
        const val HOME_FRAGMENT = "HomeFragment"
        const val MY_PAGE = "MyPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        initBnv()
        getUserInfo()
        clickBnv()
    }

    private fun initBnv() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }

        binding.bnvHome.menu.findItem(R.id.menu_home).isChecked = true
    }

    private fun clickBnv() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_do_android -> {
                    replaceFragment(DO_ANDROID, null)
                    true
                }

                R.id.menu_home -> {
                    replaceFragment(HOME_FRAGMENT, null)
                    true
                }

                R.id.menu_mypage -> {
                    val bundle = Bundle().apply {
                        putParcelable("UserInfo", UserInfo)
                    }
                    replaceFragment(MY_PAGE, bundle)
                    true
                }

                else -> false
            }
        }
    }

    fun replaceFragment(name: String, bundle: Bundle?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val newFragment = when (name) {
            DO_ANDROID -> DoAndroidFragment()
            HOME_FRAGMENT -> HomeFragment()
            MY_PAGE -> MyPageFragment()
            else -> Fragment()
        }

        newFragment.arguments = bundle
        fragmentTransaction.replace(R.id.fcv_home, newFragment)
        fragmentTransaction.commit()
    }

    private fun getUserInfo() {
        UserInfo = intent.getParcelable("UserInfo", UserInfo::class.java)!!
    }
}