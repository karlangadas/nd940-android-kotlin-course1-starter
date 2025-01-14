package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentWelcomeBinding>(
            inflater, R.layout.fragment_welcome, container, false
        )
        binding.buttonGotToInstructions.setOnClickListener { view ->
            view.findNavController()
                .navigate(WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment())
        }
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }
}