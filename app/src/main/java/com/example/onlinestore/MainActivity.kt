package com.example.onlinestore

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.onlinestore.Data.AUTHFIREBASE
import com.example.onlinestore.Data.initFirebase
import com.example.onlinestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        if (AUTHFIREBASE.currentUser != null) {
            val navHostFragment =
                supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.mainFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}