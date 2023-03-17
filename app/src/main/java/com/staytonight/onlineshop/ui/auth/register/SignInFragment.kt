package com.staytonight.onlineshop.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.binding.BindingFragment
import com.staytonight.onlineshop.base.extensions.hide
import com.staytonight.onlineshop.base.extensions.show
import com.staytonight.onlineshop.databinding.FragmentSignInBinding
import com.staytonight.onlineshop.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BindingFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {
        setupNavigateToLogIn()
        setupRegistration()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SignInViewModel.State.ShowLoading -> {
                    showLoading()
                }
                is SignInViewModel.State.HideLoading -> {
                    hideLoading()
                }
                is SignInViewModel.State.Register -> {
                    if (state.regState)
                        goToMain()
                    else
                        showError()
                }
            }
        }
    }

    private fun setupRegistration() {
        binding?.apply {
            btnSignIn.setOnClickListener {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val email = etEmail.text.toString()

                if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty())
                    viewModel.register(firstName, lastName, email)
                else
                    showError()
            }
        }
    }

    private fun setupNavigateToLogIn() {
        binding?.apply {
            tvLogIn.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_logInFragment)
            }
        }
    }

    private fun showLoading() {
        binding?.apply {
            btnSignIn.isClickable = false
            btnSignIn.text = ""
            pb.show()
        }
    }

    private fun hideLoading() {
        binding?.apply {
            btnSignIn.isClickable = true
            btnSignIn.text = getText(R.string.sign_in)
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