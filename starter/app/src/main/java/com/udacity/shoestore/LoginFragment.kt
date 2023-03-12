package com.udacity.shoestore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )
        binding.buttonLogin.setOnClickListener { view ->
            maybeNavigateToWelcomeFragment(view)
        }
        binding.buttonSignUp.setOnClickListener { view ->
            maybeNavigateToWelcomeFragment(view)
        }
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    private fun maybeNavigateToWelcomeFragment(view: View) {
        if ((binding.editTextEmail.text.toString() == "") || (binding.editTextPassword.text.toString() == "")) {
            val myToast =
                Toast.makeText(context, R.string.login_error_toast, Toast.LENGTH_SHORT)
            myToast.show()
            return
        }
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
        view.findNavController()
            .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())

    }
}