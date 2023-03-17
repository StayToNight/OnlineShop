package com.staytonight.onlineshop.ui.profile

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.staytonight.domain.model.User
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.binding.BindingFragment
import com.staytonight.onlineshop.base.utils.Base64Converter
import com.staytonight.onlineshop.databinding.FragmentProfileBinding
import com.staytonight.onlineshop.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : BindingFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()

    private val galleryResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data as Uri

                viewModel.changeAvatar(Base64Converter.convertToBase64(getBimapFromUri(imageUri)))
                binding?.ivAvatar?.setImageURI(imageUri)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserData()

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProfileViewModel.State.ShowLoading -> {}
                is ProfileViewModel.State.HideLoading -> {}
                is ProfileViewModel.State.UserData -> {
                    setupUserData(state.user)
                }
            }
        }
    }

    private fun setupListeners() {
        setupLogout()
        setupChangePhotoBtn()
    }

    private fun setupLogout() {
        binding?.btnLogout?.setOnClickListener {
            viewModel.logout()
            goToAuth()
        }
    }

    private fun setupUserData(user: User?) {
        binding?.apply {
            tvName.text = String.format(
                getString(R.string.name_placeholder),
                user?.firstName, user?.lastName
            )

            tvBalance.text = String.format(
                getString(R.string.balance_placeholder),
                user?.balance?.toString()
            )

            user?.avatar?.let {
                ivAvatar.setImageBitmap(Base64Converter.convertToBitmap(it))
            }
            Log.e("TAG", "${user?.avatar}")
        }
    }

    private fun goToAuth() {
        val intent = Intent(context, AuthActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun setupChangePhotoBtn() {
        binding?.tvChangePhoto?.setOnClickListener {
            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            galleryResultLauncher.launch(galleryIntent)
        }
    }

    private fun getBimapFromUri(uri: Uri) = when {
        Build.VERSION.SDK_INT > 28 -> {
            val source =
                ImageDecoder.createSource(requireActivity().contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
        else -> {
            MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)
        }
    }
}