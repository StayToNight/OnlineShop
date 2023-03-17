package com.staytonight.onlineshop.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.staytonight.domain.model.Details
import com.staytonight.onlineshop.R
import com.staytonight.onlineshop.base.binding.BindingFragment
import com.staytonight.onlineshop.base.extensions.hide
import com.staytonight.onlineshop.base.extensions.show
import com.staytonight.onlineshop.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BindingFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetails()

        setupObservers()
        setupListeners()
        setupAddRemoveFromCart()
    }

    private fun setupListeners() {
        binding?.apply {
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailsViewModel.State.ShowLoading -> {
                    showLoading()
                }
                is DetailsViewModel.State.HideLoading -> {
                    hideLoading()
                }
                is DetailsViewModel.State.Detail -> {
                    setupData(state.details)
                }
            }
        }
    }

    private fun setupData(details: Details) {
        binding?.apply {
            tvName.text = details.name
            tvDescription.text = details.description
            tvPrice.text = String.format(
                getString(R.string.balance_placeholder),
                details.price
            )
            tvRateAndRateQuantity.text = String.format(
                getString(R.string.rate_holder),
                details.rating,
                details.numberOfReviews
            )
            Glide.with(requireContext())
                .load(details.imageUrls.firstOrNull())
                .error(R.drawable.details)
                .centerCrop()
                .into(ivItem)
            rvImages.adapter = DetailsAdapter(details.imageUrls) { image ->
                Glide.with(requireContext())
                    .load(image)
                    .error(R.drawable.details)
                    .centerCrop()
                    .into(ivItem)
            }
        }
    }

    private fun setupAddRemoveFromCart() {
        binding?.apply {
            btnAdd.setOnClickListener {
                tvTotalPrice.text = String.format(
                    getString(R.string.balance_placeholder),
                    viewModel.addToCart()
                )
            }
            btnRemove.setOnClickListener {
                tvTotalPrice.text = String.format(
                    getString(R.string.balance_placeholder),
                    viewModel.removeFromCart()
                )
            }
        }
    }

    private fun showLoading() {
        binding?.apply {
            pb.show()
            tvName.hide()
            tvPrice.hide()
            tvDescription.hide()
            tvRateAndRateQuantity.hide()
            rvImages.hide()
            ivStar.hide()
            cvImage.hide()
            ivItem.hide()
        }
    }

    private fun hideLoading() {
        binding?.apply {
            pb.hide()
            tvName.show()
            tvPrice.show()
            tvDescription.show()
            tvRateAndRateQuantity.show()
            rvImages.show()
            ivStar.show()
            cvImage.show()
            ivItem.show()
        }
    }

}