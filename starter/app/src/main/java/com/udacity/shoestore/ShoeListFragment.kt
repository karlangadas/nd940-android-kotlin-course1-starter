package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
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
                    binding.shoeList.addView(inflateShoeItemView(shoe, inflater))
                }
            }
        })

        binding.plusFab.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        (activity as AppCompatActivity).supportActionBar?.let {
            // https://stackoverflow.com/questions/16771532/removing-left-arrow-from-the-actionbar-in-android
            it.setHomeButtonEnabled(false); // disable the button
            it.setDisplayHomeAsUpEnabled(false); // remove the left caret
            it.setDisplayShowHomeEnabled(false); // remove the icon
            it.show()
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun inflateShoeItemView(shoe: Shoe, inflater: LayoutInflater): View {
        val shoeItemBinding: ShoeItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.shoe_item, null, false
        )
        shoeItemBinding.textShoeName.text = "Name: ${shoe.name}"
        shoeItemBinding.textShoeSize.text = "Size: ${shoe.size}"
        shoeItemBinding.textCompany.text = "Company: ${shoe.company}"
        shoeItemBinding.textShoeDescription.text = "Description: ${shoe.description}"

        context?.resources?.getDimensionPixelSize(R.dimen.shoe_card_item_margin_bottom)
            ?.let {
                val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(0, 0, 0, it)
                shoeItemBinding.root.layoutParams = layoutParams
            }

        return shoeItemBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> requireView().findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)
    }
}