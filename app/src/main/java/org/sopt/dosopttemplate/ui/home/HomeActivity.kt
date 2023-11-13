package org.sopt.dosopttemplate.ui.home

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.ui.doandroid.DoAndroidFragment
import org.sopt.dosopttemplate.ui.mypage.MyPageFragment
import org.sopt.dosopttemplate.util.base.BindingActivity
import org.sopt.dosopttemplate.util.context.shortSnackBar

class HomeActivity : BindingActivity<ActivityHomeBinding>({ ActivityHomeBinding.inflate(it) }) {
    private var backPressedTime: Long = 0

    companion object {
        const val DO_ANDROID = "DoAndroid"
        const val HOME_FRAGMENT = "HomeFragment"
        const val MY_PAGE = "MyPage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        initBnv()
        clickBnv()
        initOnBackPressed()
    }

    private fun initBnv() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }

        binding.bnvHome.menu.findItem(R.id.item_home).isChecked = true
    }

    private fun clickBnv() {
        binding.bnvHome.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_do_android -> {
                    replaceFragment(DO_ANDROID, null)
                    true
                }

                R.id.item_home -> {
                    replaceFragment(HOME_FRAGMENT, null)
                    true
                }

                R.id.item_mypage -> {
                    replaceFragment(MY_PAGE, null)
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(name: String, bundle: Bundle?) {
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