package com.eduni.appcafeteria.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.eduni.appcafeteria.R
import com.eduni.appcafeteria.databinding.FragmentRegisterBinding
import com.eduni.appcafeteria.model.User
import com.eduni.appcafeteria.model.DataProvider

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRegisterBinding.bind(view)

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val email = binding.etEmail.text.toString()

            if (username.isNotBlank() && password.isNotBlank() && email.isNotBlank()) {
                val newUser = User(DataProvider.users.size + 1, username, password, email)
                DataProvider.users += newUser
                findNavController().navigate(R.id.loginFragment)
            } else {
                binding.etUsername.error = "Campo obligatorio"
                binding.etPassword.error = "Campo obligatorio"
                binding.etEmail.error = "Campo obligatorio"
            }
        }

        binding.backToLogin.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
