package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentInstructionsBinding>(
            inflater, R.layout.fragment_instructions, container, false
        )
        binding.buttonGoToShowList.setOnClickListener { view ->
            view.findNavController()
                .navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment())
        }
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }
}