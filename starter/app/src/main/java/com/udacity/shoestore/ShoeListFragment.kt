package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.fragment_shoe_detail.view.*
import kotlinx.android.synthetic.main.fragment_shoe_list.view.*
import kotlinx.android.synthetic.main.shoe_item.view.*

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.lifecycleOwner = this
        viewModel.shoes.observe(viewLifecycleOwner, Observer { shoes ->
            if (shoes.size > 0) {
                for (shoe in shoes) {
                    binding.shoeList.addView(inflateShoeItemView(shoe))
                }
            }
        })

        binding.plusFab.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }

    private fun inflateShoeItemView(shoe: Shoe): View {
        val shoeCardItem: View = View.inflate(context, R.layout.shoe_item, null)
        shoeCardItem.textShoeName.text = "Name: ${shoe.name}"
        shoeCardItem.textShoeSize.text = "Size: ${shoe.size}"
        shoeCardItem.textCompany.text = "Company: ${shoe.company}"
        shoeCardItem.textShoeDescription.text = "Description: ${shoe.description}"

        context?.resources?.getDimensionPixelSize(R.dimen.shoe_card_item_margin_bottom)
            ?.let {
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, 0, 0, it)
                shoeCardItem.layoutParams = layoutParams
            }

        return shoeCardItem
    }
}