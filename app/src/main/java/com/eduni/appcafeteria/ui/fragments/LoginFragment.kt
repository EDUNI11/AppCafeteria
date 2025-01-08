package com.eduni.appcafeteria.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eduni.appcafeteria.R
import com.eduni.appcafeteria.databinding.FragmentLoginBinding
import com.eduni.appcafeteria.model.DataProvider

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            val user = DataProvider.users.find { it.username == username && it.password == password }

            if (user != null) {
                findNavController().navigate(R.id.menjarsFragment)
            } else {
                binding.etUsername.error = "Credenciales incorrectas"
                binding.etPassword.error = "Credenciales incorrectas"
            }
        }

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.menjarsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
