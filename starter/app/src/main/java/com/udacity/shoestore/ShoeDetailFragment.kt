package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_login.view.*

class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ShoeListViewModel
    lateinit var shoe: Shoe


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater, R.layout.fragment_shoe_detail, container, false
        )
        shoe = Shoe("", .0, "", "")
        binding.detail = this
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.buttonCancel.setOnClickListener { view -> view.findNavController().popBackStack() }
        binding.buttonSave.setOnClickListener { view ->
            if (binding.editTextShoeSize.text != null) {
                shoe.size = binding.editTextShoeSize.text.toString().toDouble()
                viewModel.addNewShoe(shoe)
                view.findNavController().popBackStack()
            }
        }
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }
}