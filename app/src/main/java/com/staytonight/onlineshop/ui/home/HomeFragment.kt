package com.staytonight.onlineshop.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.binding.BindingFragment
import com.staytonight.onlineshop.base.extensions.hide
import com.staytonight.onlineshop.base.extensions.show
import com.staytonight.onlineshop.base.utils.Base64Converter
import com.staytonight.onlineshop.base.utils.delegate_adapter.CompositeAdapter
import com.staytonight.onlineshop.databinding.FragmentHomeBinding
import com.staytonight.onlineshop.ui.home.delegates.CategoryDelegateAdapter
import com.staytonight.onlineshop.ui.home.delegates.FlashSaleDelegateAdapter
import com.staytonight.onlineshop.ui.home.delegates.LatestDealDelegateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter by lazy {
        CompositeAdapter(
            CategoryDelegateAdapter(),
            LatestDealDelegateAdapter {
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
            },
            FlashSaleDelegateAdapter {
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
            }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPage()
        getUserAvatar()

        setupObservers()
        setupHomeRV()
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is HomeViewModel.State.ShowLoading -> {
                    showLoading()
                }
                is HomeViewModel.State.HideLoading -> {
                    hideLoading()
                }
                is HomeViewModel.State.Result -> {
                    homeAdapter.submitList(state.page)
                }
                is HomeViewModel.State.Avatar -> {
                    binding?.toolbar?.ivAvatar?.setImageBitmap(state.avatar?.let {
                        Base64Converter.convertToBitmap(
                            it
                        )
                    })
                }
                is HomeViewModel.State.Error -> {
                    showError()
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupHomeRV() {
        binding?.rvHome?.adapter = homeAdapter
    }

    private fun showLoading() {
        binding?.pb?.show()
        binding?.rvHome?.hide()
        binding?.tvError?.hide()
    }

    private fun hideLoading() {
        binding?.pb?.hide()
        binding?.rvHome?.show()
    }

    private fun showError() {
        binding?.pb?.hide()
        binding?.rvHome?.hide()
        binding?.tvError?.show()
    }

    private fun getUserAvatar() {
        viewModel.getUserAvatar()
    }
}