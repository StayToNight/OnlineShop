package com.staytonight.onlineshop.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.staytonight.domain.usecase.profile.GetCurrentUserUseCase
import com.staytonight.onlineshop.base.binding.viewBinding
import com.staytonight.onlineshop.databinding.ActivityAuthBinding
import com.staytonight.onlineshop.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityAuthBinding::inflate)

    @Inject
    lateinit var getCurrentUserUseCase: GetCurrentUserUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkForCurrentUser()
    }

    private fun checkForCurrentUser() {
        if (getCurrentUserUseCase.getCurrentUser() != null)
            goToMain()
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}