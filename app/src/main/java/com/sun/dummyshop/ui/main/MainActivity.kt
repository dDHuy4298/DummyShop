package com.sun.dummyshop.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sun.dummyshop.R
import com.sun.dummyshop.utils.NetworkUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var isConnection = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.DummyShopTheme)
        isConnection = NetworkUtil.isConnection(this)
        if (!isConnection) {
            showDialogCheckInternet().show()
            return
        }
        setContentView(R.layout.activity_main)

        val navigationHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentNavigationHost) as NavHostFragment
        val navigationController = navigationHostFragment.navController
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.isVisible = destination.id in mainFragments
        }
        bottomNavigationView.apply {
            setupWithNavController(navigationController)
//            setOnNavigationItemSelectedListener {
////                if (it.itemId != bottomNavigationView.selectedItemId) {
////                    NavigationUI.onNavDestinationSelected(it, navigationController)
////                }
//                when(it.itemId) {
//                    R.id.fragmentHome -> navigationController.navigate(R.id.fragmentHome, null, navOption)
//                    R.id.fragmentFavorite -> navigationController.navigate(R.id.fragmentFavorite, null, navOption)
//                    R.id.fragmentHistory -> navigationController.navigate(R.id.fragmentHistory, null, navOption)
//                }
//                true
//            }
            setOnNavigationItemReselectedListener { item ->
                return@setOnNavigationItemReselectedListener
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun showDialogCheckInternet(): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_internet))
            .setMessage(getString(R.string.text_message_internet))
            .setPositiveButton(getString(R.string.text_ok)) { _, _ ->
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                finish()
            }
            .setNegativeButton(getString(R.string.text_cancel)) { _, _ ->
                finish()
            }
        return builder.create()
    }

    companion object {
        val mainFragments = listOf(
            R.id.fragmentHome,
            R.id.fragmentFavorite,
            R.id.fragmentHistory
        )
    }
}
