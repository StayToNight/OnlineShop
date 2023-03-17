package com.staytonight.onlineshop.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.binding.BindingFragment
import com.staytonight.onlineshop.base.extensions.hide
import com.staytonight.onlineshop.base.extensions.show
import com.staytonight.onlineshop.databinding.FragmentLogInBinding
import com.staytonight.onlineshop.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BindingFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers() //todo auth state
    }

    private fun setupListeners() {
        setupLogin()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginViewModel.State.ShowLoading -> {
                    showLoading()
                }
                is LoginViewModel.State.HideLoading -> {
                    hideLoading()
                }
                is LoginViewModel.State.Login -> {
                    if (state.loginState)
                        goToMain()
                    else
                        showError()
                }
            }
        }
    }

    private fun setupLogin() {
        binding?.apply {
            btnLogIn.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.getText()

                if (email.isNotEmpty() && password.isNotEmpty())
                    viewModel.login(email, password)
                else
                    showError()
            }
        }
    }

    private fun showLoading() {
        binding?.apply {
            btnLogIn.isClickable = false
            btnLogIn.text = ""
            pb.show()
        }
    }

    private fun hideLoading() {
        binding?.apply {
            btnLogIn.isClickable = true
            btnLogIn.text = getText(R.string.login)
            pb.hide()
        }
    }

    private fun showError() {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun goToMain() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}