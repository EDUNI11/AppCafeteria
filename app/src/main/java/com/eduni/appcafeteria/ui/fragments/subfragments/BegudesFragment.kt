package com.eduni.appcafeteria.ui.fragments.subfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduni.appcafeteria.R
import com.eduni.appcafeteria.databinding.FragmentBegudesBinding
import com.eduni.appcafeteria.model.DataProvider
import com.eduni.appcafeteria.viewmodel.SharedViewModel
import com.eduni.appcafeteria.ui.adapters.ProductAdapter

class BegudesFragment : Fragment(R.layout.fragment_begudes) {

    private lateinit var binding: FragmentBegudesBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val productAdapter = ProductAdapter { product ->
        sharedViewModel.toggleProductSelection(product)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBegudesBinding.inflate(inflater, container, false)

        binding.rvBegudes.layoutManager = LinearLayoutManager(context)
        binding.rvBegudes.adapter = productAdapter

        val productList = DataProvider.products.filter { it.category == "Begudes" }
        productAdapter.submitList(productList)

        sharedViewModel.selectedProducts.observe(viewLifecycleOwner) { selectedProducts ->
            binding.btnContinue.isEnabled = selectedProducts.isNotEmpty()
            val totalPrice = sharedViewModel.getTotalPrice()
            binding.tvTotalPrice.text = "Precio total: €${"%.2f".format(totalPrice)}"
        }

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_begudesFragment_to_nextFragment)
        }

        return binding.root
    }
}
