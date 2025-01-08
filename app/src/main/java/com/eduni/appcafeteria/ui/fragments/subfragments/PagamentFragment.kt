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
import com.eduni.appcafeteria.databinding.FragmentPagamentBinding
import com.eduni.appcafeteria.ui.adapters.PaymentAdapter
import com.eduni.appcafeteria.viewmodel.SharedViewModel

class PagamentFragment : Fragment(R.layout.fragment_pagament) {

    private lateinit var binding: FragmentPagamentBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val paymentAdapter = PaymentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPagamentBinding.inflate(inflater, container, false)

        binding.rvTotal.layoutManager = LinearLayoutManager(context)
        binding.rvTotal.adapter = paymentAdapter

        sharedViewModel.selectedProducts.observe(viewLifecycleOwner, Observer { selectedProducts ->
            paymentAdapter.submitList(selectedProducts.values.toList())

            val totalPrice = sharedViewModel.getTotalPrice()
            binding.tvTotalPrice.text = "Total: €${"%.2f".format(totalPrice)}"

            binding.btnPay.isEnabled = totalPrice > 0.0
        })

        binding.btnPay.setOnClickListener {
            sharedViewModel.clearSelectedProducts()
            paymentAdapter.deleteProductList()

            Toast.makeText(context, "Pago realizado. Regresando a inicio.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_pagamentFragment_to_nextFragment)
        }

        return binding.root
    }
}
